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
    <title>修改个人信息</title>
</head>
<body>
<h1>管理员个人资料修改</h1>
<hr>
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
<form name="myform" action="/demo9/UpdateSellerViewServlet" method="post" enctype="multipart/form-data">
    <table border = "1" align = "center"  style="border-collapse:collapse;">
        <% int adminId = (Integer) session.getAttribute("adminId");
            AdminDao adminDao = new AdminDao();
            Admin admin = adminDao.selectOneSellerInfo(adminId);
        %>

        <tr>
            <td colspan="2" align="center">个人信息
                <input type="submit" value="保存">
            </td>
        </tr>
        <tr>
            <td><label for="adminName">用户名</label></td>
            <td><input type="text" size="6" name="adminName" value="<%= admin.getAdminName() %>" id="adminName"></td>
        </tr>
        <tr>
            <td><label for="adminPassword">用户密码</label></td>
            <td><input type="password" size="6" name="adminPassword" value="<%= admin.getAdminPassword() %>" id="adminPassword"></td>
        </tr>
        <tr>
            <td>地址</td>
            <td><input type="text" size="6" name="adminProvince" value="<%= admin.getAdminProvince() %>">省
                <input type="text" size="6" name="adminCity" value="<%= admin.getAdminCity() %>">市</td>
        </tr>
        <tr>
            <td><label for="adminSex">性别</label></td>
            <td><input type="text" size="6" name="adminSex" value="<%= admin.getAdminSex() %>" id="adminSex"></td>
        </tr>
        <tr>
            <td><label for="adminAge">年龄</label></td>
            <td><input type="number" style="width: 72px" name="adminAge" value="<%= admin.getAdminAge() %>" id="adminAge"></td>
        </tr>
        <tr>
            <td><label for="headPicture">头像</label></td>
            <td><input type="file" size="6" name="headPicture" placeholder="请上传头像" id="headPicture"></td>
        </tr>
        <tr>
            <td  align="center" colspan="2"><a href="/demo9/jsp/admin/sellerView.jsp">返回</a></td>
        </tr>

    </table>
</form>
</body>
</html>
