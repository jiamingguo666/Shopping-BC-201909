<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="pers.guojiaming.shopping.entity.Goods" %>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <base href="<%=basePath%>">

    <title>加入购物车</title>

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
<h1>会员专属</h1>
<%
    String isNotMember = (String) request.getAttribute("isNotMember");
    if(isNotMember != null && !"".equals(isNotMember)){
%>
<script>
    alert('<%=isNotMember%>');
</script>

<%
    }
%>

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
<form  action="/demo9/MemberAddCarShoppingServlet" method="post">
    <center>
        <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
            <tr>

                <%
                    Goods goods= (Goods) session.getAttribute("goods");
                %>
                <td width="70%" valign="top">
                    <table>
                        <tr>
                            <td rowspan="6"><img src="/demo9/MemberShowImgServlet?imgPath=<%=goods.getPicture() %>" width="200" height="200"/></td>
                        </tr>
                        <tr>
                            <td><B><%=goods.getName() %></B></td>
                            <%
                                session.setAttribute("goodsName",goods.getName());
                            %>
                        </tr>
                        <tr>
                            <td>产地：<%=goods.getCity() %></td>
                            <%
                                session.setAttribute("goodsCity",goods.getCity());
                            %>
                        </tr>
                        <tr>
                            <td>库存量：<%=goods.getNumber() %></td>
                            <%
                                session.setAttribute("goodsNumber",goods.getNumber());
                            %>
                        </tr>
                        <tr>
                            <td>会员价：<%=goods.getMemberPrice() %></td>
                            <%
                                session.setAttribute("goodsMemberPrice",goods.getMemberPrice());
                            %>
                        </tr>
                        <tr>
                            <td><label for="buyNumber">购买数量</label></td>
                            <td><input type="number" name="buyNumber" placeholder="请输入购买的数量" id="buyNumber"></td>
                        </tr>
                        <tr hidden="right">
                            <td><label for="buyGoodsId">商品的id</label></td>
                            <td><input type="text" name="buyGoodsId" value="<%=goods.getId()%>" id="buyGoodsId"></td>
                        </tr>
                        <tr>
                            <td colspan="5" align="center">
                                <input type="submit" value="加入购物车">
                                <a href="/demo9/jsp/member/memberGoodsShow.jsp">返回</a>
                            </td>
                        </tr>




                    </table>
                </td>

            </tr>
        </table>
    </center>
</form>
</body>
</html>