<%@ page contentType="text/html;charset=UTF-8" language="java"  %>
<%@ page import="pers.guojiaming.shopping.dao.GoodsDao "%>
<%@ page import="pers.guojiaming.shopping.entity.Goods" %>
<%@ page import="java.util.ArrayList" %>

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
        div dd.dd_member{
            color: red;
        }
    </style>
</head>

<body style="text-align: center">
<h1 align="left">会员专属</h1>
<tr>
    <font size="5"> <a href="/demo9/GoodsShow">首页</a></font>
    <font size="5"> <a href="/demo9/IfLoginOrderServlet">订单</a></font>
    <font size="5"> <a href="/demo9/IfLoginMyCarShoppingServlet">购物车</a></font>
    <font size="5"> <a href="/demo9/IfMyServlet">我的</a></font>
    <td align="right"><a href="/demo9/FirstLogin">退出</a></td>

</tr>

<hr>

<center>
    <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>

                <!-- 商品循环开始  -->
                <%
                    GoodsDao goodsDao = new GoodsDao();
                    ArrayList<Goods> list = goodsDao.getAllMemberGoods();
                    if(list!=null&&list.size()>0){
                        for(int i=list.size()-1;i>=0;i--){
                            Goods goods = list.get(i);
                %>
                <div>
                    <dl>
                        <dt>
                            <a href="jsp/member/memberGoodsDetails.jsp?id=<%=goods.getId() %>"><img src="/demo9/MemberShowImgServlet?imgPath=<%=goods.getPicture() %>" width="120" height="120"></a>

                        </dt>
                        <dd class="dd_name"><%=goods.getName() %></dd>
                        <dd class="dd_city">产地：<%=goods.getCity() %>&nbsp;&nbsp;原价：<%=goods.getPrice() %></dd>
                        <dd class="dd_member">会员价：<%=goods.getMemberPrice() %>&nbsp;&nbsp;优惠：<%=goods.getDiscountPrice() %></dd>
                    </dl>
                </div>
                <!-- 商品循环结束  -->
                <%
                        }
                    }
                %>

            </td>
        </tr>
        <tr>
            <td colspan="5" align="center">
                <a href="/demo9/GoodsShow">首页</a>
                <a href="/demo9/IfLoginOrderServlet">订单</a>
                <a href="/demo9/IfLoginMyCarShoppingServlet">购物车</a>
                <a href="/demo9/IfMyServlet">我的</a>
            </td>

        </tr>
    </table>
</center>
</body>
</html>