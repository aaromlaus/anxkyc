<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<div class="modal fade" role="dialog" id="sendEmailCode">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>

					<h4 class="modal-title">
						<span class="glyphicon glyphicon-envelope mar-r-10"></span><spring:message code="modal.email.verification"/>
					</h4>
				</div>

				<div class="modal-body">
					<div id="errorMessage"></div>
					<p>
						<input type="text" placeholder="Email Address"
							class="my-account-input sm-input" id="emailVerificationAddress">
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="kyc.btn.cancel"/></button>
					<button type="button" class="btn btn-primary"
						onclick="sendEmailVerification();">
						<span class="glyphicon glyphicon-send mar-r-10"></span><spring:message code="kyc.btn.send"/>
					</button>
				</div>
			</div>

		</div>
	</div>