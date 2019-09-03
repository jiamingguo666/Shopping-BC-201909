package pers.guojiaming.shopping.servlet.servletintegral;

import pers.guojiaming.shopping.dao.IntegralDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 积分兑换G币
 * @Author: jiaming.guo
 * @Date: 2019/8/20 10:34
 */
@WebServlet("/IntegralChangeMoneyServlet")
public class IntegralChangeMoneyServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String integralMessage = "兑换数量不能为空";
        String notEnoughIntegral = "积分不足!完成交易的金额越大，所获积分越多！";
        //从登录界面获得用户的id
        HttpSession session = request.getSession();
        Object id= session.getAttribute("id");
        if(id != null && !"".equals(id)) {
            int userId = (Integer) id;
            //从兑换页面获取信息
            String changeIntegral = request.getParameter("integral");
            if(changeIntegral == null || "".equals(changeIntegral)){
                request.setAttribute("integralMessage",integralMessage);
                request.getRequestDispatcher("/jsp/integral/integral.jsp").forward(request,response);
            }else{
                double integralChangeMoney = Double.parseDouble(changeIntegral);
                double surplusIntegral = IntegralDao.integralCompareIntegralChangeMoney(userId,integralChangeMoney);
                if(surplusIntegral<0){
                    request.setAttribute("notEnoughIntegral",notEnoughIntegral);
                    request.getRequestDispatcher("/jsp/integral/integral.jsp").forward(request,response);
                }else{
                    //先扣除积分
                    if(IntegralDao.updateUserSurplusIntegral(surplusIntegral,userId)){
                        //扣除积分后，将积分添加到钱包里
                        IntegralDao.updateUserMoney(integralChangeMoney,userId);
                        request.getRequestDispatcher("/jsp/integral/integral.jsp").forward(request,response);
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
