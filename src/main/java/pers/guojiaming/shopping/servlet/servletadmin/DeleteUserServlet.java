package pers.guojiaming.shopping.servlet.servletadmin;

import pers.guojiaming.shopping.dao.UserDao;
import pers.guojiaming.shopping.dao.MemberDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: 管理员删除用户
 * @Author: jiaming.guo
 * @Date: 2019/8/19 16:34
 */
@WebServlet( "/adminDeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String  id = request.getParameter("id");
        UserDao userDao = new UserDao();
        //GoodsDao层向数据库删除数据,判断是否删除成功，成功则眺转到所有用户页面，不成功则眺转到删除用户页面
        if(UserDao.deleteById(Integer.parseInt(id))){
            //删除用户时，判断该用户是否在会员表里，有则一起删除
            int userId = Integer.parseInt(id);
            int memberId = MemberDao.checkIfMember(userId);
            //该用户在会员表里
            if(memberId != 0){
                //将其从会员表里删除
                if(MemberDao.deleteByMemberId(memberId)){
                    request.getRequestDispatcher("/jsp/admin/allUserInformation.jsp").forward(request,response);
                }
            }else {
                //该用户不是会员，直接跳转到所有用户列表
                request.getRequestDispatcher("/jsp/admin/allUserInformation.jsp").forward(request,response);
            }
        }
    }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
   this.doPost(request,response);
    }
}
