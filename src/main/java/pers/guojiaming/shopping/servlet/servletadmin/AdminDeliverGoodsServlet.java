package pers.guojiaming.shopping.servlet.servletadmin;

import pers.guojiaming.shopping.dao.OrderDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: 管理员删除普通商品
 * @Author: jiaming.guo
 * @Date: 2019/8/6 16:34
 */
@WebServlet("/AdminDeliverGoodsServlet")
public class AdminDeliverGoodsServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        //从待支付页面获取订单编号
        String orderNumber = request.getParameter("orderNumber");
        //根据订单编号将订单状态改为已支付
        String orderState ="配送中";
        if(OrderDao.updateOrderStateByOrderNumber(orderNumber,orderState)){
            request.getRequestDispatcher("/jsp/admin/adminPaidOrder.jsp").forward(request,response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
