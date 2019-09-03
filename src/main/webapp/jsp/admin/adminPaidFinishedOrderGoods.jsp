<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ page import="pers.guojiaming.shopping.dao.GoodsDao "%>
<%@ page import="pers.guojiaming.shopping.entity.Goods" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pers.guojiaming.shopping.dao.OrderDao" %>
<%@ page import="pers.guojiaming.shopping.entity.Order" %>

<%
    String path = request.getContextPath();

    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>订单详情</title>
    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">
    <link rel="stylesheet" type="text/css" href="styles.css">
    <style type="text/css">
        div{
            float: left;
            margin: 10px;
        }
        div dd{
            margin: 0px;
            font-size: 10px;
        }
        div dd.dd_name{
            color: blue;
        }
        div dd.dd_city{
            color: #000;
        }
    </style>
</head>

<body>

<center>
    <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>
                <%
                    OrderDao orderDao = new OrderDao();
                    ArrayList<Order> goodsList = orderDao.getAllGoodsByOrderNumber(request.getParameter("orderNumber"));
                    if(goodsList!=null && goodsList.size()>0){
                        for(int i = 0;i<goodsList.size();i++){
                            Order order = goodsList.get(i);
                %>
                <table border = "1" align = "center" width="250" style="border-collapse:collapse;">
                    <tr>
                        <td>商品名</td>
                        <td><%= order.getGoodsName() %></td>
                    </tr>
                    <tr>
                        <td>产地</td>
                        <td><%= order.getGoodsCity() %></td>
                    </tr>
                    <tr>
                        <td>单价</td>
                        <td><%= order.getGoodsPrice()%></td>
                    </tr>
                    <tr>
                        <td>数量</td>
                        <td><%= order.getBuyNumber()%></td>
                    </tr>
                </table>
                <br/>
                <%
                        }
                    }
                %>

            </td>
        </tr>
        <tr>
            <td colspan="6" align="center">
                <a href="/demo9/jsp/admin/adminPaidFinishedOrder.jsp">返回</a>
            </td>

        </tr>
    </table>
</center>
</body>
</html>