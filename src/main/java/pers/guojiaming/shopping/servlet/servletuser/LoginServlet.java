package pers.guojiaming.shopping.servlet.servletuser;
import pers.guojiaming.shopping.dao.UserDao;
import pers.guojiaming.shopping.entity.CheckLogin;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
/**
 * @Description: 获取登录界面的用户信息，并进行参数校验
 * @Author: jiaming.guo
 * @Date: 2019/8/3 16:34
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String errorMessage = "用户名或则密码错误";
        String message = "您尚未注册，请先注册";
        String errorNameMessage = "名字不能为空";
        String errorPasswordMessage = "密码不能为空";
        //获取登录界面获得的username，password
        String userName = request.getParameter("userName");
        String passwword = request.getParameter("password");
        if (userName == null || "".equals(userName)) {
            request.setAttribute("errorNameMessage", errorNameMessage);
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } else if (passwword == null || "".equals(passwword)) {
            request.setAttribute("errorPasswordMessage", errorPasswordMessage);
            request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
        } else {
            CheckLogin checkLoginResp = UserDao.checkLogin(userName, passwword);
            if (checkLoginResp.getResult()) {
            //获取登录界面用户的id 存在session中，以便根据该id获取用户的注册信息显示在个人资料里
                int id = UserDao.getUserIdByUserName(userName);
                //将id存在session中
                HttpSession session = request.getSession();
                session.setAttribute("id", id);
            //用户名和密码匹配，登录眺转到商品页面
            request.getRequestDispatcher("/jsp/goodsShow.jsp").forward(request, response);
        } else {
                int one = 1;
                int two = 2;
                final int flag = checkLoginResp.getFlag();
            if (flag == one) {
                HttpSession session = request.getSession();
                session.setAttribute("flag", flag);
                //用户名或则密码错误，重新登录
                request.setAttribute("errorMessage", errorMessage);
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            } else if (flag == two) {
                //用户不存在，先注册
                HttpSession session = request.getSession();
                session.setAttribute("flag", flag);
                request.setAttribute("message", message);
                request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
            }
        }
      }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
