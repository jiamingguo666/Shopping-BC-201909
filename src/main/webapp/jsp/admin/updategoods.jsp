<%@ page import="pers.guojiaming.shopping.entity.Goods" %><%--
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
    <title>修改商品</title>
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
<div align="center">
    <h1 style="color: red;">商品信息修改</h1>
    <a href="/demo9/jsp/admin/homePage.jsp">返回</a>
    <form action="/demo9/adminUpdateGoodsServlet" method="post" enctype="multipart/form-data">
    <table width="500" border="1" align="center">
        <%
            String blankMessage = (String) request.getAttribute("blankMessage");
            if(blankMessage != null && !"".equals(blankMessage)){
        %>
        <script>
            alert('<%=blankMessage%>');
        </script>

        <%
            }
        %>

        <%
            Goods unitGoods = (Goods) session.getAttribute("unitGoods");
        %>

        <tr>
            <td><label for="name">商品名</label></td>
            <td><input type="text" name="name" value="<%=unitGoods.getName()%>" id="name"></td>
        </tr>
        <tr>
            <td><label for="city">生产城市</label></td>
            <td><input type="text" name="city" value="<%=unitGoods.getCity()%>" id="city"></td>
        </tr>
        <tr>
            <td><label for="price">价格</label></td>
            <td><input type="text" name="price" value="<%=unitGoods.getPrice()%>" id="price"></td>
        </tr>
        <tr>
            <td><label for="number">库存量</label></td>
            <td><input type="text" name="number" value="<%=unitGoods.getNumber()%>" id="number"></td>
        </tr>

        <tr>
            <td><label for="picture">商品图</label></td>
            <td><input type="file" name="picture" value="<%=unitGoods.getPicture()%>" id="picture"></td>
        </tr>

        <tr>
            <td colspan="2" align="center"><input type="submit" value="修改"></td>
        </tr>

    </table>
    </form>
</div>
</body>
</html>
