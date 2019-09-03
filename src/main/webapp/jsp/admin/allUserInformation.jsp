<%@ page contentType="text/html;charset=UTF-8" language="java"  %>


<%@ page import="java.util.ArrayList" %>
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
    <title>买家用户信息</title>


</head>

<body>
<h1>普通买家信息</h1>
<hr>
<form action = "#" method = "post" >
    <table border ="1" align = "center" style="border-collapse:collapse;">
        <tr align="center">
            <td colspan="8">
                用户信息列表
            </td>
        </tr>
        <tr align="center">
            <td>用户名</td>
            <td>地址</td>
            <td>性别</td>
            <td>年龄</td>
            <td>G币</td>
            <td>积分</td>
            <td colspan="2">操作</td>
        </tr>


                <%
                    UserDao userDao = new UserDao();
                    ArrayList<UserVo> list = userDao.getAllUsers();
                    if(list!=null&&list.size()>0){
                        for(int i=0;i<list.size();i++){
                            UserVo user = list.get(i);
                            double integral1 = user.getIntegral();
                            int integral = (new Double(integral1)).intValue();
                %>

        <tr align="center" >
            <td><%= user.getUsername() %></td>
            <td><%= user.getProvince() %>省 <%= user.getCity() %>市</td>
            <td><%= user.getSex() %></td>
            <td><%= user.getAge() %></td>
            <td><%= user.getMoney()%></td>
            <td><%= integral%></td>
            <td><a href="/demo9/adminUpdateShowUserServlet?id=<%=user.getId()%>">修改</a></td>
            <td><a href="/demo9/adminDeleteUserServlet?id=<%=user.getId()%>">删除</a></td>
        </tr>
                <%
                        }
                    }
                %>
    </table>
        <table align="center">
        <tr>
            <td><a href="/demo9/jsp/admin/homePage.jsp">返回</a></td>
            <td><a href="/demo9/jsp/admin/allMembersInformation.jsp">会员</a> </td>
        </tr>
        </table>


</form>
</body>
</html>
