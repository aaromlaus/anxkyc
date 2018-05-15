$(document).ready(function () {
  var navListItems = $('div.setup-panel div a'),
          allWells = $('.setup-content'),
          allNextBtn = $('.nextBtn'),
  		  allPrevBtn = $('.prevBtn');

  allWells.hide();

  navListItems.click(function (e) {
      e.preventDefault();
      var $target = $($(this).attr('href')),
              $item = $(this);

      if (!$item.hasClass('disabled')) {
          navListItems.removeClass('btn-primary1').addClass('btn-default1');
          $item.addClass('btn-primary1');
          allWells.hide();
          $target.show();
          $target.find('input:eq(0)').focus();
      }
  });
  
  allPrevBtn.click(function(){
      var curStep = $(this).closest(".setup-content"),
          curStepBtn = curStep.attr("id"),
          prevStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().prev().children("a");

          prevStepWizard.removeClass('disabledLink').trigger('click');
  });

  allNextBtn.click(function(){
      var curStep = $(this).closest(".setup-content"),
          curStepBtn = curStep.attr("id"),
          nextStepWizard = $('div.setup-panel div a[href="#' + curStepBtn + '"]').parent().next().children("a"),
          curInputs = curStep.find("input[type='text'],input[type='url'],select,input[type='radio'],input[type='date']"),
          isValid = true;
      	var id = curStep.attr("id").substring(5,6);
	      $(".form-group").removeClass("has-error");
	      for(var i=0; i<curInputs.length; i++){
          if (!curInputs[i].validity.valid){
              isValid = false;
              $(curInputs[i]).closest(".form-group").addClass("has-error");
          }
      }

      if (isValid){
          nextStepWizard.removeClass('disabledLink').trigger('click');
          $('#step'+id+'btn').css('color', '#397D02');
      }
  });

  $('div.setup-panel div a.btn-primary1').trigger('click');
});

function employmentChanged(){
	var status = $('input[name=employmentStatus]:checked').val();

	if(status == 'employed'){
		hideSourceDetails();
		showIndustryDetails();
		showEmploymentDetails();
		
	}else if(status == 'selfemployed'){
		hideSourceDetails();
		hideEmploymentDetails();
		
	}else if(status == 'retired'|| status == 'unemployed'|| status == 'student'){
		showSourceDetails();
		hideIndustryDetails();
		hideEmploymentDetails();
	}
}

function showIndustryDetails(){
	$('#industryDivId').show();
	$('#industryId').attr("required","required");
}

function hideIndustryDetails(){
	$('#industryDivId').hide();
	$('#industryId').removeAttr("required");
	$('#industryId').val('');
}

function showSourceDetails(){
	$('#sourceDivId').show();
	$('#fundsourceId').attr("required","required");
}

function hideSourceDetails(){
	$('#sourceDivId').hide();
	$('#fundsourceId').removeAttr("required");
	$('#fundsourceId').val('');
}

function showEmploymentDetails(){
	$('#employedSubId').show();
	$('#titleId').attr("required","required");
	$('#employerId').attr("required","required");
}

function hideEmploymentDetails(){
	$('#employedSubId').hide();
	$('#titleId').removeAttr("required");
	$('#employerId').removeAttr("required");
	$('#titleId').val('');
	$('#employerId').val('');
}



function fundSourceChanged(){
	var source = $('#fundsourceId').val();
	
	if(source == 'other'){
		$('#sourceOfFundExpId').show();
		$('#subSourceOfFundExpId').attr("required","required");
	}else{
		$('#sourceOfFundExpId').hide();
		$('#subSourceOfFundExpId').removeAttr("required");
		$('#subSourceOfFundExpId').val('');
	}
}

function idTypeChanged(){
	var type = $('#idTypeId').val()
	console.log(type);
	if(type == "afp"){
		showNumberId();
		showBackIdImage();
	}
	else if(type == "drivers_license"){
		showNumberId();
		hideBackIdImage();
	}
	else if(type == "gsis_ecard"){
		showNumberId();
		hideBackIdImage();		
	}
	else if(type == "nbi"){
		showNumberId();
		hideBackIdImage();
	}
	else if(type == "ncwdp"){
		showNumberId();
		showBackIdImage();
	}
	else if(type == "ofw"){
		showNumberId();
		showBackIdImage();
	}
	else if(type == "owwa"){
		showNumberId();
		showBackIdImage();
	}
	else if(type == "passport"){
		showNumberId();
		hideBackIdImage();
	}
	else if(type == "police"){
		hideNumberDiv();
		showBackIdImage();
	}
	else if(type == "postal"){
		showNumberId();
		showBackIdImage();
	}
	else if(type == "prc"){
		showNumberId();
		hideBackIdImage();
	}
	else if(type == "seaman"){
		showNumberId();
		hideBackIdImage();
	}
	else if(type == "ssn"){
		hideNumberDiv();
		showBackIdImage();
	}
	else if(type == "umid"){
		hideNumberDiv(); 
		showBackIdImage();
	}
	else if(type == "voter"){
		hideNumberDiv();
		
		showBackIdImage();
	}
	else if(type == "alien"){
		hideNumberDiv();
		showBackIdImage();
	}
	else if(type == "bureau_of_fire_protection"){
		hideNumberDiv();
		showBackIdImage();
	}
	else if(type == "pnp"){
		hideNumberDiv();
		showBackIdImage();
	}
	else if(type == "integrated_bar"){
		hideNumberDiv();
		showBackIdImage();
	}
	else if(type == "philhealth"){
		hideNumberDiv();
		hideBackIdImage();
	}	
}

var presentBack = false;
var presentFront = false;

function  showPreviewFront(){

	var input = document.getElementById("fileUploadFrontId");

	var fReader = new FileReader();
	fReader.readAsDataURL(input.files[0]);
	fReader.onloadend = function(event) {
		if(!presentFront){
			$('#fileUploadFrontId').popover({
		        content: "<img src='"+event.target.result+"' style='width: 250px;height:200px;'>",
		        placement : 'bottom',
		        trigger: 'hover',
		        html:true
		      });
			presentFront = true;
		}else{
			var img = $('<img/>', {
				id : 'dynamic',
				width : 250,
				height : 200
			});
			img.attr('src', event.target.result);
			$('#fileUploadFrontId').attr("data-content",$(img)[0].outerHTML).popover("show");
		}
		$('#fImgId').val(event.target.result);
	}
}
function  showPreviewBack(){
	
	
	var input = document.getElementById("fileUploadBackId");

	var fReader = new FileReader();
	var t=fReader.readAsDataURL(input.files[0]);
	console.log(t);
	fReader.onloadend = function(event) {
		if(!presentBack){
			$('#fileUploadBackId').popover({
		        content: "<img src='"+event.target.result+"' style='width: 250px;height:200px;'>",
		        placement : 'bottom',
		        trigger: 'hover',
		        html:true
		      });
			presentBack = true;
		}else{
			var img = $('<img/>', {
				id : 'dynamic',
				width : 250,
				height : 200
			});
			img.attr('src', event.target.result);
			$('#fileUploadBackId').attr("data-content",$(img)[0].outerHTML).popover("show");
		}
		$('#bImgId').val(event.target.result);
	}
	
}

function showNumberId(){
	$('#numberIdDiv').show();
	$('#numberId').attr("required","required");
}

function hideNumberDiv(){
	$('#numberIdDiv').hide();
	$('#numberId').removeAttr("required");
	$('#numberId').val(''); 
}

function hideBackIdImage(){
	$('#backId').hide();
	$('#fileUploadBackId').removeAttr("required");
}

function showBackIdImage(){
	$('#backId').show();
	$('#fileUploadBackId').attr("required","required");
}

function removeFileFront(){
	$('#ffileNameLblId').popover('destroy');
	$('#ffileNameLblId').hide();
	$('#ffileNameLinkId').hide();
	$('#fileUploadFrontId').show();
}

function removeFileBack(){
	$('#bfileNameLblId').popover('destroy');
	$('#bfileNameLblId').hide();
	$('#bfileNameLinkId').hide();
	$('#fileUploadBackId').show();
}


