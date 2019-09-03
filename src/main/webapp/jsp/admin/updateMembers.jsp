
<%@ page import="java.util.ArrayList" %>
<%@ page import="pers.guojiaming.shopping.entity.Member" %>
<%@ page import="pers.guojiaming.shopping.entity.UserVo" %><%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/8/5
  Time: 15:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();

    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>修改会员信息</title>
    <style>
        .a{
            margin-top: 20px;
        }
        .b{
            font-size: 20px;
            width: 160px;
            color: white;
            background-color: greenyellow;
        }
    </style>
</head>
<body>
<%
    String message = (String) request.getAttribute("message");
    if(message!= null  && !"".equals(message)){
%>
<script>
    alert('<%=message%> ');
</script>

<%
    }
%>
<div align="center">
    <h1 style="color: red;">会员信息修改</h1>
    <a href="/demo9/jsp/admin/allMembersInformation.jsp">返回</a>
    <form action="/demo9/UpdateMemberServlet" method="post">
        <table width="500" border="1" align="center" style="border-collapse:collapse;">
            <%
                Member member = (Member) session.getAttribute("member");
            %>

            <tr>
                <td><label for="startDate">注册时间</label></td>
                <td><input type="text" name="startDate" value="<%= member.getStartDate() %>" id="startDate"></td>
            </tr>
            <tr>
                <td><label for="deadline">到期时间</label></td>
                <td><input type="text" name="deadline" value="<%= member.getDeadline() %>" id="deadline"></td>
            </tr>


            <tr>
                <td colspan="2" align="center"><input type="submit" value="修改"></td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>
