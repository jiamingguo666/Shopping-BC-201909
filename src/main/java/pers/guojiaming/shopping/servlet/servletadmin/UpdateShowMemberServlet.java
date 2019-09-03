package pers.guojiaming.shopping.servlet.servletadmin;

import pers.guojiaming.shopping.dao.MemberDao;
import pers.guojiaming.shopping.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


/**
 * @Description: 管理员修改会员用户资料前先将会员资料展示出来
 * @Author: jiaming.guo
 * @Date: 2019/8/16 16:34
 */
@WebServlet("/UpdateShowMemberServlet")
public class UpdateShowMemberServlet extends HttpServlet {
   @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String  memberId = request.getParameter("memberId");
        MemberDao memberDao = new MemberDao();
        Member member = memberDao.selectOneMemberInfo(Integer.parseInt(memberId));
        HttpSession session = request.getSession();
        session.setAttribute("memberId",memberId);
        session.setAttribute("member",member);
        request.getRequestDispatcher("/jsp/admin/updateMembers.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
