package pers.guojiaming.shopping.servlet.servletadmin;

import pers.guojiaming.shopping.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: 管理员注销账户
 * @Author: jiaming.guo
 * @Date: 2019/8/10 16:34
 */
@WebServlet("/AdminLogoutServlet")
public class AdminLogoutServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String  adminId = request.getParameter("adminId");
        AdminDao adminDao = new AdminDao();
        //adminDao层向数据库删除数据,判断是否删除成功，成功则眺转到登录页面
        if(AdminDao.deleteByAdminId(Integer.parseInt(adminId))){
            request.getSession().invalidate();
            request.getRequestDispatcher("/jsp/admin/adminLogin.jsp").forward(request,response);
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
