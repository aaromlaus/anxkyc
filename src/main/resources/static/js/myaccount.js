function sendEmailVerification() {

	var secretCode = {
		"emailAddress" : jQuery("#emailVerificationAddress").val(),
		"currentEmail" : jQuery("#currentEmail").val(),
		"currentPhone" : jQuery("#currentPhone").val()

	};
	$.ajax({
		url : '/api/sendEmailChangeCode',
		data : JSON.stringify(secretCode),
		method : "POST",
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			$('#codeVerification').modal('show');
		},
		complete : function(data) {
			if (data.responseText === "ok") {
				$('#sendEmailCode').modal('hide');
				$('#codeVerification').modal('show');
			} else {
				$('#errorMessage').html(
						"<div class='alert alert-danger'>Error: "
								+ data.responseText + "</div>");
			}
		}
	});

}
function updatePhoneNumber() {
	var secretCode = {
		"phoneNumber" : jQuery("#phoneNumber").val(),
		"phoneCodeId" : jQuery('#phoneCodeNameId').val(),
		"currentEmail" : jQuery("#currentEmail").val(),
		"currentPhone" : jQuery("#currentPhone").val(),
		"currentPage" : jQuery("#currentPage").val()
	};
	$.ajax({
		url : '/api/changePhoneNumber',
		data : JSON.stringify(secretCode),
		method : "POST",
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			$('#codeVerification').modal('show');
		},
		complete : function(data) {
			if (data.responseText === "ok") {
				$('#codeVerification').modal('hide');
				window.location.href = "/logout";
			} else if (data.responseText === "userdashBoard") {
				window.location.reload();
			} else {
				console.log(data.responseText);
				
			}
		}
	});
}
function enterVerificationCode() {
	var secretCode = {
		"verificationCode" : jQuery("#verificationCode").val(),
		"currentEmail" : jQuery("#currentEmail").val(),
		"currentPhone" : jQuery("#currentPhone").val(),
		"currentPage" : jQuery("#currentPage").val()
	};
	$.ajax({
		url : '/api/changeEmailCode',
		data : JSON.stringify(secretCode),
		method : "POST",
		contentType : 'application/json',
		dataType : "json",
		success : function(data) {
			$('#codeVerification').modal('show');
		},
		complete : function(data) {
			if (data.responseText === "ok") {
				$('#codeVerification').modal('hide');
				window.location.href = "/logout";
			} else if (data.responseText === "userdashBoard") {
				window.location.reload();
			} else {
				
				$('#errorCodeVerification').html(
						"<div class='alert alert-danger'>Error: "
								+ data.responseText + "</div>");
			}
		}
	});
}

function clearErrorMessage() {
	$('#errorMessage').html("");
	$('#errorMessagePhone').html("");

}