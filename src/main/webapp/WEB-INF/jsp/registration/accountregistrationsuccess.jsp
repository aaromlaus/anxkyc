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
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
</head>
<script type="text/javascript">
    function login() {
        location.href = "../login";
    };
</script>
<body>
	<jsp:include page="../common/header.jsp"/>
	
<div class="inner contact">
	<div class="forgotContainer2">
		<h1>Success!</h1>
		<div class="message">
			<c:if test="${not empty param.errorMsg}">
				<div class="alert alert-danger">${param.errorMsg}</div>
			</c:if>
		</div>
		<div class="forgotContainer pad-bot-30">
			<span class="glyphicon glyphicon-ok-circle success" ></span>
			<br>
			<span class="reset-password__note">Your account has been created.</span> 
			<br>
			<span class="reset-password__note">A verification link has been sent to your email address.</span>
		
			<button class="btn-sign-in"  onclick="login()">SIGN IN</button>
		</div>
	</div>	
	</div>
</body>
</html>
