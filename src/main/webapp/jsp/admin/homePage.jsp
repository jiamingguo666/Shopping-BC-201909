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
    </style>
</head>

<body style="text-align: center">
<h1 align="left">普通商品</h1>
<tr>

        <font size="5"><a href="/demo9/jsp/admin/homePage.jsp">首页</a> </font>
        <font size="5"><a href="/demo9/jsp/admin/memberGoodsPage.jsp">会员</a></font>
        <font size="5"><a href="/demo9/AdminIfLoginAddGoodsServlet">发布新产品</a></font>
        <font size="5"><a href="/demo9/IfLoginUsersInformationServlet">买家信息</a></font>
        <font size="5"><a href="/demo9/AdminIfLoginOrderServlet">订单信息</a></font>
        <font size="5"><a href="/demo9/AdminIfLoginMyServlet">我的</a></font>
        <td align="right"><a href="/demo9/AdminFirstLogin">退出</a></td>
</tr>
<hr>

<center>
    <table width="750" height="60" cellpadding="0" cellspacing="0" border="0">
        <tr>
            <td>

                <!-- 商品循环开始  -->
                <%
                    GoodsDao goodsDao = new GoodsDao();
                    ArrayList<Goods> list = goodsDao.getAllGoods();
                    if(list!=null&&list.size()>0){
                        for(int i=list.size()-1;i>=0;i--){
                            Goods goods = list.get(i);
                %>
                <div>
                    <dl>
                        <dt>
                           <img src="/demo9/showImgServlet?imgPath=<%=goods.getPicture() %>" width="120" height="120"></a>

                        </dt>
                        <dd class="dd_name"><%=goods.getName() %></dd>
                        <dd class="dd_city">产地：<%=goods.getCity() %>&nbsp;&nbsp;价格：<%=goods.getPrice() %></dd>
                        <dd><a href="/demo9/updateShowGoodsServlet?id=<%=goods.getId()%>">修改</a>
                            <a href="/demo9/adminDeleteGoodsServlet?goodsId=<%=goods.getId()%>">删除</a></dd>
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
                <a href="/demo9/jsp/admin/homePage.jsp">首页</a>
                <a href="/demo9/jsp/admin/memberGoodsPage.jsp">会员</a>
                <a href="/demo9/AdminIfLoginAddGoodsServlet">发布新产品</a>
                <a href="/demo9/IfLoginUsersInformationServlet">买家信息</a>
                <a href="/demo9/AdminIfLoginOrderServlet">订单信息</a>
                <a href="/demo9/AdminIfLoginMyServlet">我的</a>
            </td>

        </tr>
    </table>
</center>
</body>
</html>