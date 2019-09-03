<%@ page import="pers.guojiaming.shopping.entity.UserVo" %>
<%@ page import="pers.guojiaming.shopping.dao.UserDao" %>
<%@ page import="java.util.ArrayList" %><%--
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
<h1>个人资料修改</h1>
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
<form name="myform" action="/demo9/updateUserViewServlet" method="post" enctype="multipart/form-data">
    <table border = "1" align = "center" style="border-collapse:collapse;">
        <% int id = (Integer)session.getAttribute("id");
            UserDao userDao = new UserDao();
            UserVo user = userDao.selectOneUserInfo(id);
        %>

        <tr>
            <td colspan="2" align="center">个人信息
                <input type="submit" value="保存">
            </td>
        </tr>
        <tr>
            <td><label for="userName">用户名</label></td>
            <td><input type="text" size="6" name="userName" value="<%= user.getUsername() %>" id="userName"></td>
        </tr>
        <tr>
            <td><label for="password">用户密码</label></td>
            <td><input type="password"size="6" name="password" value="<%= user.getPassword() %>" id="password"></td>
        </tr>
        <tr>
            <td>地址</td>
            <td><input type="text" size="6" name="province" value="<%= user.getProvince() %>">省
                <input type="text" size="6" name="city" value="<%= user.getCity() %>">市</td>
        </tr>
        <tr>
            <td><label for="sex">性别</label></td>
            <td><input type="text" size="6" name="sex" value="<%= user.getSex() %>" id="sex"></td>
        </tr>
        <tr>
            <td><label for="age">年龄</label></td>
            <td><input type="number" style="width: 72px" name="age" value="<%= user.getAge() %>" id="age"></td>
        </tr>
        <tr>
            <td><label for="headPicture">头像</label></td>
            <td><input type="file" size="6" name="headPicture" placeholder="请上传头像" id="headPicture"></td>
        </tr>
        <tr>
            <td  align="center" colspan="2"><a href="/demo9/jsp/userview.jsp">返回</a></td>
        </tr>

    </table>
</form>
</body>
</html>
