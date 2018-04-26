<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="modal fade" role="dialog" id="codeVerification">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-envelope mar-r-10"></span><spring:message code="kyc.verification.code.title"/>
					</h4>
				</div>
				<div class="modal-body">
					<p>
						<input type="text" placeholder="Verification Code"
							class="my-account-input" id="verificationCode">
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="kyc.btn.cancel"/></button>
					<button type="button" class="btn btn-primary"
						onclick="enterVerificationCode();"><spring:message code="kyc.btn.change.email"/></button>
				</div>
			</div>

		</div>
	</div>