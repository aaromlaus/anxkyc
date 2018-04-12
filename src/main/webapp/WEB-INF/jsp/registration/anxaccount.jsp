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
		<c:if test="${not empty msgDetails}">
			<div class="${msgCss }">
			    ${msgDetails }
			</div>
		</c:if>
		<form:form id="contact-us" method="POST" modelAttribute="anxUserForm"
			action="createaccount">			
			<form:hidden path="usePhoneNumber" value="false"/>
			<form:input path="firstName" required="required" class="form"
				placeholder="First Name" />
			<form:input path="middleName" required="required" class="form"
				placeholder="Middle Name" />
			<form:input path="lastName" required="required" class="form"
				placeholder="Last Name" />
			<div id="userNameLink">	
				<form:errors path="username" class="formerror" />
				<form:input path="username" required="required" class="form"
					placeholder="Email Address" />
				<a href="#" onclick="myFunction();return false;">Use Phone number instead </a>
			</div>
			
			<div id="phoneNumberLink" class="displayNone">	
				 <form:select class="form-control"  path="phoneCode.phoneCodeId" >
				 	<form:options items="${phoneCodeLookUp}" itemValue="phoneCodeId" itemLabel="phoneCodeCountry" />
				 </form:select>
				<form:input path="phoneNumber" class="form"
					placeholder="Mobile Number" />	
				<a href="#" onclick="myFunction();return false;">Use Email instead </a>
				
			</div>	

			<form:errors path="password" class="formerror" />
			<form:password path="password" required="required" class="form"
				placeholder="Password" />
			
			<form:password path="confirmPassword" required="required" class="form"
				placeholder="Confirm Password" />
			<input type="submit" class="form-btn semibold" value="Create Account" />
		</form:form>
		<div class="clear"></div>


	</div>
</div>

<center>
	Already have an account? <a href="../login" target="blank">Sign in </a>
</center>
<script>
function myFunction() {
    var userNameLink = document.getElementById("userNameLink");
    var phoneNumberLink = document.getElementById("phoneNumberLink"); 
    var phoneNumber = document.getElementById("phoneNumber");
    var username = document.getElementById("username");
    var usePhoneNumber =  document.getElementById("usePhoneNumber");
    if (userNameLink.style.display === "none") {
    	userNameLink.style.display = "block";
    	phoneNumberLink.style.display = "none";
    	phoneNumber.required = false;
    	username.required = true;
    	usePhoneNumber.value = false;
    } else {
    	userNameLink.style.display = "none";
    	phoneNumberLink.style.display = "block";
    	phoneNumber.required = true;
    	username.required = false;
    	usePhoneNumber.value = true;
    }
}
</script>
