<%@ page import="pers.guojiaming.shopping.entity.UserVo" %>
<%@ page import="pers.guojiaming.shopping.dao.UserDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pers.guojiaming.shopping.dao.AdminDao" %>
<%@ page import="pers.guojiaming.shopping.entity.Admin" %><%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/8/7
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人信息</title>
</head>
<body>
<h1>个人资料</h1>
<hr>
<form name="myform" action="#" method="post">
    <table border = "1" align = "center" width="250" style="border-collapse:collapse;">
        <% int adminId = (Integer) session.getAttribute("adminId");
            AdminDao adminDao = new AdminDao();
            Admin admin = adminDao.selectOneSellerInfo(adminId);
        %>
        <tr>
            <td colspan="2" align="center">个人信息
                <a href="/demo9/jsp/admin/updateSellerView.jsp">修改</a> </td>
        </tr>
        <tr>
            <td>用户名</td>
            <td><%= admin.getAdminName() %></td>
        </tr>
        <tr>
            <td>地 址</td>
            <td><%= admin.getAdminProvince()%>省 <%= admin.getAdminCity() %>市</td>
        </tr>
        <tr>
            <td>性 别</td>
            <td><%= admin.getAdminSex() %></td>

        </tr>
        <tr>
            <td>年龄</td>
            <td><%= admin.getAdminAge() %></td>

        </tr>
        <tr>
            <td  align="center" colspan="2"><a href="/demo9/jsp/admin/adminMy.jsp">返回</a></td>
        </tr>

    </table>
</form>
</body>
</html>
