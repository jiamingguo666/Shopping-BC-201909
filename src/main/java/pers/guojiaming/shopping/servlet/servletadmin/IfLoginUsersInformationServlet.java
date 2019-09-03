package pers.guojiaming.shopping.servlet.servletadmin;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 管理员进入用户资料前判断是否登录，未登录则返回登录界面
 * @Author: jiaming.guo
 * @Date: 2019/8/12 16:34
 */
@WebServlet("/IfLoginUsersInformationServlet")
public class IfLoginUsersInformationServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //获取管理员id
        HttpSession session = request.getSession();
        Object  id = session.getAttribute("adminId");
        if(id == null || "".equals(id)){
            request.getRequestDispatcher("/AdminFirstLogin").forward(request,response);
        }else{
            request.getRequestDispatcher("/jsp/admin/allUserInformation.jsp").forward(request,response);
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
