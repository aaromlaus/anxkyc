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
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/main.css">
</head>

<body>
	<jsp:include page="../jsp/common/header.jsp"></jsp:include>
	<div class=""></div>
	<div class="message">
		<c:if test="${not empty errorMsg}">
			<div class="alert alert-danger">
				${errorMsg}
			</div>
		</c:if>
	</div>
	<div class="login">
		<h3>ANX Login</h3>
		<form:form method="POST" modelAttribute="loginForm" action="/login" >
			<form:input path="username" class="field" placeholder="Email or Mobile Number" />
			<form:password path="password" class="field" placeholder="Password" />
			<a href="/forgotPassword" class="forgot">Forgot Password?</a>
			<input class="btn" type="submit" value="SIGN IN"/>
			<label>Don't have an Account? <a href="/signup/" class="forgot">Sign Up</a></label>
		</form:form>
	</div>	
</body>
</html>
