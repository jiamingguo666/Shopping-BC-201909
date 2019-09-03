package pers.guojiaming.shopping.servlet.servletaddcarshopping;

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
 * @Description: 用户我的购物车资料，并进行数量修改
 * @Author: jiaming.guo
 * @Date: 2019/8/19 16:34
 */
@WebServlet("/myCarShoppingServlet")
public class MyCarShoppingServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        int surplusNumber = 0;
        String numberMessage = "购买数量需在库存范围内且大于0";
        //从登录界面获得用户的id
        HttpSession session = request.getSession();
        int userId = (Integer) session.getAttribute("id");
        // 从我的购物车界面获得数据
        int buyGoodsId = Integer.parseInt(request.getParameter("buyGoodsId"));
        String buyNumber = request.getParameter("buyNumber");
        if("".equals(buyNumber)){
            request.setAttribute("numberMessage",numberMessage);
            request.getRequestDispatcher("/jsp/carshopping/myCarShopping.jsp").forward(request,response);
        }else{
            int goodsNumber = Integer.parseInt(request.getParameter("buyNumber"));
            //判断输入的购买数量是否合理
            surplusNumber = GoodsDao.buyNumberCompareGoodsNumber(buyGoodsId, goodsNumber);
            //如果库存量不足或则购买数量小于等于0，则返回提示
            if(surplusNumber < 0 || goodsNumber <= 0){
                request.setAttribute("numberMessage",numberMessage);
                request.getRequestDispatcher("/jsp/carshopping/myCarShopping.jsp").forward(request,response);
            }else{
                //输入数量合理，则更新
                AddShoppingDao.updateCarShoppingNumber(userId, buyGoodsId, goodsNumber);
            }
        }
        request.getRequestDispatcher("/jsp/carshopping/myCarShopping.jsp").forward(request, response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
