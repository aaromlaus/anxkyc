<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
<script src="/js/myaccount.js"></script>

</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/headermenu.jsp"></jsp:include>
	<div class="col-sm-3 clearfix row ">&nbsp</div>
	<div class="col-sm-6 clearfix row ">
		<div id="container">
			<h1>
				<strong><spring:message code="kyc.label.account.setting"/></strong>
			</h1>
			<div class="col-sm-12 bg-info mar-bot-0">
				<div class="col-sm-3 text-left mar-t-b-10 h4">
					<strong><spring:message code="kyc.label.email"/></strong>
				</div>
				<div class="col-sm-5 text-left mar-t-b-10 h4">
					<c:out value="${anxUser.emailAddress}"></c:out>
					<form:hidden path="anxUser.emailAddress" id="currentEmail" />
				</div>
				<div class="col-sm-4 text-right mar-t-b-10 h4">
					<a href="#" data-toggle="modal" data-target="#sendEmailCode"
						onClick="clearErrorMessage();">${anxUser.emailAddress.length() >0 ? 'Change Email':'Add Email' }</a>
				</div>
			</div>
			<div class="col-sm-12 bg-info mar-bot-0">
				<div class="col-sm-3 text-left mar-t-b-10 h4">
					<strong><spring:message code="kyc.label.phone"/></strong>
				</div>
				<div class="col-sm-5 text-left mar-t-b-10 h4">
					<form:hidden path="anxUser.phoneNumber" id="currentPhone" />
					<c:out value="${anxUser.phoneNumber}"></c:out>
				</div>
				<div class="col-sm-4 text-right mar-t-b-10 h4">
					<a href="#" data-toggle="modal" data-target="#phoneVerification"
						onClick="clearErrorMessage();">${anxUser.phoneNumber.length() >0 ? 'Change Phone':'Add Phone' }</a>
				</div>
			</div>
			<div class="col-sm-12 bg-info mar-bot-0">
				<div class="col-sm-3 text-left mar-t-b-10 h4">
					<strong><spring:message code="kyc.label.password"/></strong>
				</div>
				<div class="col-sm-5 text-left mar-t-b-10 h5">.....</div>
				<div class="col-sm-4 text-right mar-t-b-10 h4">
					<a data-toggle="collapse" href="#collapseExample" role="button"
						aria-expanded="false" aria-controls="collapseExample"><spring:message code="kyc.label.change.password"/></a>
				</div>
			</div>
			<div
				class="col-sm-12 bg-info mar-bot-0 collapse ${passwordClassDisplay}"
				id="collapseExample">
				<div class="panel panel-default ">
					<form:form id="contact-us" method="POST"
						modelAttribute="anxUser" action="changePassword">
						<div class="${msgCss }">${msgDetails }</div>
						<div class="panel-heading">
							<form:password path="password" required="required"
								class="my-account-input" placeholder="Password" />
						</div>
						<div class="panel-heading">
							<form:password path="confirmPassword" required="required"
								class="my-account-input" placeholder="Confirm Password" />
						</div>
						<div class="panel-heading text-right">
							<a data-toggle="collapse" href="#collapseExample" role="button"
								aria-expanded="false" aria-controls="collapseExample"
								class="btn">Cancel</a>
							<button type="submit" class="btn btn-primary">Change</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-3 clearfix row ">&nbsp</div>
	<jsp:include page="../modal/sendEmailCodeModal.jsp"></jsp:include>
	<jsp:include page="../modal/sendPhoneCodeModal.jsp"></jsp:include>
	<jsp:include page="../modal/codeVerificationModal.jsp"></jsp:include>
	

</body>
</html>