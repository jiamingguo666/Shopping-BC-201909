<%@ page import="pers.guojiaming.shopping.entity.UserVo" %>
<%@ page import="pers.guojiaming.shopping.dao.UserDao" %>
<%@ page import="java.util.ArrayList" %>
<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/8/7
  Time: 15:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户个人信息</title>
</head>
<body>
<h1>个人资料</h1>
<hr>
<form name="myform" action="#" method="post">
<table border = "1" align = "center" style="border-collapse:collapse;">
   <% int id = (Integer)session.getAttribute("id");
    UserDao userDao = new UserDao();
    UserVo user = userDao.selectOneUserInfo(id);
%>
    <tr>
        <td colspan="2" align="center">个人信息
         <a href="/demo9/jsp/updateUserView.jsp">修改</a> </td>
    </tr>
    <tr>
        <td>用户名</td>
        <td><%= user.getUsername() %></td>
    </tr>
    <tr>
        <td>地 址</td>
        <td><%= user.getProvince() %>省 <%= user.getCity() %>市</td>
    </tr>
    <tr>
        <td>性 别</td>
        <td><%= user.getSex() %></td>

    </tr>
    <tr>
        <td>年龄</td>
        <td><%= user.getAge() %></td>

    </tr>
    <tr>
        <td  align="center" colspan="2"><a href="/demo9/jsp/my.jsp">返回</a></td>
    </tr>

</table>
</form>
</body>
</html>
