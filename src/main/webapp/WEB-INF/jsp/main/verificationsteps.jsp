<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
<link rel="stylesheet" href="/css/verificationsteps.css">
<script src="/js/verificationsteps.js"></script>
	
<title><spring:message code="kyc.verification.steps.title"/></title>
</head>

  <body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/headermenu.jsp"></jsp:include>
<div class="inner contact">
	<div class="col-sm-12 clearfix row">		
<!-- 		<div id="main-user"> -->
			<c:if test="${not empty msgDetails}">
				<div class="${msgCss }">
				    ${msgDetails }
				</div>
			</c:if>
			
<div class="container">
  
<div class="stepwizard">
    <div class="stepwizard-row setup-panel">
      <div class="stepwizard-step arrow-left-div">
        <a href="#step-1" type="button" class="btn1 btn-primary1 btn-circle">
        	<i id="step1btn" class="fa fa-question-circle" style="font-size:55px;color:#337ab7"></i>
        </a>
        <p><spring:message code ="kyc.label.general"/></p>
      </div>
      <div class="stepwizard-step arrow-left-div">
        <a href="#step-2" type="button" class="btn1 btn-default1 btn-circle disabledLink">
			<i id="step2btn"  class="fa fa-money" style="font-size:50px;color:#337ab7"></i>
        </a>
        <p class="side-border"><spring:message code ="kyc.label.source"/></p>
      </div>
      <div class="stepwizard-step ">
        <a href="#step-3" type="button" class="btn1 btn-default1 btn-circle disabledLink">
			<i id="step3btn"  class="fa fa-id-card-o" style="font-size:45px;color:#337ab7"></i>
        </a>
        <p><spring:message code ="kyc.label.id.upload"/></p>
      </div>
    </div>
  </div>
  
  <form role="form" class="verification-form" method="post">
    <div class="row setup-content" id="step-1">
      <div class="col-xs-12">
        <div class="col-md-6 col-div">
          <h3> <spring:message code ="kyc.label.general"/></h3>
          <br>
          <div class="form-group">
            <label class="control-label"><spring:message code ="kyc.label.gender"/></label><br>
              <input type="radio" required="required"  name="gender" value="male" checked> <spring:message code ="kyc.label.gender.male"/>
  			  <input type="radio" required="required"  name="gender" value="female"> <spring:message code ="kyc.label.gender.female"/>
          </div>
          <div class="form-group">
            <label class="control-label"><spring:message code="kyc.label.nationality"/></label>
            <select name="nationality" required="required">
			    <option value="">Please select an option</option>
			    <option value="Philippines">Philippines</option>
			    <option value="Australia">Australia</option>
			    <option value="New Zealand">New Zealand</option>
			    <option value="India">India</option>
			  </select>
          </div>
          <div class="form-group">
            <label class="control-label"><spring:message code ="kyc.label.birthdate"/></label>
            <input  type="date" required="required" class="form-control">
          </div>
      </div>
    </div>
      <button class="btn btn-primary nextBtn btn-lg pull-right" type="button"><spring:message code ="kyc.btn.next"/></button>
    </div>
    <div class="row setup-content" id="step-2">
      <div class="col-xs-12">
        <div class="col-md-12">
          <h3> <spring:message code="kyc.label.source"/></h3>
          <br>
          <div class="col-xs-6 col-div">
          <div class="form-group">
            <label class="control-label"><spring:message code="kyc.label.employment.status"/></label><br>
              <input type="radio" required="required" onchange="employmentChanged();" name="employmentStatus" value="employed" checked> Employed
  			  <input type="radio" required="required" onchange="employmentChanged();"  name="employmentStatus" value="selfemployed"> Self-employed
              <input type="radio" required="required" onchange="employmentChanged();"  name="employmentStatus" value="retired"> Retired
  			  <input type="radio" required="required" onchange="employmentChanged();"  name="employmentStatus" value="unemployed"> Unemployed
              <input type="radio" required="required" onchange="employmentChanged();"  name="employmentStatus" value="student"> Student
          </div>
          <div class="form-group" id="industryDivId">
            <label class="control-label"><spring:message code="kyc.label.industry"/></label>
               <select name="industry" required="required">
			    <option value="">Please select an option</option>
			    <option value="accounting_and_finance">Accounting and Finance</option>
			    <option value="administrative">Administrative</option>
			    <option value="architecture_and_engineering">Architecture and Engineering</option>
			    <option value="arts_and_sports">Arts and Sports</option>
			    <option value="construction">Construction</option>
			    <option value="customer_service">Customer Service</option>
			    <option value="education_and_training">Education and Training</option>
			    <option value="general_service">General Service</option>
			    <option value="health_and_medical">Health and Medical</option>
			    <option value="hospitality_and_tourism">Hospitality and Tourism</option>
			    <option value="household">Household (helper, driver)</option>
			    <option value="human_resources">Human Resources</option>
			    <option value="it_and_software">IT and Software</option>
			    <option value="legal">Legal</option>
			    <option value="management_and_consultancy">Management and Consultancy</option>
			    <option value="manufacturing_and_production">Manufacturing and Production</option>
			    <option value="media_and_creatives">Media and Creatives</option>
			    <option value="public_service_and_ngos">Public Service and NGOs</option>
			    <option value="safety_and_security">Safety and Security</option>
			    <option value="sales_and_marketing">Sales and Marketing</option>
			    <option value="sciences">Sciences</option>
			    <option value="supply_chain">Supply Chain</option>
			    <option value="writing_and_content">Writing and Content</option>
			  </select>
          </div>
          
          <div class="form-group" id="sourceDivId" style="display:none">
            <label class="control-label"><spring:message code="kyc.label.fund.source"/></label>
            <select id="fundsourceId" name="fundsource" required="required" onchange="fundSourceChanged();">
			    <option value="">Please select an option</option>
			    <option value="allowance">Allowance</option>
			    <option value="government_subsidy">Government Subsidy</option>
			    <option value="other">Other</option>
			    <option value="pension">Pension</option>
			    <option value="remittances">Remittances</option>
			    <option value="scholarship_stipend">Scholarship/Stipend</option>
			    <option value="separation_pay">Separation Pay</option>
			  </select>
          </div>
          </div>
          
          <div class="col-xs-6">
          <div id="employedSubId">
	          <div class="form-group" >
	            <label class="control-label"><spring:message code="kyc.label.title.position"/></label>
	            <input type="text" placeholder="Title | Position" required="required" class="form-control">
	          </div>
	          <div class="form-group">
	            <label class="control-label"><spring:message code="kyc.label.employer.name"/></label>
	            <input type="text" placeholder="Name of Employer" required="required" class="form-control">
	          </div>
	          </div>
          	 <div class="form-group" id="sourceOfFundExpId" style="display: none">
	            <label class="control-label"><spring:message code="kyc.label.source.explain"/></label>
	            <input type="text" placeholder="My funds will come from" required="required" class="form-control">
	          </div>
          </div>
          <div class="col-xs-12">
          	<button class="btn btn-primary prevBtn btn-lg pull-left" type="button"><spring:message code="kyc.btn.prev"/></button>
          	<button class="btn btn-primary nextBtn btn-lg pull-right" type="button"><spring:message code="kyc.btn.next"/></button>
          </div>
        </div>
      </div>
    </div>
    <div class="row setup-content" id="step-3">
      <div class="col-xs-12">
        <div class="col-md-12">
          <h3> <spring:message code="kyc.label.upload.id"/></h3>
          <br>
          <div class="col-xs-6 col-div">
          <div class="form-group">
            <label class="control-label"><spring:message code="kyc.label.id.type"/></label><br>
	            <select name="idType" id="idTypeId" required="required" onchange="idTypeChanged();">
	              	<option value="" disabled="disabled">Government Issued ID</option>
	                <option value="afp">Armed Forces of the Philippines (AFP) ID</option>
	                <option value="drivers_license">Driver's License</option>
	                <option value="gsis_ecard">Government Service Insurance System (GSIS) e-Card Plus2</option>
	                <option value="nbi">National Bureau of Investigation (NBI) clearance</option>
	                <option value="ncwdp">Certification from the National Council for the Welfare of Disabled Persons (NCWDP)</option>
	                <option value="ofw">OFW E-Card</option>
	                <option value="owwa">Overseas Workers Welfare Administration (OWWA) ID</option>
	                <option value="passport">Passport</option>
                    <option value="police">Police Clearance Certificate</option>
	                <option value="postal">Postal ID</option>
	                <option value="prc">Professional Regulation Commission (PRC) ID</option>
	                <option value="seaman">Seaman's Book</option>
	                <option value="ssn">Social Security System (SSS) Card</option>
	                <option value="umid">Unified Multi-Purpose ID</option>
	                <option value="voter">Voter's ID</option>
	                <option value="alien">Alien Certificate of Registration</option>
	                <option value="bureau_of_fire_protection">Bureau of Fire Protection ID</option>
	                <option value="pnp">Philippine National Police ID</option>
	                <option value="integrated_bar">Integrated Bar of the Philippines ID</option>
	                <option value="philhealth">PhilHealth ID</option>
	          </select>
          </div>
          
          <div class="form-group">
            <label class="control-label"><spring:message code="kyc.label.id.expiration"/></label>
            <input type="text" placeholder="Expiration date" required="required" class="form-control">
          </div>
          <div class="form-group" id="expireId">
            <label class="control-label"><spring:message code="kyc.label.id.number"/></label>
            <input type="text" placeholder="ID number" required="required" class="form-control">
          </div>
         </div> 
        <div class="col-xs-6">
          <br><br>
          <div class="form-group">
            <input type="button" value="Upload ID (front)" required="required" class="btn btn-default btn-md">
          </div>
          <div class="form-group" id="backId">
            <input type="button" value="Upload ID (back)" required="required" class="btn btn-default btn-md">
          </div>
          
        </div>
        </div>
          <button class="btn btn-primary prevBtn btn-lg pull-left" type="button"><spring:message code="kyc.btn.prev"/></button>
          <button class="btn btn-success btn-lg pull-right" type="submit"><spring:message code="kyc.btn.submit"/></button>
      </div>
    </div>
  </form>
  
			</div>
</div>
			</div>
</body>

<script type="text/javascript">

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
</script>



