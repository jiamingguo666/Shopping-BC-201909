<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 2018/10/19
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" import="java.util.*"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>用户登录</title>
    <link rel="stylesheet" href="/demo9/css/fore/normalize.css">
    <link rel="stylesheet" href="/demo9/css/fore/login.css">
    <link rel="stylesheet" href="/demo9/css/fore/sign-up-login.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/demo9/css/fore/inputEffect.css" />
    <link rel="stylesheet" href="/demo9/css/fore/tooltips.css" />
    <link rel="stylesheet" href="/demo9/css/fore/spop.min.css" />
    <link rel="stylesheet" href="/demo9/css/bootstrap/3.3.6/bootstrap.css" />

    <script src="/demo9/js/jquery/2.0.0/jquery.min.js"></script>
    <script src="/demo9/js/snow.js"></script>
    <script src="/demo9/js/jquery.pure.tooltips.js"></script>
    <script src="/demo9/js/spop.min.js"></script>
    <script>
        $(function() {
            $('#login #password').focus(function() {
                $('.login-owl').addClass('password');
            }).blur(function() {
                $('.login-owl').removeClass('password');
            });


        });



        function goto_login(){
            $("#username").val("");
            $("#password").val("");
            $("#tab-1").prop("checked",true);
        }

        function login(){//登录
            var value = $("#username").val();
            if (value.length==0){
                alert("用户名不能为空!")
                $("#username")[0].focus();
                return;
            }
            var value1 = $("#password").val();
            if (value1.length==0){
                alert('密码不能为空')
                $("#password")[0].focus();
                return;
            }
            <c:if test="${!empty message}">
            alert('${message}')
            </c:if>
        }


    </script>
    <style type="text/css">
        html{width: 100%; height: 100%;}
        body{
            background-repeat: no-repeat;
            background-position: center center #2D0F0F;
            background-color: #00BDDC;/demo9/5476ba25Ne75aa640.png
        owl-login.png
        owl-login@2x.png
        SANGSHUIY.jpg
        snow.jpg
        background-image: url(/demo9/images/snow.jpg);
            background-size: 100% 100%;
        }
        .snow-container { position: fixed; top: 0; left: 0; width: 100%; height: 100%; pointer-events: none; z-index: 100001; }
    </style>
</head>
<body>
<%
    String errorNameMessage = (String) request.getAttribute("errorNameMessage");
    if(errorNameMessage!= null  && !"".equals(errorNameMessage)){
%>
<script>
    alert('<%=errorNameMessage%> ');
</script>

<%
    }
%>
<%
    String errorPasswordMessage = (String) request.getAttribute("errorPasswordMessage");
    if(errorPasswordMessage!= null  && !"".equals(errorPasswordMessage)){
%>
<script>
    alert('<%=errorPasswordMessage%> ');
</script>

<%
    }
%>
<%
    String errorConfirmPasswordMessage = (String) request.getAttribute("errorConfirmPasswordMessage");
    if(errorConfirmPasswordMessage!= null  && !"".equals(errorConfirmPasswordMessage)){
%>
<script>
    alert('<%=errorConfirmPasswordMessage%> ');
</script>

<%
    }
%>
<%
    String errorAgeMessage = (String) request.getAttribute("errorAgeMessage");
    if(errorAgeMessage!= null  && !"".equals(errorAgeMessage)){
%>
<script>
    alert('<%=errorAgeMessage%> ');
</script>

<%
    }
%>
<%
    String errorProvinceMessage = (String) request.getAttribute("errorProvinceMessage");
    if(errorProvinceMessage!= null  && !"".equals(errorProvinceMessage)){
%>
<script>
    alert('<%=errorProvinceMessage%> ');
</script>

<%
    }
%>
<%
    String errorCityMessage = (String) request.getAttribute("errorCityMessage");
    if(errorCityMessage!= null  && !"".equals(errorCityMessage)){
%>
<script>
    alert('<%=errorCityMessage%> ');
</script>

<%
    }
%>
<%
    String errorGenderMessage = (String) request.getAttribute("errorGenderMessage");
    if(errorGenderMessage!= null  && !"".equals(errorGenderMessage)){
%>
<script>
    alert('<%=errorGenderMessage%> ');
</script>

<%
    }
%>





<!-- 雪花背景 -->
<div class="snow-container"></div>
<!-- 登录控件 -->
<div id="login">
    <input id="tab-1" type="radio" name="tab" class="sign-in hidden" checked />
    <input id="tab-2" type="radio" name="tab" class="sign-up hidden" />
    <input id="tab-3" type="radio" name="tab" class="sign-out hidden" />
    <div class="wrapper">
        <!-- 登录页面 -->
        <div class="login sign-in-htm">
            <form action="/demo9/registe" method="post" class="container offset1 loginform">
                <!-- 猫头鹰控件 -->
                <div id="owl-login" class="login-owl">
                    <div class="hand"></div>
                    <div class="hand hand-r"></div>
                    <div class="arms">
                        <div class="arm"></div>
                        <div class="arm arm-r"></div>
                    </div>
                </div>

                <div class="pad input-container">
                    <section class="content">
							<span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="userName"
                                       autocomplete="off" placeholder="请输入用户名" tabindex="1" name="userName" />
								<label class="input__label input__label--hideo" for="userName">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="password" name="password" placeholder="请输入密码" tabindex="2" maxlength="15"/>
								<label class="input__label input__label--hideo" for="password">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="password" id="confirmPassword" name="confirmPassword" placeholder="请确认密码" tabindex="2" maxlength="15"/>
								<label class="input__label input__label--hideo" for="confirmPassword">
									<i class="fa fa-fw fa-lock icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>

                        <span align="center" colspan="5" style="color: red">
                             <%
                                     String errorMessage = (String) request.getAttribute("errorMessage");
                                     if( errorMessage!= null  && !"".equals(errorMessage)){
                             %>
                                 <td ><% out.print(errorMessage);
                                 %></td>

                            <%
                            }
                            %>
                        </span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="province"
                                       autocomplete="off" placeholder="请输入你的省份" tabindex="1" name="province" />
								<label class="input__label input__label--hideo" for="province">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="city"
                                       autocomplete="off" placeholder="请输入你的城市" tabindex="1" name="city" />
								<label class="input__label input__label--hideo" for="city">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="gender"
                                       autocomplete="off" placeholder="请输入你的性别" tabindex="1" name="gender" />
								<label class="input__label input__label--hideo" for="city">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="text" id="age"
                                       autocomplete="off" placeholder="请输入你的年龄" tabindex="1" name="age" />
								<label class="input__label input__label--hideo" for="city">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>

                        <span class="form-actions" align="center" colspan="5" >
                            <input class="btn btn-primary" type="submit" tabindex="3"value="注册"
                                   style="color:white;"/>
                            <a tabindex="5" class="btn btn-link text-muted" onClick="goto_register()"href="/demo9/jsp/login.jsp">登录</a>

                        </span>
                    </section>
                </div>
            </form>
        </div>

        </form>
    </div>
</div>
</div>
</body>
</html>