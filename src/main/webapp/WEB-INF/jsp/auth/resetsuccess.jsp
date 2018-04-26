<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


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
<script type="text/javascript">
    function login() {
        location.href = "../login";
    };
</script>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	
<div class="inner contact">
	<div class="forgotContainer2">
		<h1><spring:message code="kyc.reset.password.complete"/></h1>
		<div class="message">
			<c:if test="${not empty param.errorMsg}">
				<div class="alert alert-danger">${param.errorMsg}</div>
			</c:if>
		</div>
		<div class="forgotContainer pad-bot-30">
			<span class="glyphicon glyphicon-ok-circle success" ></span>
			<br>
			<span class="reset-password__note"><spring:message code="kyc.reset.password.success"/></span> 
			<br>
			<span class="reset-password__note"><spring:message code="kyc.reset.password.login"/></span>
		
			<button class="btn-sign-in"  onclick="login()"><spring:message code="kyc.label.signin"/></button>
		</div>
	</div>	
	</div>
</body>
</html>
