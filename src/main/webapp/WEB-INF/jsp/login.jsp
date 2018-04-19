<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/form.css">
</head>

<body>
	<jsp:include page="../jsp/common/header.jsp"></jsp:include>

	<div class="login-form" style="margin-top: 2.5%;">
		<img class="logo-container" src="/images/anxlogo.png" />
		<form:form method="POST" id="loginInputFormId" class="login-form" modelAttribute="loginForm" action="/login" >
		<div class="message">
			<c:if test="${not empty errorMsg}">
				<div class="alert alert-danger">
					${errorMsg}
				</div>
			</c:if>
		</div>
			<h4>Login Form</h4>
			<form:input path="username" class="field" placeholder="Email address or Mobile Number" />
			<form:password path="password" class="field" placeholder="Password" />
			<button>Sign In</button>
			<p class="message">
				Not registered? <a href="/signup/">Create an account</a>
			</p>
			<a id="forgotPasswordBtnId" class="btn" href="/forgotPassword">Forgot your password?</a>
		</form:form>
	</div>
	<script>
	
		$('.message a').click(function() {
			$('form').animate({
				height : "toggle",
				opacity : "toggle"
			}, "slow");
		});
	</script>
</body>
</html>
