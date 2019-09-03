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
import java.sql.Timestamp;


/**
 * @Description: 管理员修改会员信息
 * @Author: jiaming.guo
 * @Date: 2019/8/19 16:34
 */
@WebServlet("/UpdateMemberServlet")
public class UpdateMemberServlet extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String message = "输入不能为空";
        //从修改会员界面获得的会员信息
        HttpSession session = request.getSession();
        String memberId = (String) session.getAttribute("memberId");
        String startDate1 =request.getParameter("startDate");
        String deadline1 = request.getParameter("deadline");
        if(startDate1 == null || "".equals(startDate1)){
            request.setAttribute("message",message);
            request.getRequestDispatcher("/jsp/admin/updateMembers.jsp").forward(request,response);
        }else if(deadline1 == null || "".equals(deadline1)){
            request.setAttribute("message",message);
            request.getRequestDispatcher("/jsp/admin/updateMembers.jsp").forward(request,response);
        }else{
            Timestamp startDate = Timestamp.valueOf(startDate1);
            Timestamp deadline = Timestamp.valueOf(deadline1);
            Member member = new Member();
            // 将获得的会员信息存到对象user中
            member.setMemberId(Integer.parseInt(memberId));
            member.setStartDate(startDate);
            member.setDeadline(deadline);
            //判断是否修改成功，成功则眺转到所有会员页面，不成功则眺转到修改会员页面
            if(MemberDao.updateMember(member)){
                request.getRequestDispatcher("/jsp/admin/allMembersInformation.jsp").forward(request,response);
            }else{
                request.getRequestDispatcher("/jsp/admin/updateMembers.jsp").forward(request,response);
            }
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
