package pers.guojiaming.shopping.servlet.servletaddcarshopping;
import pers.guojiaming.shopping.dao.GoodsDao;
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
 * @Description: 获得加入购物车的商品资料。存进数据库
 * @Author: jiaming.guo
 * @Date: 2019/8/19 16:34
 */
@WebServlet("/addCarShoppingServlet")
public class AddCarShoppingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        //剩余量
        int surplusNumber = 0;
        String numberMessage = "购买数量需在库存范围内且大于0";
        HttpSession session = request.getSession();
        Object id = session.getAttribute("id");
        if(id == null || "".equals(id) || UserDao.checkIfIdExist((Integer)id) == 0){
            request.getRequestDispatcher("/FirstLogin").forward(request,response);
        }else{
             int userId = (Integer) id;
            //从购物车页面获得购买商品的资料
            String goodsName = (String) session.getAttribute("goodsName");
            double buyPrice = (Integer) session.getAttribute("buyPrice");
            String goodsCity = (String) session.getAttribute("goodsCity");
            int buyGoodsId = Integer.parseInt(request.getParameter("buygoodsId"));
            String goodsNumber = request.getParameter("buyNumber");
            if("".equals(goodsNumber)){
                request.setAttribute("numberMessage",numberMessage);
                request.getRequestDispatcher("/jsp/carshopping/addCarShopping.jsp").forward(request,response);
            }else {
                int buyGoodsNumber = Integer.parseInt(request.getParameter("buyNumber"));
                //判断输入的购买数量是否合理
                surplusNumber = GoodsDao.buyNumberCompareGoodsNumber(buyGoodsId, buyGoodsNumber);
                //若剩余量小于0，则返回加入购物车页提示
                if (buyGoodsNumber <= 0 || surplusNumber < 0) {
                    request.setAttribute("numberMessage", numberMessage);
                    request.getRequestDispatcher("/jsp/carshopping/addCarShopping.jsp").forward(request, response);
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
                                int goodId = list.get(i).getBuygoodsId();
                                if (goodId == buyGoodsId) {
                                    int buyNumber = list.get(i).getBuyNumber();
                                    //将重复添加的商品数量添加，并存在list中
                                    int buySumNumber = buyNumber + buyGoodsNumber;
                                    //判断累加的数量是否超过库存量
                                    surplusNumber = GoodsDao.buyNumberCompareGoodsNumber(buyGoodsId, buySumNumber);
                                    //若累加值超过库存量，则返回提示
                                    if (surplusNumber < 0) {
                                        request.setAttribute("numberMessage", numberMessage);
                                        request.getRequestDispatcher("/jsp/carshopping/addCarShopping.jsp").forward(request, response);
                                    } else {
                                        //从list中将更新数据拿出，将更新的数据存在carShopping中
                                        CarShopping carShopping = new CarShopping();
                                        carShopping.setBuygoodsId(goodId);
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
                    request.getRequestDispatcher("/jsp/carshopping/addCarShopping.jsp").forward(request, response);
                }
            }
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
