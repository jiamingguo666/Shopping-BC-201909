package pers.guojiaming.shopping.servlet.servletorder;

import pers.guojiaming.shopping.dao.GoodsDao;
import pers.guojiaming.shopping.dao.AddShoppingDao;
import pers.guojiaming.shopping.dao.OrderDao;
import pers.guojiaming.shopping.entity.CarShopping;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;


/**
 * @Description: 获取提交订单的资料
 * @Author: jiaming.guo
 * @Date: 2019/8/19 21:34
 */
@WebServlet("/submitOrderServlet")
public class SubmitOrderServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        //计算该订单的金额
        double sumPrice = 0;
        //从登录界面获得用户的id
        HttpSession session = request.getSession();
        int buyerId = (Integer) session.getAttribute("id");
        String notNullMessage = "输入不能为空";
        //从提交订单界面获得信息
        String buyerName = request.getParameter("buyerName");
        String buyerAddress = request.getParameter("buyerAddress");
        String buyerShipping = request.getParameter("buyerShipping");
        String buyerRemarks = request.getParameter("buyerRemarks");
        String buyerPhone = request.getParameter("buyerPhone");
        //参数校验
        if(buyerName == null || "".equals(buyerName)){
            request.setAttribute("notNullMessage",notNullMessage);
            request.getRequestDispatcher("/jsp/order/submitOrder.jsp").forward(request,response);
        }else if(buyerAddress == null || "".equals(buyerAddress)){
            request.setAttribute("notNullMessage",notNullMessage);
            request.getRequestDispatcher("/jsp/order/submitOrder.jsp").forward(request,response);
        }else if(buyerShipping == null || "".equals(buyerShipping)){
            request.setAttribute("notNullMessage",notNullMessage);
            request.getRequestDispatcher("/jsp/order/submitOrder.jsp").forward(request,response);
        }else if(buyerRemarks == null || "".equals(buyerRemarks)){
            request.setAttribute("notNullMessage",notNullMessage);
            request.getRequestDispatcher("/jsp/order/submitOrder.jsp").forward(request,response);
        }else if (buyerPhone == null || "".equals(buyerPhone)){
            request.setAttribute("notNullMessage",notNullMessage);
            request.getRequestDispatcher("/jsp/order/submitOrder.jsp").forward(request,response);
        }else {
            //获取下单日期
            Date date = new Date();
            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            Timestamp orderDate = Timestamp.valueOf(nowTime);
            System.out.println(orderDate);
            //获取订单编号
            String orderNumber = UUID.randomUUID().toString();
            System.out.println(orderNumber);
            //设置订单状态
            String orderState = "未付款";
            //通过用户id获得购买商品的列表
            ArrayList<CarShopping> list = AddShoppingDao.getBuyGoodsByUserId(buyerId);
            if(list!=null&&list.size()>0){
                for(int i=0;i<list.size();i++){
                    CarShopping carShopping = list.get(i);
                    String goodsName = carShopping.getGoodsName();
                    String goodsCity = carShopping.getGoodsCity();
                    double goodsPrice = carShopping.getGoodsPrice();
                    int buyNumber = carShopping.getBuyNumber();
                    int buyGoodsId = carShopping.getBuygoodsId();
                    double everyOrderPrice = OrderDao.everyOrderPriceByUserId(buyerId);
                    //Dao层向数据库添加数据
                    OrderDao.addMainOrder(buyerId,orderNumber,orderState,everyOrderPrice,orderDate,buyerName,buyerPhone,buyerAddress,buyerShipping,buyerRemarks);
                    OrderDao.addSubmitorder(buyerId,goodsName,goodsCity,goodsPrice,buyNumber,orderNumber,buyGoodsId);
                    //提交订单后减少商品库存数量
                    GoodsDao.updateGoodsNumber(buyNumber,buyGoodsId);
                }
            }
        }
        request.getRequestDispatcher("/OrderServlet").forward(request,response);
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
