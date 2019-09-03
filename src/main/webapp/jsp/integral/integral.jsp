<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/8/5
  Time: 16:50
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
    <title>积分兑换</title>
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
    String integralMessage = (String) request.getAttribute("integralMessage");
    if(integralMessage!= null  && !"".equals(integralMessage)){
%>
<script>
    alert('<%=integralMessage%> ');
</script>

<%
    }
%>
<%
    String notEnoughIntegral = (String) request.getAttribute("notEnoughIntegral");
    if(notEnoughIntegral!= null  && !"".equals(notEnoughIntegral)){
%>
<script>
    alert('<%=notEnoughIntegral%> ');
</script>
<%
    }
%>

<div align="center">
    <h1 style="color: red;">积分兑换</h1>
    <a href="/demo9/jsp/my.jsp">返回</a>
    <form action="/demo9/IntegralChangeMoneyServlet" method="post">
        <table width="500" border="1" align="center">
            <td><input type="radio" name="integral" value="300">300G币/300积分
                <input type="radio" name="integral" value="500">500G币/500积分
                <input type="radio" name="integral" value="900">900G币/900积分
            </td>
            <tr>
                <td colspan="2" align="center"><input type="submit" value="兑换"></td>
            </tr>

        </table>
    </form>
</div>
</body>
</html>
