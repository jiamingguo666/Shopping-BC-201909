package pers.guojiaming.shopping.servlet.servletadmin;

import pers.guojiaming.shopping.dao.MemberDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * @Description: 管理员删除会员
 * @Author: jiaming.guo
 * @Date: 2019/8/11 16:34
 */
@WebServlet("/DeleteMemberServlet")
public class DeleteMemberServlet extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String  memberId = request.getParameter("memberId");
        MemberDao memberDao = new MemberDao();
        //memberDao层向数据库删除数据,判断是否删除成功，成功则眺转到所有会员页面，不成功则眺转到删除会员页面
        if(MemberDao.deleteByMemberId(Integer.parseInt(memberId))){
            request.getRequestDispatcher("/jsp/admin/allMembersInformation.jsp").forward(request,response);
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
