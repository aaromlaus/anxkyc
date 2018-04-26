<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="modal fade" role="dialog" id="phoneVerification">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-phone mar-r-10"></span> <spring:message code="modal.phone.verification"/>
					</h4>
				</div>
				<div class="modal-body">
					<div id="errorMessagePhone"></div>
					<p>
						<form:select
							class="selectpicker phone-code-width my-account-input"
							data-live-search="true" path="anxUser.phoneCode.phoneCodeId"
							id="phoneCodeNameId">
							<form:options items="${countryCodeList}" itemValue="phoneCodeId"
								itemLabel="phoneCodeCountry" />
						</form:select>
						<input type="text" placeholder="Mobile number"
							class="my-account-input phone-number-width" id="phoneNumber">
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal"><spring:message code="kyc.btn.cancel"/></button>
					<button type="button" class="btn btn-primary"
						onclick="updatePhoneNumber();"><spring:message code="kyc.btn.send"/></button>
				</div>
			</div>

		</div>
	</div>