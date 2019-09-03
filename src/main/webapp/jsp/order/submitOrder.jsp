<%@ page import="pers.guojiaming.shopping.dao.AddShoppingDao" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pers.guojiaming.shopping.entity.CarShopping" %>
<%--
  Created by IntelliJ IDEA.
  User: EDZ
  Date: 2019/8/12
  Time: 16:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>结算</title>
</head>
<body>
<%
    String notNullMessage = (String) request.getAttribute("notNullMessage");
    if(notNullMessage != null && !"".equals(notNullMessage)){
%>
<script>
    alert('<%=notNullMessage%>');
</script>

<%
    }
%>




<td align="left">确认订单</td>
<form  action="/demo9/submitOrderServlet" method="post">
    <table width="500" border="1" align="center">
        <%
            //从session中获取用户的user_id
            int userId = (Integer) session.getAttribute("id");
            AddShoppingDao addShoppingDao = new AddShoppingDao();
            double sumPrice = AddShoppingDao.sumPriceByUserId(userId);
            ArrayList<CarShopping> list = addShoppingDao.getBuyGoodsByUserId(userId);
            if(list!=null&&list.size()>0){
                for(int i=0;i<list.size();i++){
                   CarShopping carShopping = list.get(i);
        %>
        <tr>
            <td>商品名</td>
            <td><%= carShopping.getGoodsName() %></td>
        </tr>
        <tr>
            <td>商品出产地</td>
            <td><%= carShopping.getGoodsCity()%></td>
        </tr>
        <tr>
            <td>商品单价</td>
            <td><%= carShopping.getGoodsPrice() %></td>
        </tr>
        <tr>
            <td>购买的数量</td>
            <td><%= carShopping.getBuyNumber() %></td>
        </tr>
        <%
                }
            }
        %>
        <tr>
            <td><label for="buyerName">收货人</label></td>
            <td><input type="text" name="buyerName" placeholder="请输入收货人姓名" id="buyerName"></td>
        </tr>
        <tr>
            <td><label for="buyerPhone">手机号码</label></td>
            <td><input type="number" name="buyerPhone" placeholder="请输入收货人手机号" id="buyerPhone"></td>
        </tr>
        <tr>
            <td><label for="buyerAddress">收获地址</label></td>
            <td><input type="text" name="buyerAddress" placeholder="请输入收获地址" id="buyerAddress"></td>
        </tr>
        <tr>
            <td>配送方式</td>
            <td><input type="radio" name="buyerShipping" value="普通配送（免邮）"> 普通配送（免邮）
                <input type="radio" name="buyerShipping" value="快速配送（自费）"> 快速配送（自费）</td>
        </tr>
        <tr>
            <td><label for="buyerRemarks">订单备注</label></td>
            <td><input type="text" name="buyerRemarks" placeholder="选填，请先和商家协商一致" id="buyerRemarks"></td>
        </tr>
        <tr>
            <td colspan="5" align="center">
                合计：<%=sumPrice%>元
            </td>
        </tr>
        <tr>
            <td colspan="5" align="center">
                <a href="/demo9/jsp/carshopping/myCarShopping.jsp">返回</a>
                <input type="submit" value="提交订单">
            </td>
        </tr>

    </table>

</form>
</body>
</html>
