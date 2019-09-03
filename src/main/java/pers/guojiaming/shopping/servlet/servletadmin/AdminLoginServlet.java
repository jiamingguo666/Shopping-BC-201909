package pers.guojiaming.shopping.servlet.servletadmin;
import pers.guojiaming.shopping.dao.AdminDao;
import pers.guojiaming.shopping.entity.CheckAdminLogin;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
/**
 * @Description: 管理员获取登录界面信息，并进行参数校验
 * @Author: jiaming.guo
 * @Date: 2019/8/8 16:34
 */
@WebServlet("/adminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        //获取登录界面获得的username，password
        String errorMessage = "用户名或则密码错误";
        String message = "您尚未注册，请先注册";
        String errorNameMessage = "名字不能为空";
        String errorPasswordMessage = "密码不能为空";
        String adminName = request.getParameter("adminName");
        String adminPassword = request.getParameter("adminPassword");
        if(adminName == null ||"".equals(adminName)){
            request.setAttribute("errorNameMessage",errorNameMessage);
            request.getRequestDispatcher("/jsp/admin/adminLogin.jsp").forward(request,response);
        }else if(adminPassword == null ||"".equals(adminPassword)){
            request.setAttribute("errorPasswordMessage",errorPasswordMessage);
            request.getRequestDispatcher("/jsp/admin/adminLogin.jsp").forward(request,response);
        }else {
             CheckAdminLogin checkAdminLoginDTO = AdminDao.checkLogin(adminName, adminPassword);
            HttpSession session = request.getSession();
            int adminId = checkAdminLoginDTO.getAdminId();
            if (adminId != 0) {
                session.setAttribute("adminId", adminId);
                //用户名和密码匹配，登录眺转到商品管理页面
                request.getRequestDispatcher("/jsp/admin/homePage.jsp").forward(request, response);
            } else {
                int one = 1;
                int two = 2;
                final int adminFlag = checkAdminLoginDTO.getAdminFlag();
                if (adminFlag == one) {
                    //用户名或则密码错误，重新登录
                    session.setAttribute("adminFlag", adminFlag);
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("/jsp/admin/adminLogin.jsp").forward(request, response);
                } else if (adminFlag == two) {
                    //用户不存在，先注册
                    session.setAttribute("adminFlag", adminFlag);
                    request.setAttribute("message", message);
                    request.getRequestDispatcher("/jsp/admin/adminLogin.jsp").forward(request, response);
                }
            }
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
