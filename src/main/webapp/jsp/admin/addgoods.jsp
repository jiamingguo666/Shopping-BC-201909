<%--
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
    <title>添加商品</title>
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
<h1>普通商品发布</h1>
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
<div align="center">
    <h1 style="color: red;">发布普通商品</h1>
    <a href="/demo9/jsp/admin/homePage.jsp">返回</a>
    <form action="/demo9/adminAddGoodsServlet" method="post" enctype="multipart/form-data">
        <table width="500" border="1" align="center">
            <tr>
                <td><label for="name">商品名称</label></td>
                <td><input type="text" name="name" placeholder="请输入你的商品名称" id="name"></td>
            </tr>
            <tr>
                <td><label for="city">生产城市</label></td>
                <td><input type="text" name="city" placeholder="请输入你的商品生产城市" id="city"></td>
            </tr>
            <tr>
                <td><label for="price">价格</label></td>
                <td><input type="number" name="price" placeholder="请输入你的商品单价" id="price"></td>
            </tr>
            <tr>
                <td><label for="number">库存量</label></td>
                <td><input type="number" name="number" placeholder="请输入你的商品库存量" id="number"></td>
            </tr>
            <tr>
                <td>商品类别</td>
                <td><input type="radio" name="category" value="ordinary_goods">普通商品
                </td>
            </tr>
            <tr>
                <td><label for="picture">商品图片</label></td>
                <td><input type="file" name="picture" placeholder="请插入你的商品图片" id="picture"></td>
            </tr>

            <tr>
                <td colspan="2" align="center"><input type="submit" value="发布"></td>
            </tr>

        </table>
        <tr align="center" style="color: red">
            <a href="/demo9/jsp/admin/addMemerGoods.jsp">发布会员商品</a>
        </tr>
    </form>
</div>
</body>
</html>
