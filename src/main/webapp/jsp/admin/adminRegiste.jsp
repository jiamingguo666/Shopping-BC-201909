<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/10/18
  Time: 17:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>管理员注册</title>

    <style type="text/css">
        html{overflow-y:scroll;vertical-align:baseline;}
        body{font-family:Microsoft YaHei,Segoe UI,Tahoma,Arial,Verdana,sans-serif;font-size:12px;color:#fff;height:100%;line-height:1;background:#999}
        *{margin:0;padding:0}
        ul,li{list-style:none}
        h1{font-size:30px;font-weight:700;text-shadow:0 1px 4px rgba(0,0,0,.2)}
        .login-box{width:410px;margin:120px auto 0 auto;text-align:center;padding:30px;background:url(//images/login/mask.png);border-radius:10px;}
        .login-box .name,.login-box .password,.login-box .code,.login-box .remember{font-size:16px;text-shadow:0 1px 2px rgba(0,0,0,.1)}
        .login-box .remember input{box-shadow:none;width:15px;height:15px;margin-top:25px}
        .login-box .remember{padding-left:40px}
        .login-box .remember label{display:inline-block;height:42px;width:70px;line-height:34px;text-align:left}
        .login-box label{display:inline-block;width:100px;text-align:right;vertical-align:middle}
        .login-box #code{width:120px}
        .login-box .codeImg{float:right;margin-top:26px;}
        .login-box img{width:148px;height:42px;border:none}
        input[type=text],input[type=password]{width:270px;height:42px;margin-top:25px;padding:0px 15px;border:1px solid rgba(255,255,255,.15);border-radius:6px;color:#fff;letter-spacing:2px;font-size:16px;background:transparent;}
        button{cursor:pointer;width:100%;height:44px;padding:0;background:#ef4300;border:1px solid #ff730e;border-radius:6px;font-weight:600;color:#fff;font-size:24px;letter-spacing:15px;text-shadow:0 1px 2px rgba(0,0,0,.1)}
        input:focus{outline:none;box-shadow:0 2px 3px 0 rgba(0,0,0,.1) inset,0 2px 7px 0 rgba(0,0,0,.2)}
        button:hover{box-shadow:0 15px 30px 0 rgba(255,255,255,.15) inset,0 2px 7px 0 rgba(0,0,0,.2)}
        .screenbg{position:fixed;bottom:0;left:0;z-index:-999;overflow:hidden;width:100%;height:100%;min-height:100%;}
        .screenbg ul li{display:block;list-style:none;position:fixed;overflow:hidden;top:0;left:0;width:100%;height:100%;z-index:-1000;float:right;}
        .screenbg ul a{left:0;top:0;width:100%;height:100%;display:inline-block;margin:0;padding:0;cursor:default;}
        .screenbg a img{vertical-align:middle;display:inline;border:none;display:block;list-style:none;position:fixed;overflow:hidden;top:0;left:0;width:100%;height:100%;z-index:-1000;float:right;}
        .bottom{margin:8px auto 0 auto;width:100%;position:fixed;text-align:center;bottom:0;left:0;overflow:hidden;padding-bottom:8px;color:#ccc;word-spacing:3px;zoom:1;}
        .bottom a{color:#FFF;text-decoration:none;}
        .bottom a:hover{text-decoration:underline;}
    </style>

    <script type="text/javascript" src="/demo9/js/jquery/2.0.0/jquery.min.js"></script>
    <script type="text/javascript">
        $(function(){
            $(".screenbg ul li").each(function(){
                $(this).css("opacity","0");
            });
            $(".screenbg ul li:first").css("opacity","1");
            var index = 0;
            var t;
            var li = $(".screenbg ul li");
            var number = li.size();
            function change(index){
                li.css("visibility","visible");
                li.eq(index).siblings().animate({opacity:0},3000);
                li.eq(index).animate({opacity:1},3000);
            }
            function show(){
                index = index + 1;
                if(!index>(number-1)){
                    change(index);
                }else{
                    index = 0;
                    change(index);
                }
            }
            t = setInterval(show,8000);
            //根据窗口宽度生成图片宽度
            var width = $(window).width();
            $(".screenbg ul img").css("width",width+"px");
        });
    </script>

</head>

<body>

<div class="login-box">
    <h1>商城管理员注册</h1>
    <form method="post" action="/demo9/adminRegisteServlet">
        <div class="adminName">
            <label>账     号：</label>
            <input type="text" name="adminName" id="adminName" tabindex="1" autocomplete="off" />
        </div>
        <div align="center" colspan="5" style="color: red">
                <%
                String errorNameMessage = (String) request.getAttribute("errorNameMessage");
                if(errorNameMessage!= null  && !"".equals(errorNameMessage)){
            %>
            <td ><% out.print(errorNameMessage);
            %></td>
                <%
                }
            %>
        </div>
        <div class="adminPassword">
            <label>密     码：</label>
            <input type="password" name="adminPassword" maxlength="16" id="adminPassword" tabindex="2"/>
        </div>
                    <div align="center" colspan="5" style="color: red">
                            <%
                String errorPasswordMessage = (String) request.getAttribute("errorPasswordMessage");
                if(errorPasswordMessage!= null  && !"".equals(errorPasswordMessage)){
            %>
                        <td ><% out.print(errorPasswordMessage);
                        %></td>
                            <%
                }
            %>
                    </div>
        <div class="adminConfirmPassword">
            <label>确认密码：</label>
            <input type="password" name="adminConfirmPassword" maxlength="16" id="adminConfirmPassword" tabindex="2"/>
        </div>
        <div align="center" colspan="5" style="color: red">
                <%
                String errorConfirmPasswordMessage = (String) request.getAttribute("errorConfirmPasswordMessage");
                if(errorConfirmPasswordMessage!= null  && !"".equals(errorConfirmPasswordMessage)){
            %>
            <td ><% out.print(errorConfirmPasswordMessage);
            %></td>
                <%
                }else{
            %>
                <%
                String errormessage = (String) request.getAttribute("errormessage");
                if(errormessage!= null  && !"".equals(errormessage)){
            %>
                    <td ><% out.print(errormessage);
                    %></td>
                        <%
                }
                }
            %>
        </div>
        <div class="adminProvince">
            <label>省     份：</label>
            <input type="text" name="adminProvince" id="adminProvince" tabindex="1" autocomplete="off" />
        </div>
                    <div align="center" colspan="5" style="color: red">
                                        <%
                String errorProvinceMessage = (String) request.getAttribute("errorProvinceMessage");
                if(errorProvinceMessage!= null  && !"".equals(errorProvinceMessage)){
            %>
                                    <td ><% out.print(errorProvinceMessage);
                                    %></td>
                                        <%
                }
            %>
                    </div>
        <div class="adminCity">
            <label>城     市：</label>
            <input type="text" name="adminCity" id="adminCity" tabindex="1" autocomplete="off" />
        </div>
        <div align="center" colspan="5" style="color: red">
            <%
                String errorCityMessage = (String) request.getAttribute("errorCityMessage");
                if(errorCityMessage!= null  && !"".equals(errorCityMessage)){
            %>
            <td ><% out.print(errorCityMessage);
            %></td>
            <%
                }
            %>
        </div>

        <div class="adminSex">
            <label>性    别：</label>
            <input type="text" name="gender" id="gender" tabindex="1" autocomplete="off" />
        </div>
        <div align="center" colspan="5" style="color: red">
            <%
                String errorGenderMessage = (String) request.getAttribute("errorGenderMessage");
                if(errorGenderMessage!= null  && !"".equals(errorGenderMessage)){
            %>
            <td ><% out.print(errorGenderMessage);
            %></td>
            <%
                }
            %>
        </div>
        <div class="adminAge">
            <label>年    龄：</label>
            <input type="text" name="adminAge" id="adminAge" tabindex="1" autocomplete="off" />
        </div>
        <div align="center" colspan="5" style="color: red">
            <%
                String errorAgeMessage = (String) request.getAttribute("errorAgeMessage");
                if(errorAgeMessage!= null  && !"".equals(errorAgeMessage)){
            %>
            <td ><% out.print(errorAgeMessage);
            %></td>
            <%
                }
            %>
        </div>
        <br/>
        <div class="registe">
            <button type="submit" tabindex="5">注册</button>
            <a tabindex="5" class="btn btn-link text-muted" onClick="goto_register()"href="/demo9/jsp/admin/adminLogin.jsp">登录</a>

        </div>
    </form>
</div>


<div class="screenbg">
    <ul>
        <li><a href="javascript:;"><img src="/demo9/images/login/0.jpg"></a></li>
        <li><a href="javascript:;"><img src="/demo9/images/login/1.jpg"></a></li>
        <li><a href="javascript:;"><img src="/demo9/images/login/2.jpg"></a></li>
    </ul>
</div>

</body>
</html>
