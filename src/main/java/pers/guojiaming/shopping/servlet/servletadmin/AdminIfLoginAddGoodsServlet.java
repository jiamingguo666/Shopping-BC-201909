package pers.guojiaming.shopping.servlet.servletadmin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 管理员若未登录，则不能发布商品。会返回登录页面
 * @Author: jiaming.guo
 * @Date: 2019/8/7 16:34
 */
@WebServlet("/AdminIfLoginAddGoodsServlet")
public class AdminIfLoginAddGoodsServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Object  id = session.getAttribute("adminId");
        if(id == null || "".equals(id)){
            request.getRequestDispatcher("/AdminFirstLogin").forward(request,response);
        }else{
            request.getRequestDispatcher("/jsp/admin/addgoods.jsp").forward(request,response);
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
