function sendEmailVerification() {

	var secretCode = {
		"emailAddress" : jQuery("#emailVerificationAddress").val(),
		"currentEmail" : jQuery("#currentEmail").val(),
		"currentPhone" : jQuery("#currentPhone").val()
	};
	$.ajax({
		url : '/api/sendCode',
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
			}else if(data.responseText ==="Invalid Email") {
				alert("Invalid Email");
			} else {
				alert("Error parsing json");
			}
		}
	});

}