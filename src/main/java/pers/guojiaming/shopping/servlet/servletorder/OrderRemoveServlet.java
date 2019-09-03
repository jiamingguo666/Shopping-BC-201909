package pers.guojiaming.shopping.servlet.servletorder;

import pers.guojiaming.shopping.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: 获取取消订单的资料
 * @Author: jiaming.guo
 * @Date: 2019/8/19 20:34
 */
@WebServlet("/OrderRemoveServlet")
public class OrderRemoveServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        //从待支付页面获取订单编号
        String orderNumber = request.getParameter("orderNumber");
        //根据订单编号将订单状态改为已支付
        String orderState ="订单已取消";
        if(OrderDao.updateOrderStateByOrderNumber(orderNumber,orderState)){
            //订单取消成功后，将订单的购买的商品数量加回库存
            OrderDao.addGoodsNumberByOrderNumber(orderNumber);
            request.getRequestDispatcher("/jsp/order/unpaidOrder.jsp").forward(request,response);

        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         this.doPost(request,response);
    }
}
