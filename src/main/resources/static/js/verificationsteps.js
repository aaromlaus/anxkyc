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
          curInputs = curStep.find("input[type='text'],input[type='url']"),
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
		$('#industryDivId').show();
		$('#employedSubId').show();
	}else if(status == 'selfemployed'){
		$('#sourceDivId').hide();
		$('#industryDivId').show();
		$('#employedSubId').hide();
	}else if(status == 'retired'|| status == 'unemployed'|| status == 'student'){
		$('#sourceDivId').show();
		$('#industryDivId').hide();
		$('#employedSubId').hide();
	}
}

function fundSourceChanged(){
	var source = $('#fundsourceId').val();
	
	if(source == 'other'){
		$('#sourceOfFundExpId').show();
	}else{
		$('#sourceOfFundExpId').hide();
	}
}

function idTypeChanged(){
	var type = $('#idTypeId').val()
	console.log(type);
	if(type == "afp"){
		$('#expireId').show();
		$('#backId').show();
	}
	else if(type == "drivers_license"){
		$('#expireId').show();
		$('#backId').hide();
	}
	else if(type == "gsis_ecard"){
		$('#expireId').show();
		$('#backId').hide();
	}
	else if(type == "nbi"){
		$('#expireId').show();
		$('#backId').hide();
	}
	else if(type == "ncwdp"){
		$('#expireId').show();
		$('#backId').show();
	}
	else if(type == "ofw"){
		$('#expireId').show();
		$('#backId').show();
	}
	else if(type == "owwa"){
		$('#expireId').show();
		$('#backId').show();
	}
	else if(type == "passport"){
		$('#expireId').show();
		$('#backId').hide();
	}
	else if(type == "police"){
		$('#expireId').hide();
		$('#backId').show();
	}
	else if(type == "postal"){
		$('#expireId').show();
		$('#backId').show();
	}
	else if(type == "prc"){
		$('#expireId').show();
		$('#backId').hide();
	}
	else if(type == "seaman"){
		$('#expireId').show();
		$('#backId').hide();
	}
	else if(type == "ssn"){
		$('#expireId').hide();
		$('#backId').show();
	}
	else if(type == "umid"){
		$('#expireId').hide();
		$('#backId').show();
	}
	else if(type == "voter"){
		$('#expireId').hide();
		$('#backId').show();
	}
	else if(type == "alien"){
		$('#expireId').hide();
		$('#backId').show();
	}
	else if(type == "bureau_of_fire_protection"){
		$('#expireId').hide();
		$('#backId').show();
	}
	else if(type == "pnp"){
		$('#expireId').hide();
		$('#backId').show();
	}
	else if(type == "integrated_bar"){
		$('#expireId').hide();
		$('#backId').show();
	}
	else if(type == "philhealth"){
		$('#expireId').hide();
		$('#backId').hide();
	}

	
}