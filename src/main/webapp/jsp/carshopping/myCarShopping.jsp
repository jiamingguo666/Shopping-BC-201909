<%@ page contentType="text/html;charset=UTF-8" language="java"  %>


<%@ page import="java.util.ArrayList" %>

<%@ page import="pers.guojiaming.shopping.dao.AddShoppingDao" %>
<%@ page import="pers.guojiaming.shopping.entity.CarShopping" %>
<%@ page import="pers.guojiaming.shopping.entity.Goods" %>
<%@ page import="java.util.List" %>

<%
    String path = request.getContextPath();

    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">
    <title>我的购物车</title>


</head>

<body>
<h1>我的购物车</h1>
<hr>
<%
    String numberMessage = (String) request.getAttribute("numberMessage");
    if(numberMessage != null && !"".equals(numberMessage)){
%>
<script>
    alert('<%=numberMessage%>');
</script>

<%
    }
%>


            <%   Object id = session.getAttribute("id");
                 if(id == null || id.equals("")){
                     %>
                  <a href="/demo9/FirstLogin"></a>
                 <%
                 }else {
                 int userId = (Integer) id;
                 %>
        <table border ="1" align = "center" style="border-collapse:collapse;">
            <tr align="center">
                <td colspan="6">
                    我的购物车
                </td>
            </tr>
            <tr align="center">
                <td>商品名</td>
                <td>商品出产地</td>
                <td>商品单价</td>
                <td>购买的数量</td>
                <td style="display:none;" id="hidetd">商品的ID</td>
                <td colspan="2">操作</td>
            </tr>
        <%


            AddShoppingDao addShoppingDao = new AddShoppingDao();
            double sumPrice = AddShoppingDao.sumPriceByUserId(userId);
            ArrayList<CarShopping> list = addShoppingDao.getBuyGoodsByUserId(userId);
            if(list!=null&&list.size()>0){
                for(int i=0;i<list.size();i++){
                    CarShopping carShopping = list.get(i);
                    %>

        <form action = "/demo9/myCarShoppingServlet" method = "post" >
        <tr align="center" >
            <td><%= carShopping.getGoodsName() %></td>
            <td><%= carShopping.getGoodsCity() %></td>
            <td><%= carShopping.getGoodsPrice() %></td>
            <td><input type="number" size="3" name="buyNumber" value="<%= carShopping.getBuyNumber() %>"></td>
            <td><input type="hidden" size="5" name="buyGoodsId" value="<%=carShopping.getBuygoodsId()%>"></td>
            <td><a href="/demo9/deleteCarShoppingGoodsServlet?goodsId=<%=carShopping.getGoodsId()%>">删除</a>
                <button type="submit">保存</button>
            </td>

        </tr>
        </form>
        <%
                }
            }

        %>
        <tr align="center">
            <td colspan="6">
                合计：<%=sumPrice%>元
            </td>
        </tr>


    </table>
    <table align="center">
        <tr>
            <td colspan="5" align="center">
            <td colspan="2"> <a href="/demo9/jsp/goodsShow.jsp ">返回</a></td>
            <td colspan="2"><a href="/demo9/jsp/order/submitOrder.jsp">结算</a></td>
            </td>
        </tr>
    </table>

    <%
        }
    %>

</body>
</html>
