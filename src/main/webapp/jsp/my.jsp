<%@ page import="pers.guojiaming.shopping.dao.UserDao" %>
<%@ page import="pers.guojiaming.shopping.entity.UserVo" %>
<%@ page import="pers.guojiaming.shopping.dao.MemberDao" %>
<%@ page import="pers.guojiaming.shopping.entity.Member" %>
<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/8/8
  Time: 11:02
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的</title>
</head>
<body>
<h1>个人中心</h1>
<hr>
<table border = "1" align = "center" style="border-collapse:collapse;">


    <%
        //从登录界面获得用户的id
        Object id = session.getAttribute("id");
        if(id == null || "".equals(id)){
    %>
    <a href="/demo9/FirstLogin"></a>
    <%
    }else {
        int userId = (Integer) id;
        UserDao userDao = new UserDao();
        UserVo userVo = userDao.selectOneUserInfo(userId);
        double integral1 = userVo.getIntegral();
        int integral = (new Double(integral1)).intValue();
        MemberDao memberDao = new MemberDao();
        Member member = memberDao.selectOneMemberInfo(userId);
         String isMember = (String) request.getAttribute("isMember");
        if(isMember != null && !"".equals(isMember)){

            %>
    <script>
        alert('<%=isMember%> 会员将在<%=member.getDeadline()%>到期 ');
       </script>
    <%
        }
    %>

<tr>
    <td style="color: red;" colspan="2" align="center">个人中心
    <a href="/demo9/jsp/goodsShow.jsp">返回</a></td>
</tr>
    <tr>
        <td> <a href="/demo9/jsp/userview.jsp"><img src="/demo9/showHeadPictureServlet?imgPath=<%=userVo.getHeadPicture() %>" width="110" height="90"></a></td>
    </tr>
    <tr style="color: red">
        <td>钱包
        <%=userVo.getMoney()%>G币</td>
    </tr>
    <tr>
        <td><a href="/demo9/IfMemberServlet?userId=<%=userId%>">会员</a>
        <%
        //如果用户为会员，则显示会员名字
                int memberId = MemberDao.checkIfMember(userId);
                if(memberId != 0){
        %>
                <%=member.getMemberName()%>
                <%
                }
                %>

    </tr>
    <tr>
        <td><a href="/demo9/jsp/integral/integral.jsp">积分</a>
        <%=integral%></td>
    </tr>

    <tr>
        <td><a href="/demo9/logoutServlet?id=<%=userId%>">注销账号</a></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><a href="/demo9/FirstLogin">退出登录</a></td>
    </tr>

    <%
        }
    %>
</table>

</body>
</html>
