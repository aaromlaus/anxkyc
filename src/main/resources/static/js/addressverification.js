/**
 * 
 */

function sameAddress(){
	if($('#sameAddressId').is(':checked')){
		$('#permanentAddressId').hide();
		$('#permanentunitId').removeAttr('required');
		$('#permanentcityId').removeAttr('required');
		$('#permanentstateId').removeAttr('required');
		$('#permanentpostalId').removeAttr('required');
		$('#permanentcountryId').removeAttr('required');
	}else{
		$('#permanentAddressId').show();
		$('#permanentunitId').attr('required','required');
		$('#permanentcityId').attr('required','required');
		$('#permanentstateId').attr('required','required');
		$('#permanentpostalId').attr('required','required');
		$('#permanentcountryId').attr('required','required');
		$('#permanentunitId').val('');
		$('#permanentcityId').val('');
		$('#permanentstateId').val('');
		$('#permanentpostalId').val('');
		$('#permanentcountryId').val('');
		
	}
}

function removeDocument(){
	
	$('#docFileLblId').popover('destroy');
	$('#docFileLblId').remove();
	$('#docFileLinkId').remove();
	$('#docUploadId').append('<input  required="required" type="file" class="form-control btn btn-default btn-md" id="docFileId" name="addressVerificationDoc.fileName" value="${user.addressVerificationDoc.fileName }" onchange="showDocPreview()" >');
}

var presentDoc = false;
function  showDocPreview(){

	var input = document.getElementById("docFileId");
	console.log(input);
	var fReader = new FileReader();
	fReader.readAsDataURL(input.files[0]);
	fReader.onloadend = function(event) {
		if(!presentDoc){
			$('#docFileId').popover({
		        content: "<img src='"+event.target.result+"' style='width: 250px;height:200px;'>",
		        placement : 'bottom',
		        trigger: 'hover',
		        html:true
		      });
			presentDoc = true;
		}else{
			var img = $('<img/>', {
				id : 'dynamic',
				width : 250,
				height : 200
			});
			img.attr('src', event.target.result);
			$('#docFileId').attr("data-content",$(img)[0].outerHTML).popover("show");
		}
		$('#fileStr').val(event.target.result);
	}
}