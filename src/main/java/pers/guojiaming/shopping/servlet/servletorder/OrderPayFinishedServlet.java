package pers.guojiaming.shopping.servlet.servletorder;

import pers.guojiaming.shopping.dao.IntegralDao;
import pers.guojiaming.shopping.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 获取完成订单的订单资料
 * @Author: jiaming.guo
 * @Date: 2019/8/19 17:34
 */
@WebServlet("/OrderPayFinishedServlet")
public class OrderPayFinishedServlet extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        //从登录界面获得用户的id
        HttpSession session = request.getSession();
        Object id = session.getAttribute("id");
        int buyerId = (Integer)id;
        //从确认收货页面获取订单编号
        String orderNumber = request.getParameter("orderNumber");
        //根据订单编号将订单状态改为已支付
        String orderState ="交易完成";
        if(OrderDao.updateOrderStateByOrderNumber(orderNumber,orderState)){
            //根据用户id和订单状态获取该用户在交易完成时所买商品的总价
            double sumPrice = OrderDao.sumPriceByUserIdAndOrderState(buyerId, orderState);
            //计算该订单所获得积分
            double integral = sumPrice * 0.01;
            //将该积分值加到ser表的积分值
            IntegralDao.updateUserIntegral(integral,buyerId);
             //计算积完成后跳转到确认收获页面
            request.getRequestDispatcher("/jsp/order/confirmReceipt.jsp").forward(request,response);
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
