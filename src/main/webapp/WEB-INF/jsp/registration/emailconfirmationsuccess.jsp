<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
</head>
<body align="center">
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="jumbotron text-xs-center">
		<h1 class="display-3"><spring:message code="kyc.label.thankyou"/>!</h1>
		<p class="lead">${emailConfirmMsg }</p>
		<hr>
		<p>
			<spring:message code="kyc.label.trouble"/> <a href="#"><spring:message code="kyc.label.contactus"/></a>
		</p>
		<p class="lead">
			<a class="btn btn-primary btn-sm"
				href="../login" role="button"><spring:message code="kyc.email.continue.login"/></a>
		</p>
	</div>
</body>
</html>