<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="css/main.css">
<link rel="stylesheet" href="css/form.css">
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	
<div class="inner contact">
	<div class="forgotContainer2">
		<!-- <h3>ANX Login</h3> -->
		<h1>Forgot your password?</h1>
		<div class="forgotContainer">
			<span class="reset-password__note">Please enter your email or mobile number, and we'll send you a reset code</span>
		</div>
		<form:form method="POST" modelAttribute="loginForm" action="/sendCode" >
			<form:input path="username" class="field" placeholder="Email or Mobile Number" />
			<input class="btn" type="submit" value="SEND CODE"/>
			<label>Don't have an Account? <a href="/signup/">Sign Up</a></label>
		</form:form>
	</div>	
	</div>
</body>
</html>
