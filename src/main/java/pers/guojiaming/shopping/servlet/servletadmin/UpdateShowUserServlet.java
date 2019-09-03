package pers.guojiaming.shopping.servlet.servletadmin;

import pers.guojiaming.shopping.dao.UserDao;
import pers.guojiaming.shopping.entity.UserVo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 管理员修改用户资料前先将用户资料展示出来
 * @Author: jiaming.guo
 * @Date: 2019/8/19 16:34
 */
@WebServlet("/adminUpdateShowUserServlet")
public class UpdateShowUserServlet extends HttpServlet {
 @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
         //设置编码
        request.setCharacterEncoding("UTF-8");
        String  id = request.getParameter("id");
        UserDao userDao = new UserDao();
        UserVo user = userDao.selectOneUserInfo(Integer.parseInt(id));
        HttpSession session = request.getSession();
        session.setAttribute("id",id);
        session.setAttribute("user",user);
        request.getRequestDispatcher("/jsp/admin/updateUsers.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
