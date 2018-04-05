<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!doctype html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="css/login.css">
</head>

<body>
	<div class="login">
		<h3>ANX Login</h3>
		<form:form method="POST" modelAttribute="loginForm" action="doLogin" >
			<form:input path="username" class="field" placeholder="Email or Mobile Number" />
			<form:password path="password" class="field" placeholder="Password" />
			<a href=# class="forgot">Forgot Password?</a>
			<input class="btn" type="submit" value="SIGN IN"/>
			<label>Don't have an Account? <a href="#" class="forgot">Sign Up</a></a></label>
		</form:form>
	</div>
</body>
</html>
