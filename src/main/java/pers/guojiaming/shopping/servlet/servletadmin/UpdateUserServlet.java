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
 * @Description: 管理员修改用户资料
 * @Author: jiaming.guo
 * @Date: 2019/8/17 16:34
 */
@WebServlet("/adminUpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String message = "输入不能为空";
        //从修改用户界面获得的用户信息
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("id");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String province = request.getParameter("province");
        String city = request.getParameter("city");
        String sex= request.getParameter("sex");
        String age = request.getParameter("age");
        String money = request.getParameter("money");
        if(userName == null || "".equals(userName)){
            request.setAttribute("message",message);
            request.getRequestDispatcher("/jsp/admin/updateUsers.jsp").forward(request,response);
        }else if(password == null || "".equals(password)){
            request.setAttribute("message",message);
            request.getRequestDispatcher("/jsp/admin/updateUsers.jsp").forward(request,response);
        }else if(province == null || "".equals(province)){
            request.setAttribute("message",message);
            request.getRequestDispatcher("/jsp/admin/updateUsers.jsp").forward(request,response);
        }else if(city == null || "".equals(city)){
            request.setAttribute("message",message);
            request.getRequestDispatcher("/jsp/admin/updateUsers.jsp").forward(request,response);
        }else if(sex == null || "".equals(sex)){
            request.setAttribute("message",message);
            request.getRequestDispatcher("/jsp/admin/updateUsers.jsp").forward(request,response);
        }else if(age == null || "".equals(age)){
            request.setAttribute("message",message);
            request.getRequestDispatcher("/jsp/admin/updateUsers.jsp").forward(request,response);
        }else if(money == null || "".equals(money)){
            request.setAttribute("message",message);
            request.getRequestDispatcher("/jsp/admin/updateUsers.jsp").forward(request,response);
        } else{
            UserVo user=new UserVo();
            // 将获得的用户信息存到对象user中
            user.setId(Integer.parseInt(id));
            user.setUsername(userName);
            user.setPassword(password);
            user.setProvince(province);
            user.setCity(city);
            user.setSex(sex);
            user.setAge(Integer.parseInt(age));
            user.setMoney(Double.parseDouble(money));
            //判断是否修改成功，成功则眺转到所有用户页面，不成功则眺转到修改用户页面
            if(UserDao.adminUpdateUsers(user)){
                request.getRequestDispatcher("/jsp/admin/allUserInformation.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("/jsp/admin/updateUsers.jsp").forward(request,response);
            }
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
