<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/verificationsteps.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
<script src="/js/verificationsteps.js"></script>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/headermenu.jsp"></jsp:include>
	<div class="inner contact">
		<div class="col-sm-12 clearfix row">
			<c:if test="${not empty msgDetails}">
				<div class="${msgCss }">${msgDetails }</div>
			</c:if>
		</div>

		<div class="container" id="containerId" style="height:430px;">
			<div class="stepwizard">
				<div class="stepwizard-row stepwizard-row-single setup-panel">
					
					<div class="stepwizard-step" style="width:100%; height:95%;">
						<a href="#identification" type="button"
							class="btn btn-default btn-circle"> <i id="step2btn"
							class="glyphicon glyphicon-user" style="font-size: 50px; color: #337ab7"></i>
						</a>
						<p class="side-border">Identification</p>
					</div>
				</div>
			</div>
			
			<form role="form" class="verification-form" style="height:75%;" >
					<div class="verification-row">
						<h4>Before continuing, please choose which of the following applies to you?</h4>
						<select class="selectpicker select-input"
								data-live-search="true" id="accountOptionId">
								<option value="#">Select here</option>
								<option value="singleAccount">This is my only account </option>
								<option value="multipleAccount">I have multiple accounts</option>
							</select>
					</div>	
					
					<div class="verification-row" id="multipleAccountDivId" style="display: none; margin-top:2%">
						<h4>Thank you. Which of the following applies to you on why you have multiple accounts?</h4>
						<select class="selectpicker select-input"
								data-live-search="true" id="accountOptionId">
								<option value="#">Select here</option>
								<option value="forgotPassword">Because I forgot my password </option>
								<option value="cannotAccessAccount">Because I cannot access my account</option>
								<option value="cannotAccessAccount">I have different reasons for creating another accounts</option>
							</select>
					</div>
			</form>
		</div>
		
	</div>
</body>
<script>
	$('#accountOptionId').change(function() {
		if($(this).index() != 0) {
			
			if($(this).val() == "singleAccount") {
				location.href = "./steps";
			} else if($(this).val() == "multipleAccount") {
				$('#multipleAccountDivId').fadeTo(600, 1);
			} else {
				$('#multipleAccountDivId').fadeTo(600, 0);
			}
		}
		
	});
</script>



