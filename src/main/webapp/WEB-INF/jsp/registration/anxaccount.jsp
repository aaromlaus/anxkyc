<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="http://code.jquery.com/ui/1.11.0/jquery-ui.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
</head>
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="inner contact">

	<div id="signup">
		<h1>Registration</h1>
		<c:if test="${not empty msgDetails}">
			<div class="${msgCss }">
			    ${msgDetails }
			</div>
		</c:if>
		<form:form id="contact-us" method="POST" modelAttribute="anxUserForm"
			action="createaccount">			
			<form:hidden path="userId" required="required" class="form" />
			<form:input path="firstName" required="required" class="form" placeholder="First Name" />
			<form:input path="middleName" required="required" class="form" placeholder="Middle Name" />
			<form:input path="lastName" required="required" class="form" placeholder="Last Name" />
			
			<div id="emailAddressGroupId" style="display:${emailDisplay};">	
				<form:errors path="emailAddress" class="formerror" />
				<form:input path="emailAddress" required="required" class="form"
					placeholder="Email Address" />
				<div class="usernameChooserLink">
					<a id="usePhonelinkId" href="#" onclick="myFunction();return false;">Sign up using Mobile Number </a>
				</div>
			</div>
			
			<div id="phoneGroupId" style="display:${phoneDisplay};">
				<form:errors path="phoneNumber" class="formerror" />
				<form:select class="selectpicker phone-code-width" data-live-search="true"  path="phoneCode.phoneCodeId" >
				 	<form:options items="${countryCodeList}" itemValue="phoneCodeId" itemLabel="phoneCodeCountry" />
				 </form:select>
				<form:input path="phoneNumber" class="form phone-number-width" placeholder="Mobile Number" />	
				<div class="usernameChooserLink">
					<a href="#" onclick="myFunction();return false;">Sign up using Email Address </a>
				</div>
				
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
	Already have an account? <a href="../login">Sign in </a>
</center>
<script>

$( document ).ready(function() {
	 var phoneGroupId = document.getElementById("phoneGroupId"); 
	 var usePhonelink = document.getElementById("usePhonelinkId"); 
	 var phoneNumber = document.getElementById("phoneNumber");

	 phoneGroupId.style.display = "none";
	 usePhonelink.style.display = "block";
	 if(phoneNumber.value != "") {
		 usePhonelink.style.display = "none";
		 phoneGroupId.style.display = "block";
	 }
});

function myFunction() {
    
	var emailAddressGroupId = document.getElementById("emailAddressGroupId");
    var phoneGroupId = document.getElementById("phoneGroupId"); 
    
    var phoneNumber = document.getElementById("phoneNumber");
    var emailAddress = document.getElementById("emailAddress");
    
    if (emailAddressGroupId.style.display === "none") {
    	emailAddressGroupId.style.display = "block";
    	phoneGroupId.style.display = "none";
    	phoneNumber.required = false;
    	emailAddress.required = true;
    	phoneNumber.value = "";
    } else {
    	emailAddressGroupId.style.display = "none";
    	phoneGroupId.style.display = "block";
    	phoneNumber.required = true;
    	emailAddress.required = false;
    	emailAddress.value = "";
    }
}
</script>
