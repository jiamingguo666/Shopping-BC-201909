package pers.guojiaming.shopping.servlet.servletuser;
import pers.guojiaming.shopping.dao.UserDao;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * @Description: 从注册界面获取用户注册信息，并存进数据库
 * @Author: jiaming.guo
 * @Date: 2019/8/3 16:34
 */
@WebServlet("/registe")
public class RegisteServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
           //设置编码格式
            request.setCharacterEncoding("UTF-8");
            String errorMessage = "两次密码输入不相同";
            String errorNameMessage = "名字不能为空";
            String errorPasswordMessage = "密码不能为空";
            String errorConfirmPasswordMessage = "确认密码不能为空";
            String errorAgeMessage = "年龄不能为空";
            String errorProvinceMessage = "省份不能为空";
            String errorCityMessage = "城市不能为空";
            String errorGenderMessage = "性别不能为空";
            //从注册界面获得username，password
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String confirmPassword = request.getParameter("confirmPassword");
            String age = request.getParameter("age");
            String province = request.getParameter("province");
            String city = request.getParameter("city");
            String gender = request.getParameter("gender");
            if(userName == null || "".equals(userName)){
                request.setAttribute("errorNameMessage",errorNameMessage);
                request.getRequestDispatcher("jsp/registe.jsp").forward(request,response);
            }else if(password == null || "".equals(password)){
                request.setAttribute("errorPasswordMessage",errorPasswordMessage);
                request.getRequestDispatcher("jsp/registe.jsp").forward(request,response);
            }else if(confirmPassword == null || "".equals(confirmPassword)){
                request.setAttribute("errorConfirmPasswordMessage",errorConfirmPasswordMessage);
                request.getRequestDispatcher("jsp/registe.jsp").forward(request,response);
            }else if(age == null ||"".equals(age)){
                request.setAttribute("errorAgeMessage",errorAgeMessage);
                request.getRequestDispatcher("jsp/registe.jsp").forward(request,response);
            }else if(province == null || "".equals(province)){
                request.setAttribute("errorProvinceMessage",errorProvinceMessage);
                request.getRequestDispatcher("jsp/registe.jsp").forward(request,response);
            }else if(city == null || "".equals(city)){
                request.setAttribute("errorCityMessage",errorCityMessage);
                request.getRequestDispatcher("jsp/registe.jsp").forward(request,response);
            }else if(gender == null || "".equals(gender)){
                request.setAttribute("errorGenderMessage",errorGenderMessage);
                request.getRequestDispatcher("jsp/registe.jsp").forward(request,response);
            }else{
                if(confirmPassword.equals(password)){
                    //Dao层向数据库添加数据
                    String headPictureUrl ="/demo9/images/headImage/1.jpg";
                    String fileName = headPictureUrl.substring(headPictureUrl.lastIndexOf("/")+1);
                    String headPicture = fileName;
                    UserDao.registe(userName,password,province,city,gender,age,headPicture);
                    request.getRequestDispatcher("/jsp/login.jsp").forward(request,response);
                }else {
                    request.setAttribute("errorMessage", errorMessage);
                    request.getRequestDispatcher("jsp/registe.jsp").forward(request,response);
                }

            }




    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     this.doPost(request,response);
    }
}
