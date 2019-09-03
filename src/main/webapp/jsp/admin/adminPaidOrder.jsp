<%@ page contentType="text/html;charset=UTF-8" language="java"  %>

<%@ page import="java.util.ArrayList" %>
<%@ page import="pers.guojiaming.shopping.dao.OrderDao" %>
<%@ page import="pers.guojiaming.shopping.entity.MainOrder" %>

<%
    String path = request.getContextPath();

    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>goodsshow.jsp' starting page</title>
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
<h1>待发货</h1>
<hr>

<center>
    <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>
                <%
                    //订单状态为已支付
                    String orderState = "已支付";
                    OrderDao orderDao = new OrderDao();
                    //根据订单状态获取订单的编号
                    ArrayList<String>orderNumberList = OrderDao.getAllOrderNumbersByOrderState(orderState);
                    if(orderNumberList != null && orderNumberList.size()>0){
                        for(int i = orderNumberList.size()-1;i>=0;i--){
                            MainOrder mainOrder = orderDao.getReceiverByOrderNumber(orderNumberList.get(i));
                %>
                <table border = "1" align = "center"width="600" style="border-collapse:collapse;">
                    <tr align="center">
                        <td colspan="6"> <a href="jsp/admin/payOrderGoods.jsp?orderNumber=<%=mainOrder.getOrderNumber()%>">订单详情</a></td>
                    </tr>
                    <tr>
                        <td colspan="3">订单编号</td>
                        <td colspan="3"><%= mainOrder.getOrderNumber() %></td>
                    </tr>
                    <tr>
                        <td>收货人</td>
                        <td><%=mainOrder.getBuyerName() %></td>
                        <td>收获地址</td>
                        <td><%=mainOrder.getBuyerAddress() %></td>
                        <td>联系方式</td>
                        <td><%=mainOrder.getBuyerPhone()%></td>
                    </tr>
                    </tr>
                    <td>订单价格</td>
                    <td><%=mainOrder.getOrderMoney() %></td>
                    <td>下单时间</td>
                    <td><%=mainOrder.getOrderDate() %></td>
                    <td>订单状态</td>
                    <td><%=mainOrder.getOrderState()%></td>
                    </tr>
                    <tr align="center">
                        <td colspan="6"><a href="/demo9/AdminDeliverGoodsServlet?orderNumber=<%=mainOrder.getOrderNumber()%>">发货</a></td>
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
                <a href="/demo9/jsp/admin/adminUnpaidOrder.jsp">返回</a>

            </td>

        </tr>
    </table>
</center>
</body>
</html>