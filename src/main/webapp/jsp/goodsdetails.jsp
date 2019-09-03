<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="pers.guojiaming.shopping.dao.GoodsDao" %>
<%@ page import="pers.guojiaming.shopping.entity.Goods"  %>


<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
 <base href="<%=basePath%>">

    <title>商品详情</title>

    <meta http-equiv="pragma" content="no-cache">
    <meta http-equiv="cache-control" content="no-cache">
    <meta http-equiv="expires" content="0">
    <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
    <meta http-equiv="description" content="This is my page">


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
<h1>商品详情</h1>
<hr>
<center>
    <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <!-- 商品详情  -->
            <%
                GoodsDao goodsDao = new GoodsDao();
                Goods goods = goodsDao.getGoodsById(Integer.valueOf(request.getParameter("id")));
                if(goods!=null){
            %>
            <td width="70%" valign="top">
                <table>
                    <tr>
                        <td rowspan="5"><img src="/demo9/showImgServlet?imgPath=<%=goods.getPicture() %>" width="200" height="200"/></td>
                    </tr>
                    <tr>
                        <td><B><%=goods.getName() %></B></td>
                    </tr>
                    <tr>
                        <td>产地：<%=goods.getCity() %></td>
                    </tr>
                    <tr>
                        <td>库存量：<%=goods.getNumber() %></td>
                    </tr>
                    <tr>
                        <td>价格：<%=goods.getPrice() %></td>
                    </tr>
                    <tr>
                        <td colspan="5" align="center">
                            <a href="/demo9/jsp/goodsShow.jsp">返回</a>
                            <a href="/demo9/oneGoodsServlet?id=<%=goods.getId()%>">加入购物车</a>
                            <a href="/demo9/IfLoginMyCarShoppingServlet">我的购物车</a>
                        </td>
                    </tr>

                </table>
            </td>
            <%
                }
            %>
        </tr>
    </table>
</center>
</body>
</html>