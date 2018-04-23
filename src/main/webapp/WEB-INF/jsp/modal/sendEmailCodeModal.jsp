<div class="modal fade" role="dialog" id="sendEmailCode">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>

					<h4 class="modal-title">
						<span class="glyphicon glyphicon-envelope mar-r-10"></span>Email
						Verification
					</h4>
				</div>

				<div class="modal-body">
					<div id="errorMessage"></div>
					<p>
						<input type="text" placeholder="Email Address"
							class="my-account-input" id="emailVerificationAddress">
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary"
						onclick="sendEmailVerification();">
						<span class="glyphicon glyphicon-send mar-r-10"></span>Send
					</button>
				</div>
			</div>

		</div>
	</div>