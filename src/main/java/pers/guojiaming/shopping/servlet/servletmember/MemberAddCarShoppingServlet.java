package pers.guojiaming.shopping.servlet.servletmember;
import pers.guojiaming.shopping.dao.GoodsDao;
import pers.guojiaming.shopping.dao.MemberDao;
import pers.guojiaming.shopping.dao.AddShoppingDao;
import pers.guojiaming.shopping.dao.UserDao;
import pers.guojiaming.shopping.entity.CarShopping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
/**
 * @Description: 获取会员加入购物车商品信息
 * @Author: jiaming.guo
 * @Date: 2019/8/20 11:34
 */
@WebServlet("/MemberAddCarShoppingServlet")
public class MemberAddCarShoppingServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        //剩余量
        int surplusNumber = 0;
        String isNotMember = "你还不是会员，请先到个人中心注册会员";
        String numberMessage = "购买数量需在库存范围内且大于0";
        HttpSession session = request.getSession();
        Object id = session.getAttribute("id");
        if (id == null || "".equals(id) || UserDao.checkIfIdExist((Integer)id) == 0) {
            request.getRequestDispatcher("/FirstLogin").forward(request, response);
        } else {
            //用户已登录，存在id；
            int userId = (Integer) id;
            //判断该用户是否为会员
            int memberId = MemberDao.checkIfMember(userId);
            if (memberId == 0) {
                request.setAttribute("isNotMember", isNotMember);
                request.getRequestDispatcher("/jsp/member/memberAddCarShopping.jsp").forward(request, response);
            } else {
                //从购物车页面获得购买商品的资料
                String goodsName = (String) session.getAttribute("goodsName");
                double buyPrice = (Double) session.getAttribute("goodsMemberPrice");
                String goodsCity = (String) session.getAttribute("goodsCity");
                int buyGoodsId = Integer.parseInt(request.getParameter("buyGoodsId"));
                String goodsNumber = request.getParameter("buyNumber");
                if("".equals(goodsNumber)){
                    request.setAttribute("numberMessage",numberMessage);
                    request.getRequestDispatcher("/jsp/member/memberAddCarShopping.jsp").forward(request,response);
                }else {
                    int buyGoodsNumber = Integer.parseInt(request.getParameter("buyNumber"));
                    //判断输入的购买数量是否合理
                    surplusNumber = GoodsDao.buyNumberCompareGoodsNumber(buyGoodsId, buyGoodsNumber);
                    //若剩余量小于0，则返回加入购物车页提示
                    if (buyGoodsNumber <= 0 || surplusNumber < 0) {
                        request.setAttribute("numberMessage", numberMessage);
                        request.getRequestDispatcher("/jsp/member/memberAddCarShopping.jsp").forward(request, response);
                    } else {
                        //从数据库获取用户购买商品的列表及其货物id
                        ArrayList<CarShopping> list = AddShoppingDao.getBuyGoodsByUserId(userId);
                        ArrayList<Integer> goodsIdList = AddShoppingDao.getBuyGoodsIdByUserId(userId);
                        // 判断购物车列表和商品id列表是否有值，没有则新建
                        if (list == null) {
                            list = new ArrayList<CarShopping>();
                        }
                        if (goodsIdList == null) {
                            goodsIdList = new ArrayList<Integer>();
                        }
                        if (goodsIdList.contains(buyGoodsId)) {
                            if (list != null && list.size() > 0) {
                                for (int i = 0; i < list.size(); i++) {
                                    if (list.get(i).getBuygoodsId() == buyGoodsId) {
                                        int buyNumber = list.get(i).getBuyNumber();
                                        //将重复添加的商品数量添加，并存在list中
                                        int buySumNumber = buyNumber + buyGoodsNumber;
                                        //判断累加的数量是否超过库存量
                                        surplusNumber = GoodsDao.buyNumberCompareGoodsNumber(buyGoodsId, buySumNumber);
                                        //若累加值超过库存量，则返回提示
                                        if (surplusNumber < 0) {
                                            request.setAttribute("numberMessage", numberMessage);
                                            request.getRequestDispatcher("/jsp/member/memberAddCarShopping.jsp").forward(request, response);
                                        } else {
                                            //从list中将更新数据拿出，将更新的数据存在carShopping中
                                            CarShopping carShopping = new CarShopping();
                                            int sameGoodsId = list.get(i).getBuygoodsId();
                                            carShopping.setBuygoodsId(sameGoodsId);
                                            carShopping.setBuyNumber(buySumNumber);
                                            int buyUserId = userId;
                                            carShopping.setUserId(buyUserId);
                                            //调用updateNumberAndPrice()方法将数据更新
                                            AddShoppingDao.updateNumberAndPrice(carShopping);
                                        }
                                    }
                                }
                            }
                        } else {
                            //首次添加，将数据存在数据库里
                            AddShoppingDao.addCarShopping(userId, goodsName, buyGoodsNumber, buyPrice, goodsCity, buyGoodsId);
                        }
                        request.getRequestDispatcher("/jsp/member/memberAddCarShopping.jsp").forward(request, response);
                    }
                }
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
