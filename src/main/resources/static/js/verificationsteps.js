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
		$('#sourceDivId').hide();
		$('#fundsourceId').removeAttr("required");
		$('#fundsourceId').val('');
		
		$('#industryDivId').show();
		$('#industryId').attr("required","required");
		
		$('#employedSubId').show();
		$('#titleId').attr("required","required");
		$('#employerId').attr("required","required");
		
	}else if(status == 'selfemployed'){
		$('#sourceDivId').hide();
		$('#fundsourceId').removeAttr("required");
		$('#fundsourceId').val('');
		
		$('#industryDivId').show();
		$('#industryId').attr("required","required");
		
		$('#employedSubId').hide();
		$('#titleId').removeAttr("required");
		$('#employerId').removeAttr("required");
		$('#titleId').val('');
		$('#employerId').val('');
		
	}else if(status == 'retired'|| status == 'unemployed'|| status == 'student'){
		$('#sourceDivId').show();
		$('#fundsourceId').attr("required","required");
		
		$('#industryDivId').hide();
		$('#industryId').removeAttr("required");
		$('#industryId').val('');
		
		$('#employedSubId').hide();
		$('#titleId').removeAttr("required");
		$('#employerId').removeAttr("required");
		$('#titleId').val('');
		$('#employerId').val('');
	}
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
		$('#expireId').show();
		$('#expireId').attr("required","required");

		$('#backId').show();
	}
	else if(type == "drivers_license"){
		$('#expireId').show();
		$('#expireId').attr("required","required");
		
		$('#backId').hide();
		$('#backId').removeAttr("required");
		
	}
	else if(type == "gsis_ecard"){
		$('#expireId').show();
		$('#expireId').attr("required","required");
		
		$('#backId').hide();
		$('#backId').removeAttr("required");
		
	}
	else if(type == "nbi"){
		$('#expireId').show();
		$('#expireId').attr("required","required");
		
		$('#backId').hide();
	}
	else if(type == "ncwdp"){
		$('#expireId').show();
		$('#expireId').attr("required","required");
		
		$('#backId').show();
	}
	else if(type == "ofw"){
		$('#expireId').show();
		$('#expireId').attr("required","required");
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "owwa"){
		$('#expireId').show();
		$('#expireId').attr("required","required");
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "passport"){
		$('#expireId').show();
		$('#expireId').attr("required","required");
		
		$('#backId').hide();
		$('#backId').removeAttr("required");
		
	}
	else if(type == "police"){
		$('#expireId').hide();
		$('#expireId').removeAttr("required");
		$('#expireId').val('');
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "postal"){
		$('#expireId').show();
		$('#expireId').attr("required","required");
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "prc"){
		$('#expireId').show();
		$('#expireId').attr("required","required");
		
		$('#backId').hide();
		$('#backId').removeAttr("required");
		
	}
	else if(type == "seaman"){
		$('#expireId').show();
		$('#expireId').attr("required","required");
		
		$('#backId').hide();
		$('#backId').removeAttr("required");
	}
	else if(type == "ssn"){
		$('#expireId').hide();
		$('#expireId').removeAttr("required");
		$('#expireId').val('');
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "umid"){
		$('#expireId').hide();
		$('#expireId').removeAttr("required");
		$('#expireId').val('');
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "voter"){
		$('#expireId').hide();
		$('#expireId').removeAttr("required");
		$('#expireId').val('');
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "alien"){
		$('#expireId').hide();
		$('#expireId').removeAttr("required");
		$('#expireId').val('');
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "bureau_of_fire_protection"){
		$('#expireId').hide();
		$('#expireId').removeAttr("required");
		$('#expireId').val('');
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "pnp"){
		$('#expireId').hide();
		$('#expireId').removeAttr("required");
		$('#expireId').val('');
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "integrated_bar"){
		$('#expireId').hide();
		$('#expireId').removeAttr("required");
		$('#expireId').val('');
		
		$('#backId').show();
		$('#backId').attr("required","required");
	}
	else if(type == "philhealth"){
		$('#expireId').hide();
		$('#expireId').removeAttr("required");
		$('#expireId').val(''); 
		
		$('#backId').hide();
		$('#backId').removeAttr("required");
		
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


