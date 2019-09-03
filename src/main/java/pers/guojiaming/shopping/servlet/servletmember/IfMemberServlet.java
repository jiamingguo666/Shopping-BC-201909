package pers.guojiaming.shopping.servlet.servletmember;

import pers.guojiaming.shopping.dao.MemberDao;
import pers.guojiaming.shopping.entity.Member;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * @Description: 判断用户是否是会员
 * @Author: jiaming.guo
 * @Date: 2019/8/19 1434
 */
@WebServlet("/IfMemberServlet")
public class IfMemberServlet extends HttpServlet {
  @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        String isMember = "你已经是会员";
        //从前端获得用户ID
        int  userId = Integer.parseInt(request.getParameter("userId"));
        int memberId = MemberDao.checkIfMember(userId);
        //未注册或则会员过期，则跳转到注册会员页面
        if(memberId == 0){
            System.out.println("未注册会员");
            request.getRequestDispatcher("/jsp/member/memberRegister.jsp").forward(request,response);
           }else{
            //获得当前日期
            Date date = new Date();
            String nowTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            Timestamp nowDate = Timestamp.valueOf(nowTime);
            //获得会员到期日期
            MemberDao memberDao = new MemberDao();
            Member member = memberDao.selectOneMemberInfo(userId);
            Timestamp deadline = member.getDeadline();
            //判断会员是否到期
            int memberSurplusTime = deadline.compareTo(nowDate);
            //如果会员到期，则将其从会员数据表删除，并跳转到注册页面重新注册，否则跳转到个人中心页面
            if(memberSurplusTime <0){
                //根据会员memberId,将其从会员列表数据表删除
                if(MemberDao.deleteByMemberId(memberId)){
                    request.getRequestDispatcher("/jsp/member/memberRegister.jsp").forward(request,response);
                }
            }else{
                request.setAttribute("isMember",isMember);
                request.getRequestDispatcher("/jsp/my.jsp").forward(request,response);
            }
        }
  }
@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      this.doPost(request,response);
    }
}
