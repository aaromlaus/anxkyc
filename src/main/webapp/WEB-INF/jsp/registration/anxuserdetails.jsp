<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script>
</head>
<br />
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="inner contact">

	<div id="signup">
		<div id="triangle"></div>
		<h1>User Details</h1>
		<form:form id="contact-us" method="POST" modelAttribute="anxUserForm"
			action="save">
			<form:input path="birthDate" type="Date" required="required" class="form" />
			<div>
			<!-- <input type="submit" class="form-btn semibold" value="Prev" onclick='this.form.action="createaccount";' /> -->
			<input type="submit" class="form-btn semibold" value="Next" />
			</div>
		</form:form>
		<div class="clear"></div>

	</div>
</div>

<center>
	Already have an account? <a href="#" target="blank">Sign in </a>
</center>

<script>
	$( "#prevButtonId" ).click(function() {
	  location.href;
	});
</script>
