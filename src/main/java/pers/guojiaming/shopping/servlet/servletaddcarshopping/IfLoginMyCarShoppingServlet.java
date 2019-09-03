package pers.guojiaming.shopping.servlet.servletaddcarshopping;

import pers.guojiaming.shopping.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 用户进入我的购物车前判断是否登录，未登录则返回登录界面
 * @Author: jiaming.guo
 * @Date: 2019/8/19 16:34
 */
@WebServlet("/IfLoginMyCarShoppingServlet")
public class IfLoginMyCarShoppingServlet extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Object id = session.getAttribute("id");
        if(id == null || "".equals(id) || UserDao.checkIfIdExist((Integer)id) == 0){
            request.getRequestDispatcher("/FirstLogin").forward(request,response);
        }else{
            request.getRequestDispatcher("/jsp/carshopping/myCarShopping.jsp").forward(request,response);
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
