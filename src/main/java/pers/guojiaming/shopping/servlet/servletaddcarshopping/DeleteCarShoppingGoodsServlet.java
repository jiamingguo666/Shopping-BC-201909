package pers.guojiaming.shopping.servlet.servletaddcarshopping;

import pers.guojiaming.shopping.dao.AddShoppingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: 用户删除自己的购物车商品
 * @Author: jiaming.guo
 * @Date: 2019/8/19 16:34
 */
@WebServlet("/deleteCarShoppingGoodsServlet")
public class DeleteCarShoppingGoodsServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        int goodsId = Integer.parseInt(request.getParameter("goodsId")) ;
        //addShoppingDao层向数据库删除数据,判断是否删除成功，
        if(AddShoppingDao.deleteCarShooppingGoodsByGoodsId(goodsId)){
            request.getRequestDispatcher("/jsp/carshopping/myCarShopping.jsp").forward(request,response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
