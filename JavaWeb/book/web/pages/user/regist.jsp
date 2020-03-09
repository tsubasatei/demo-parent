<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>尚硅谷会员注册页面</title>
	<%@include file="/pages/common/head.jsp"%>
	<style type="text/css">
		.login_form{
			height:420px;
			margin-top: 25px;
		}
	</style>
	<script src="static/script/jquery-1.7.2.js"></script>
	<script>
		$(function () {

		    // 验证用户名是否可用
			$("#username").blur(function () {
			    var username = this.value;
				$.getJSON("${basePath}userServlet", "action=ajaxExistsUsername&username=" + username, function (data) {
					if (data.existsUsername) {
					    $("span.errorMsg").text("用户名已存在");
					    return false;
					} else {
                        $("span.errorMsg").text("用户名可用");
					}
                })
            })

		    // 切换验证码图片
			$("#code_img").click(function () {
				this.src = "${basePath}kaptcha.jpg?id=" + new Date();
            });

			$("#sub_btn").click(function () {
                /**
				 * 验证用户名：必须由字母，数字下划线组成，并且长度为5 到12 位
                 验证密码：必须由字母，数字下划线组成，并且长度为5 到12 位
                 验证确认密码：和密码相同
                 邮箱验证：xxxxx@xxx.com
                 验证码：现在只需要验证用户已输入。因为还没讲到服务器。验证码生成。
                 */
				var username = $("#username").val();
				var usernamePatt = /^\w{5,12}$/;
				if (!usernamePatt.test(username)) {
				    $(".errorMsg").text('用户名不合法');
				    return false;
				}

				var password = $("#password").val();
				if (!usernamePatt.test(password)) {
                    $(".errorMsg").text('密码不合法');
                    return false;
				}
				var repwd = $("#repwd").val();
				if (repwd != password) {
                    $(".errorMsg").text('密码不一致');
                    return false;
				}
				var email = $("#email").val();
				var emailPatt = /^[a-z\d]+(\.[a-z\d]+)*@([\da-z](-[\da-z])?)+(\.{1,2}[a-z]+)+$/;
				if (!emailPatt.test(email)) {
                    $(".errorMsg").text('邮箱不合法');
                    return false;
				}
				var code = $("#code").val();
				code = $.trim(code);
				if (code == null || code == '') {
                    $(".errorMsg").text('验证码为空');
                    return false;
				}

                $(".errorMsg").text('');
            })
        })
	</script>
</head>
<body>
		<div id="login_header">
			<img class="logo_img" alt="" src="static/img/logo.gif" >
		</div>
		
			<div class="login_banner">
			
				<div id="l_content">
					<span class="login_word">欢迎注册</span>
				</div>
				
				<div id="content">
					<div class="login_form">
						<div class="login_box">
							<div class="tit">
								<h1>注册尚硅谷会员</h1>
								<span class="errorMsg">${msg}</span>
							</div>
							<div class="form">
								<form action="userServlet" method="post">
									<input type="hidden" name="action" value="register">
									<label>用户名称：</label>
									<input class="itxt" type="text" placeholder="请输入用户名" autocomplete="off" tabindex="1" name="username" id="username" value="${username}"/>
									<br />
									<br />
									<label>用户密码：</label>
									<input class="itxt" type="password" placeholder="请输入密码" autocomplete="off" tabindex="1" name="password" id="password" />
									<br />
									<br />
									<label>确认密码：</label>
									<input class="itxt" type="password" placeholder="确认密码" autocomplete="off" tabindex="1" name="repwd" id="repwd" />
									<br />
									<br />
									<label>电子邮件：</label>
									<input class="itxt" type="text" placeholder="请输入邮箱地址" autocomplete="off" tabindex="1" name="email" id="email" value="${email}"/>
									<br />
									<br />
									<label>验证码：</label>
									<input class="itxt" type="text" style="width: 110px;" id="code" name="code"/>
									<img alt="" id="code_img" src="kaptcha.jpg" style="float: right; margin-right: 40px; width: 110px; height: 40px;">
									<br />
									<br />
									<input type="submit" value="注册" id="sub_btn" />
									
								</form>
							</div>
							
						</div>
					</div>
				</div>
			</div>
		<%@include file="/pages/common/footer.jsp"%>
</body>
</html>