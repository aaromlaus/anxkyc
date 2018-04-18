function sendEmailVerification() {

	var secretCode = {
		"emailAddress" : jQuery("#emailVerificationAddress").val()
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