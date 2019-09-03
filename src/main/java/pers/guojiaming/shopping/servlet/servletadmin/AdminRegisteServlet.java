package pers.guojiaming.shopping.servlet.servletadmin;

import pers.guojiaming.shopping.dao.AdminDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: 管理员获取注册界面信息，并将数据存进数据库
 * @Author: jiaming.guo
 * @Date: 2019/8/19 16:34
 */
@WebServlet("/adminRegisteServlet")
public class AdminRegisteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码格式
        request.setCharacterEncoding("UTF-8");
        String errormessage = "两次密码输入不相同";
        String errorNameMessage = "名字不能为空";
        String errorPasswordMessage = "密码不能为空";
        String errorConfirmPasswordMessage = "确认密码不能为空";
        String errorAgeMessage = "年龄不能为空";
        String errorProvinceMessage = "省份不能为空";
        String errorCityMessage = "城市不能为空";
        String errorGenderMessage = "性别不能为空";
        //从注册界面获得username，password
        String adminName = request.getParameter("adminName");
        String adminPassword = request.getParameter("adminPassword");
        String confirmPassword = request.getParameter("adminConfirmPassword");
        String adminAge = request.getParameter("adminAge");
        String adminProvince = request.getParameter("adminProvince");
        String adminCity = request.getParameter("adminCity");
        String gender = request.getParameter("gender");
        if(adminName == null || "".equals(adminName)){
            request.setAttribute("errorNameMessage",errorNameMessage);
            request.getRequestDispatcher("/jsp/admin/adminRegiste.jsp").forward(request,response);
        }else if(adminPassword == null || "".equals(adminPassword)){
            request.setAttribute("errorPasswordMessage",errorPasswordMessage);
            request.getRequestDispatcher("/jsp/admin/adminRegiste.jsp").forward(request,response);
        }else if(confirmPassword == null || "".equals(confirmPassword)){
            request.setAttribute("errorConfirmPasswordMessage",errorConfirmPasswordMessage);
            request.getRequestDispatcher("/jsp/admin/adminRegiste.jsp").forward(request,response);
        }else if(adminAge == null ||"".equals(adminAge)){
            request.setAttribute("errorAgeMessage",errorAgeMessage);
            request.getRequestDispatcher("/jsp/admin/adminRegiste.jsp").forward(request,response);
        }else if(adminProvince == null || "".equals(adminProvince)){
            request.setAttribute("errorProvinceMessage",errorProvinceMessage);
            request.getRequestDispatcher("/jsp/admin/adminRegiste.jsp").forward(request,response);
        }else if(adminCity == null || "".equals(adminCity)){
            request.setAttribute("errorCityMessage",errorCityMessage);
            request.getRequestDispatcher("/jsp/admin/adminRegiste.jsp").forward(request,response);
        }else if(gender == null || "".equals(gender)){
            request.setAttribute("errorGenderMessage",errorGenderMessage);
            request.getRequestDispatcher("/jsp/admin/adminRegiste.jsp").forward(request,response);
        }else{
            if(adminPassword.equals(confirmPassword)){
                String headPictureUrl ="/demo9/images/headImage/1.jpg";
                String fileName = headPictureUrl.substring(headPictureUrl.lastIndexOf("/")+1);
                String headPicture = fileName;
                //Dao层向数据库添加数据
                AdminDao.registe(adminName,adminPassword,adminProvince,adminCity,gender,adminAge,headPicture);
                request.getRequestDispatcher("/jsp/admin/adminLogin.jsp").forward(request,response);
            }else{
                request.setAttribute("errormessage",errormessage);
                request.getRequestDispatcher("/jsp/admin/adminRegiste.jsp").forward(request,response);

            }
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
