<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
</head>
<br />
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="inner contact">

	<div id="signup">
		<div id="triangle"></div>
		<h1>Registration</h1>
		<form:form id="contact-us" method="POST" modelAttribute="anxUserForm"
			action="createaccount">

			<form:input path="firstName" required="required" class="form"
				placeholder="First Name" />
			<form:input path="middleName" required="required" class="form"
				placeholder="Middle Name" />
			<form:input path="lastName" required="required" class="form"
				placeholder="Last Name" />
			<form:input path="username" required="required" class="form"
				placeholder="Email Address or Mobile Number" />
			<form:password path="password" required="required" class="form"
				placeholder="Password" />
			<input type="submit" class="form-btn semibold" value="Create Account" />
		</form:form>
		<div class="clear"></div>


	</div>
</div>

<center>
	Already have an account? <a href="#" target="blank">Sign in </a>
</center>
