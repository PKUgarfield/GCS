<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%
    String action = request.getAttribute("action") == null ? "" : request.getAttribute("action").toString();
    String loginInfo = request.getAttribute("loginInfo") == null ? "" : request.getAttribute("loginInfo").toString();
	String registerInfo = request.getAttribute("registerInfo") == null ? "" : request.getAttribute("registerInfo").toString();
	
%>

<head>
<title>Maruti Admin</title>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<link rel="stylesheet" href="css/bootstrap.min.css" />
<link rel="stylesheet" href="css/bootstrap-responsive.min.css" />
<link rel="stylesheet" href="css/maruti-login.css" />
</head>
<body>
	<div id="logo">
		<img src="img/login-logo.png" alt="" />
	</div>
	<div id="loginbox">
		<form id="loginform" class="form-vertical" action="user.do?cmd=login" method="post">
			<div class="control-group normal_text">
				<h3>AbcMint Login</h3>
			</div>
			<p class="normal_text">
				If you don't have an AbcMint account, <br />please <a href="#"
					id="to-register"> <font color="#FF6633">regiter</font> </a> here.
			</p>
			<div class="control-group">
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on"><i class="icon-envelope"></i></span><input
							type="text" name="email" placeholder="E-mail" />
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on"><i class="icon-lock"></i></span><input
							type="password" name="password" placeholder="Password" />
					</div>
				</div>
			</div>
			<p class="normal_text" id="loginWarning">
				<font color="#FF6633"><%=loginInfo %></font>
			</p>
			
			<div class="form-actions">
				<span class="pull-left"><a href="#"
					class="flip-link btn btn-warning" id="to-recover">Lost
						password?</a></span> <span class="pull-right"><input type="submit"
					class="btn btn-success" value="Login" /></span>
			</div>
		</form>
		
		<!-- **************************************************************** -->
		
		<form id="recoverform" action="#" class="form-vertical" method="post">
			<p class="normal_text">
				Enter your e-mail address below and we will send you instructions <br />
				<font color="#FF6633">how to recover a password.</font>
			</p>

			<div class="controls">
				<div class="main_input_box">
					<span class="add-on"><i class="icon-envelope"></i></span><input
						type="text" placeholder="E-mail" />
				</div>
			</div>

			<div class="form-actions">
				<span class="pull-left"><a href="#"
					class="flip-link btn btn-warning" id="to-login">&laquo; Back to
						login</a></span> <span class="pull-right"><input type="submit"
					class="btn btn-info" value="Recover" /></span>
			</div>
		</form>


	<!-- **************************************************************** -->



		<form id="registerform" class="form-vertical" action="user.do?cmd=register" method="post">
			<div class="control-group normal_text">
				<h3>AbcMint Register</h3>
			</div>
			<div class="control-group">
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on"><i class="icon-envelope"></i></span><input
							type="text" name="email" placeholder="E-mail" />
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on"><i class="icon-user"></i></span><input
							type="text" name="nickname" placeholder="Username" />
					</div>
				</div>
			</div>
			<div class="control-group">
				<div class="controls">
					<div class="main_input_box">
						<span class="add-on"><i class="icon-lock"></i></span><input
							type="password" name="password" placeholder="Password" />
					</div>
				</div>
			</div>
			<p class="normal_text" id="registerWarning">
				<font color="#FF6633"><%=registerInfo%></font>
			</p>
			<div class="form-actions">
				<span class="pull-left"><a href="#"
					class="flip-link btn btn-warning" id="to-login2">&laquo; Back
						to login</a></span> <span class="pull-right"><input type="submit"
					class="btn btn-info" value="Register" /></span>
			</div>
		</form>
	</div>

	<script src="js/jquery.min.js"></script>
	<script>
	$(document).ready(function(){
		var loginInfo = "<%=loginInfo %>";
		var registerInfo = "<%=registerInfo%>";
		var action = "<%=action%>"
		
		var login = $('#loginform');
		var recover = $('#recoverform');
		var register = $('#registerform');
		var loginWarning = $('#loginWarning');
		var registerWarning = $('#registerWarning')
		var speed = 400;
		
		if(action == "" || action == "login") {
			recover.hide();
			register.hide();
			login.show();
			if(loginInfo=="")
				loginWarning.hide();
		}else if(action == "recover") {
			login.hide();
			register.hide();
			recover.show();
		}else{	
			login.hide();
			recover.hide();
			register.show();
			if(registerInfo=="")
				registerWarning.hide()
		}


		$('#to-recover').click(function(){
			
			$("#loginform").slideUp();
			$("#registerform").hide();
			$("#recoverform").fadeIn();
			loginWarning.hide();
			registerWarning.hide();
		});
		$('#to-login').click(function(){
			
			$("#recoverform").hide();
			$("#registerform").hide();
			$("#loginform").fadeIn();
			loginWarning.hide();
			registerWarning.hide();
		});
		$('#to-register').click(function(){
			
			$("#recoverform").hide();
			$("#loginform").hide();
			$("#registerform").fadeIn();
			loginWarning.hide();
			registerWarning.hide();
		});
		
		
		$('#to-login2').click(function(){
			$("#recoverform").hide();
			$("#registerform").hide();
			$("#loginform").fadeIn();
			loginWarning.hide();
			registerWarning.hide();
		});
	    
		$("input").change(function(){
			loginWarning.hide();
			registerWarning.hide();
		});
	    if($.browser.msie == true && $.browser.version.slice(0,3) < 10) {
	        $('input[placeholder]').each(function(){ 
	       
	        var input = $(this);       
	       
	        $(input).val(input.attr('placeholder'));
	               
	        $(input).focus(function(){
	             if (input.val() == input.attr('placeholder')) {
	                 input.val('');
	             }
	        });
	       
	        $(input).blur(function(){
	            if (input.val() == '' || input.val() == input.attr('placeholder')) {
	                input.val(input.attr('placeholder'));
	            }
	        });
	    });

	        
	        
	    }
	});
	</script>
</body>

</html>