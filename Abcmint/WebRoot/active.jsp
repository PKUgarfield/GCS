<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<%
	String email = request.getAttribute("email") == null ? "" : request.getAttribute("email").toString();
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
		<form id="loginform" class="form-vertical" action="Login">
			<div class="control-group normal_text">
				<h3>Register success</h3>
			</div>
			<p class="normal_text">
				Please check the active email in <%=email %>.
				<br/>Before you active your account, you can't login your account nomal.
			</p>

			<div class="form-actions">
				<span class="pull-left"><a href="#"
					class="flip-link btn btn-warning" id="to-login">&laquo; Back to
						login</a></span>
			</div>
		</form>

	</div>

	<script src="js/jquery.min.js"></script>
	<script type="text/javascript">
	$(document).ready(function(){
		$('#to-login').click(function(){		
			$(window).attr('location','user.jsp');
		});
		
	})
	</script>
</body>

</html>