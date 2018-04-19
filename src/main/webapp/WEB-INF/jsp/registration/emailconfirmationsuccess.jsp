<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
		<h1 class="display-3">Thank you!</h1>
		<p class="lead">${emailConfirmMsg }</p>
		<hr>
		<p>
			Having trouble? <a href="#">Contact us</a>
		</p>
		<p class="lead">
			<a class="btn btn-primary btn-sm"
				href="../login" role="button">Continue to
				login</a>
		</p>
	</div>
</body>
</html>