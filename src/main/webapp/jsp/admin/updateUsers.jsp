<%@ page import="pers.guojiaming.shopping.dao.UserDao" %>
<%@ page import="pers.guojiaming.shopping.entity.UserVo" %>
<%@ page import="java.util.ArrayList" %><%--
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
    <title>修改用户信息</title>
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
    <h1 style="color: red;">用户信息修改</h1>
    <a href="/demo9/jsp/admin/allUserInformation.jsp">返回</a>
    <form action="/demo9/adminUpdateUserServlet" method="post">
        <table width="500" border="1" align="center" style="border-collapse:collapse;">
            <%
                UserVo user= (UserVo) session.getAttribute("user");
            %>
            <tr>
                <td><label for="userName">用户名</label></td>
                <td><input type="text" name="userName" value="<%= user.getUsername() %>" id="userName"></td>
            </tr>
            <tr>
                <td><label for="password">用户密码</label></td>
                <td><input type="password" name="password" value="<%= user.getPassword() %>" id="password"></td>
            </tr>
            <tr>
                <td><label for="province">用户省份</label></td>
                <td><input type="text" name="province" value="<%= user.getProvince() %>" id="province"></td>
            </tr>
            <tr>
                <td><label for="city">用户城市</label></td>
                <td><input type="text" name="city" value="<%= user.getCity() %>" id="city"></td>
            </tr>
            <tr>
                <td><label for="sex">用户性别</label></td>
                <td><input type="text" name="sex" value="<%= user.getSex() %>" id="sex"></td>
            </tr>
            <tr>
                <td><label for="age">用户年龄</label></td>
                <td><input type="number" name="age" value="<%= user.getAge() %>" id="age"></td>
            </tr>
            <tr>
                <td><label for = "money">G币</label></td>
                <td><input type="number" name="money" value="<%= user.getMoney()%>"id="money"></td>
            </tr>


            <tr>
                <td colspan="2" align="center"><input type="submit" value="修改"></td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>
