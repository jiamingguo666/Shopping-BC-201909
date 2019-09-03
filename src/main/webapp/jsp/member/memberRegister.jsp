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

<!-- 雪花背景 -->
<div class="snow-container"></div>
<!-- 注册控件 -->
<div id="login">
    <input id="tab-1" type="radio" name="tab" class="sign-in hidden" checked />
    <input id="tab-2" type="radio" name="tab" class="sign-up hidden" />
    <input id="tab-3" type="radio" name="tab" class="sign-out hidden" />
    <div class="wrapper">
        <!-- 注册页面 -->
        <div class="login sign-in-htm">
            <form action="/demo9/MemberRegisterServlet" method="post" class="container offset1 loginform">
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
								<input class="input__field input__field--hideo" type="text" id="memberName"
                                       autocomplete="off" placeholder="请设置会员名" tabindex="1" name="memberName" />
								<label class="input__label input__label--hideo" for="memberName">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span align="center" colspan="5" style="color: red">
                             <%
                                 String errorNameMessage = (String) request.getAttribute("errorNameMessage");
                                 if(errorNameMessage!= null  && !"".equals(errorNameMessage)){
                             %>
                                 <td ><% out.print(errorNameMessage);
                                 %></td>

                            <%
                                }
                            %>
                        </span>
                        <span class="input input--hideo">
								<input class="input__field input__field--hideo" type="number" id="memberPhone" name="memberPhone" placeholder="请输入你的联系方式" tabindex="2" maxlength="15"/>
								<label class="input__label input__label--hideo" for="memberPhone">
									<i class="fa fa-fw fa-user icon icon--hideo"></i>
									<span class="input__label-content input__label-content--hideo"></span>
								</label>
							</span>
                        <span align="center" colspan="5" style="color: red">
                             <%
                                 String errorPhoneMessage = (String) request.getAttribute("errorPhoneMessage");
                                 if(errorPhoneMessage!= null  && !"".equals(errorPhoneMessage)){
                             %>
                                 <td ><% out.print(errorPhoneMessage);
                                 %></td>

                            <%
                                }
                            %>
                        </span>

                        <span class="input input--hideo">
								<td><input type="radio" name="memberPrice" value="300">300G币/3月
                                    <input type="radio" name="memberPrice" value="500">500G币/6月
                                    <input type="radio" name="memberPrice" value="900">900G币/一年
                                </td>

							</span>
                        <span align="center" colspan="5" style="color: red">
                             <%
                                 Object money1 = session.getAttribute("money");
                                 if(money1 != null && !money1.equals("")){
                                     double money = (double) money1;
                                 if (money == 0.0){
                                 String errorPriceMessage = (String) request.getAttribute("errorPriceMessage");
                                 if(errorPriceMessage !=null && !"".equals(errorPriceMessage))
                             %>
                                 <td ><% out.print(errorPriceMessage);
                                 %></td>

                            <%
                                }else{
                                     String notEnoughMemberPrice = (String) request.getAttribute("notEnoughMemberPrice");
                                     if(notEnoughMemberPrice !=null && !"".equals(notEnoughMemberPrice)){
                            %>
                             <td ><% out.print(notEnoughMemberPrice);
                             %></td>
                            <%
                                }
                                }
                                }
                            %>
                        </span>
                        <br>
                        <br>

                        <span class="form-actions" align="center" colspan="5" >
                            <input class="btn btn-primary" type="submit" tabindex="3"value="注册"
                                   style="color:white;"/>
                            <a tabindex="5" class="btn btn-link text-muted" onClick="goto_register()"href="/demo9/IfMyServlet">返回</a>

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