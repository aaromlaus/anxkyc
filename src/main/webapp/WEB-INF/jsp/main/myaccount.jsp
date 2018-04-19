<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Safespace Decentralize Exchange</title>
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
				<strong>Account Settings</strong>
			</h1>
			<div class="col-sm-12 bg-info mar-bot-0">
				<div class="col-sm-3 text-left mar-t-b-10 h4">
					<strong>Email</strong>
				</div>
				<div class="col-sm-5 text-left mar-t-b-10 h4">
					<c:out value="${anxUserForm.emailAddress}"></c:out>
					<form:hidden path="anxUserForm.emailAddress" id="currentEmail"/>
				</div>
				<div class="col-sm-4 text-right mar-t-b-10 h4">
					<a href="#" data-toggle="modal" data-target="#sendEmailCode" onClick="clearErrorMessage();">Change
						email</a>
				</div>
			</div>
			<div class="col-sm-12 bg-info mar-bot-0">
				<div class="col-sm-3 text-left mar-t-b-10 h4">
					<strong>Phone</strong>
				</div>
				<div class="col-sm-5 text-left mar-t-b-10 h4">
					<form:hidden path="anxUserForm.phoneNumber" id="currentPhone"/>
					<c:out value="${anxUserForm.emailAddress}"></c:out>
				</div>
				<div class="col-sm-4 text-right mar-t-b-10 h4">
					<a href="#" data-toggle="modal" data-target="#phoneVerification" onClick="clearErrorMessage();">Change
						phone</a>
				</div>
			</div>
			<div class="col-sm-12 bg-info mar-bot-0">
				<div class="col-sm-3 text-left mar-t-b-10 h4">
					<strong>Password</strong>
				</div>
				<div class="col-sm-5 text-left mar-t-b-10 h5">.....
				</div>
				<div class="col-sm-4 text-right mar-t-b-10 h4">
					<a data-toggle="collapse" href="#collapseExample" role="button"
						aria-expanded="false" aria-controls="collapseExample">Change
						password</a>
				</div>
			</div>
			<div class="col-sm-12 bg-info mar-bot-0 collapse"
				id="collapseExample">
				<div class="panel panel-default">

					<div class="panel-heading">We just sent a verification code
						to your email. Please enter this code and your new password below.</div>
					<div class="panel-heading">
						<input type="text" placeholder="Verification Code"
							class="my-account-input">
					</div>
					<div class="panel-heading">
						<input type="text" placeholder="Password" class="my-account-input">
					</div>
					<div class="panel-heading">
						<input type="text" placeholder="Confirm Password"
							class="my-account-input">
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-3 clearfix row ">&nbsp</div>

	<div class="modal fade" role="dialog" id="sendEmailCode">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<div id="errorMessage"></div>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-envelope mar-r-10"></span>Email
						Verification
					</h4>
				</div>
				
				<div class="modal-body">
					<p><input type="text" placeholder="Email Address" class="my-account-input" id="emailVerificationAddress"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" onclick="sendEmailVerification();"><span class="glyphicon glyphicon-send mar-r-10"></span>Send</button>
				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" role="dialog" id="phoneVerification">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title"><span class="glyphicon glyphicon-phone mar-r-10"></span>
					Phone Verification</h4>
				</div>
				<div class="modal-body">
					<p><input type="text" placeholder="Phone number" class="my-account-input"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" data-dismiss="modal">Send</button>
				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" role="dialog" id="codeVerification">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-envelope mar-r-10"></span>Enter verification code
					</h4>
				</div>
				<div class="modal-body">
					<p><input type="text" placeholder="Verification Code" class="my-account-input" id="verificationCode"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" onclick="enterVerificationCode();">Change Email</button>
				</div>
			</div>

		</div>
	</div>
	
</body>
</html>