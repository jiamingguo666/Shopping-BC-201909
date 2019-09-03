<%@ page import="pers.guojiaming.shopping.dao.UserDao" %>
<%@ page import="pers.guojiaming.shopping.entity.UserVo" %>
<%@ page import="pers.guojiaming.shopping.dao.AdminDao" %>
<%@ page import="pers.guojiaming.shopping.entity.Admin" %>
<%@ page import="pers.guojiaming.shopping.dao.OrderDao" %><%--
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
<h1>管理员个人中心</h1>
<hr>
<table border = "1" align = "center" style="border-collapse:collapse;">
    <%
        //从登录界面获得用户的id
        int adminId = (Integer) session.getAttribute("adminId");
        AdminDao adminDao = new AdminDao();
        Admin admin = adminDao.selectOneSellerInfo(adminId);
        String orderState = "交易完成";
        double sumPrice = OrderDao.sumPriceByOrderState(orderState);
    %>
    <tr>
        <td style="color: red;" colspan="2" width="100" align="center">个人中心
            <a href="/demo9/jsp/admin/homePage.jsp">返回</a></td>
    </tr>
    <tr>
        <td> <a href="/demo9/jsp/admin/sellerView.jsp"><img src="/demo9/showHeadPictureServlet?imgPath=<%=admin.getHeadPicture() %>" width="170" height="90"></a></td>
    </tr>
    <tr>
        <td>交易总额：<%=sumPrice%>元</td>
    </tr>
    <tr>
        <td><a href="/demo9/AdminLogoutServlet?adminId=<%=adminId%>">注销账号</a></td>
    </tr>
    <tr>
        <td colspan="2" align="center"><a href="/demo9/AdminFirstLogin">退出登录</a></td>
    </tr>
</table>

</body>
</html>
