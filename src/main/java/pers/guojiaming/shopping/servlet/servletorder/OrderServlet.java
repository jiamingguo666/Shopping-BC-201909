package pers.guojiaming.shopping.servlet.servletorder;

import pers.guojiaming.shopping.dao.AddShoppingDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 用户进入订单页面前判断是否登录，未登录则返回登录界面
 * @Author: jiaming.guo
 * @Date: 2019/8/19 20:04
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        //从登录界面获得用户的id
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("id");
        //提交订单后将数据库购物车的表信息删除
        AddShoppingDao.deleteCarShooppingGoodsByUserId(userId);
        //再跳转到订单页面，从提交订单的数据表获取信息
        request.getRequestDispatcher("/jsp/order/unpaidOrder.jsp").forward(request,response);
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);

    }
}
