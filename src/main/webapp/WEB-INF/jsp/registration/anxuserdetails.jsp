<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> -->
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">

</head>
<br />
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="inner contact">

	<div id="signup">
		<div id="triangle"></div>
		<h1>User Details</h1>
		<form:form id="contact-us" method="POST" modelAttribute="anxUserForm" action="save">
			<form:input path="birthDate" required="required" class="form" id="birthDateId" />
			<form:input path="houseNumber" placeholder="House or Building #" required="required" class="form" />
			<form:input path="street" placeholder="Street" required="required" class="form" />
			<form:input path="city" placeholder="City" required="required" class="form" />
			<form:input path="province" placeholder="Province" required="required" class="form" />
			<form:input path="country" placeholder="Country" required="required" class="form" />
			<input type="submit" class="form-btn semibold" value="Next" />
		</form:form>
		<div class="clear"></div>

	</div>
</div>

<center>
	Already have an account? <a href="#" target="blank">Sign in </a>
</center>

<script>

	$(function() {
		$("#birthDateId").datepicker();
		//Pass the user selected date format
		$("#format").change(function() {
			$("#birthDateId").datepicker("option", "dateFormat", $(this).val());
		});
	});
</script>

