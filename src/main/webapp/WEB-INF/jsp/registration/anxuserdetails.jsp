<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.0/jquery.min.js"></script> -->
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" type="text/css" media="all"
      href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.2/themes/smoothness/jquery-ui.css"    />
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
		<c:if test="${not empty msgDetails}">
			<div class="${msgCss }">
			    ${msgDetails }
			</div>
		</c:if>
		<form:form id="myFormId" method="POST" modelAttribute="anxUserForm" action="save" >
			<form:input path="birthDate" required="required" class="form" id="birthDateId" placeholder="Date of Birth" />
			<form:input path="houseNumber" placeholder="House or Building #" required="required" class="form" />
			<form:input path="street" placeholder="Street" required="required" class="form" />
			<form:input path="city" placeholder="City" required="required" class="form" />
			<form:input path="province" placeholder="Province" required="required" class="form" />
			<form:input path="country" placeholder="Country" required="required" class="form" />
			<form:input path="sourceOfFund" list="sourceOfFundList" placeHolder="Source of Fund" required="required" class="form" />
			<datalist id="sourceOfFundList">
				<option value="Salary" />
				<option value="Family member or remittance" />
				<option value="Personal Savings" />
			</datalist>
			
			<form:hidden path="firstName" required="required" class="form" />
			<form:hidden path="middleName" required="required" class="form" />
			<form:hidden path="lastName" required="required" class="form" />
			<form:hidden path="username" required="required" class="form" />
			<form:hidden path="password" required="required" class="form" />
				
			<input type="submit" class="form-btn semibold" value="Save Changes" />
		</form:form>
		<div class="clear"></div>

	</div>
</div>

<center>
	Already have an account? <a href="../login" target="blank">Sign in </a>
</center>

<script>

	$(function() {
		$("#birthDateId").datepicker({
            changeMonth: true,
            changeYear: true,
            dateFormat: 'mm/dd/yy',
            yearRange: '-100:+0',
            defaultDate: null
        }).attr('readonly','readonly');
		//Pass the user selected date format
		$("#format").change(function() {
			$("#birthDateId").datepicker("option", "dateFormat", $(this).val());
		});
	});
	
</script>

