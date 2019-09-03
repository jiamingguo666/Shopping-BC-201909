package pers.guojiaming.shopping.servlet.servletuser;

import pers.guojiaming.shopping.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 查看个人资料前先判断是否登录。没有则返回登录界面
 * @Author: jiaming.guo
 * @Date: 2019/8/2 16:34
 */
@WebServlet("/IfMyServlet")
public class IfMyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object id = session.getAttribute("id");
        if(id == null || "".equals(id) || UserDao.checkIfIdExist((Integer)id) == 0){
            request.getRequestDispatcher("/FirstLogin").forward(request,response);
        }else{
            request.getRequestDispatcher("/jsp/my.jsp").forward(request,response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   this.doPost(request,response);
    }
}
