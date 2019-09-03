<%@ page contentType="text/html;charset=UTF-8" language="java"  %>


<%@ page import="java.util.ArrayList" %>
<%@ page import="pers.guojiaming.shopping.dao.MemberDao" %>
<%@ page import="pers.guojiaming.shopping.entity.Member" %>
<%@ page import="pers.guojiaming.shopping.dao.UserDao" %>
<%@ page import="pers.guojiaming.shopping.entity.UserVo" %>


<%
    String path = request.getContextPath();

    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>会员用户信息</title>


</head>

<body>
<h1>会员买家信息</h1>
<hr>
<form action = "#" method = "post" >
    <table border ="1" align = "center" style="border-collapse:collapse;">
        <tr align="center">
            <td colspan="7">
                会员信息列表
            </td>
        </tr>
        <tr align="center">
            <td>用户名</td>
            <td>会员名</td>
            <td>会员联系方式</td>
            <td>注册时间</td>
            <td>到期时间</td>
            <td colspan="2">操作</td>
        </tr>


        <%
            MemberDao memberDao =new MemberDao();
            ArrayList<Member> list = memberDao.getAllMembers();
            if(list!=null&&list.size()>0){
                for(int i=0;i<list.size();i++){
                    Member member = list.get(i);
                    int userId = member.getMemberId();
                    UserDao userDao = new UserDao();
                    UserVo userVo = userDao.selectOneUserInfo(userId);
        %>

        <tr align="center" >
            <td><%= userVo.getUsername() %></td>
            <td><%= member.getMemberName() %>
            <td><%= member.getMemberPhone() %></td>
            <td><%= member.getStartDate() %></td>
            <td><%= member.getDeadline()%></td>
            <td><a href="/demo9/UpdateShowMemberServlet?memberId=<%=member.getMemberId()%>">修改</a></td>
            <td><a href="/demo9/DeleteMemberServlet?memberId=<%=member.getMemberId()%>">删除</a></td>
        </tr>
        <%
                }
            }
        %>
    </table>
    <table align="center">
        <tr>
            <td><a href="/demo9/jsp/admin/allUserInformation.jsp">返回</a></td>
        </tr>
    </table>


</form>
</body>
</html>
