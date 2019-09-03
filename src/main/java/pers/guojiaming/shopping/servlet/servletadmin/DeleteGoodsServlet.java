package pers.guojiaming.shopping.servlet.servletadmin;

import pers.guojiaming.shopping.dao.GoodsDao;
import pers.guojiaming.shopping.dao.AddShoppingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 管理员删除普通商品
 * @Author: jiaming.guo
 * @Date: 2019/8/10 16:34
 */
@WebServlet("/adminDeleteGoodsServlet")
public class DeleteGoodsServlet extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Object  id = session.getAttribute("adminId");
        if(id == null || "".equals(id)){
            request.getRequestDispatcher("/AdminFirstLogin").forward(request,response);
        }else{
            //获取删除商品界面获得的商品id信息
            String  goodsId = request.getParameter("goodsId");
            //GoodsDao层向数据库删除数据,判断是否删除成功，成功则眺转到商品展示页面，不成功则眺转到删除商品页面
            if(GoodsDao.deleteById(Integer.parseInt(goodsId))){
                //删除商品时，判断购物车是否有该商品，有则除购物车商品
                //判断该goodsId 是否在购物车数据表里
                int deleteGoodsId = Integer.parseInt(goodsId);
                int buyGoodsId = AddShoppingDao.checkIfDeleteGoodsExistCarShopping(deleteGoodsId);
                if(buyGoodsId != 0) {
                    if (AddShoppingDao.deleteCarShooppingGoodsByBuyGoodsId(buyGoodsId)) {
                        request.getRequestDispatcher("/jsp/admin/homePage.jsp").forward(request,response);
                    }
                }else {
                    request.getRequestDispatcher("/jsp/admin/homePage.jsp").forward(request,response);
                }
            }
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
