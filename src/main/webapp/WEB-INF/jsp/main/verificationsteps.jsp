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
  
  <form:form class="verification-form" modelAttribute="user" method="post" action="saveUserDetails">
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
            <select name="nationality" required="required" class="form-control">
			    <option value="">Please select an option</option>
			    <c:forEach var="country" items="${countryList}">
				   <option value="${country.key}" <c:if test="${user.nationality == country.key}">selected</c:if>>${country.value}</option>
				</c:forEach>
			  </select>
          </div>
          <div class="form-group">
            <label class="control-label"><spring:message code ="kyc.label.birthdate"/></label>
            <input  type="date" name="birthDateStr" required="required" class="form-control" value="${user.formattedBirthDate}">
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
               <select name="industry" id="industryId" required="required" class="form-control">
			    <option value="">Please select an option</option>
				<c:forEach var="industry" items="${industryList}">
				   <option value="${industry.key}" <c:if test="${user.industry == industry.key}">selected</c:if>>${industry.value}</option>
				</c:forEach>
			  </select>
          </div>
          
          <div class="form-group" id="sourceDivId" style="display:none">
            <label class="control-label"><spring:message code="kyc.label.fund.source"/></label>
            <select id="fundsourceId" name="fundSource" onchange="fundSourceChanged();"  class="form-control">
			    <option value="">Please select an option</option>
				<c:forEach var="source" items="${sourceList}">
				   <option value="${source.key}" <c:if test="${user.fundSource == source.key}">selected</c:if>>${source.value}</option>
				</c:forEach>
				
			 </select>
          </div>
          </div>
          
          <div class="col-xs-6">
          <div id="employedSubId">
	          <div class="form-group" >
	            <label class="control-label"><spring:message code="kyc.label.title.position"/></label>
	            <input type="text" name="position" id="titleId" placeholder="Title | Position" required="required" class="form-control"  value="${user.position}">
	          </div>
	          <div class="form-group">
	            <label class="control-label"><spring:message code="kyc.label.employer.name"/></label>
	            <input type="text" name="employerName" id="employerId" placeholder="Name of Employer" required="required" class="form-control" value="${user.employerName}">
	          </div>
	      </div>
          	 <div class="form-group" id="sourceOfFundExpId" style="display: none">
	            <label class="control-label"><spring:message code="kyc.label.source.explain"/></label>
	            <input type="text" name="fundSourceReason" id="subSourceOfFundExpId" placeholder="My funds will come from" class="form-control" value="${user.fundSourceReason}">
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
	            <select name="card[0].idType" id="idTypeId" required="required" onchange="idTypeChanged();" class="form-control">
	              	<option value="" disabled="disabled">Government Issued ID</option>
					<c:forEach var="id" items="${idList}">
					   <option value="${id.key}" <c:if test="${user.card[0].idType == id.key}">selected</c:if>>${id.value}</option>
					</c:forEach>
	          </select>
          </div>
          
          <div class="form-group">
            <label class="control-label"><spring:message code="kyc.label.id.expiration"/></label>
            <input  type="date" name="card[0].expDateStr" required="required" class="form-control"  value="${user.card[0].formattedExpDateStr}">
          </div>
          <div class="form-group" id="expireId">
            <label class="control-label"><spring:message code="kyc.label.id.number"/></label>
            <input type="text" name="card[0].idNumber" placeholder="ID number" required="required" class="form-control" value="${user.card[0].idNumber}">
          </div>
         </div> 
        <div class="col-xs-6">
          <br><br>
          <div class="form-group">
           <label class="control-label">Upload ID (front)</label>
            <input type="file" id="fileUploadFrontId" required="required" class="btn btn-default btn-md" onchange="showPreviewFront();">
          </div>
          <div class="form-group" id="backId">
           <label class="control-label">Upload ID (back)</label>
            <input type="file" id="fileUploadBackId" required="required" class="btn btn-default btn-md"  onchange="showPreviewBack();">
          </div>
          
        </div>
        </div>
          <button class="btn btn-primary prevBtn btn-lg pull-left" type="button"><spring:message code="kyc.btn.prev"/></button>
          <button class="btn btn-success btn-lg pull-right" type="submit"><spring:message code="kyc.btn.submit"/></button>
      </div>
    </div>
    <c:if test="${user.card[0] != null}">
    <input  type="hidden" name="card[0].id" value="${user.card[0].id}">
    </c:if>
    <input id="fImgId" type="hidden" name="card[0].frontImg" value="${user.card[0].frontImgStr}">
    <input id="bImgId"  type="hidden" name="card[0].backImg" value="${user.card[0].backImgStr}">
  </form:form>
</div>
</div>
</div>
</body>
<c:if test="${user.card[0].frontImg != null}">
<script type="text/javascript">
var frontImgVal = $('#fImgId').val();
$('#fileUploadFrontId').popover({
    content: "<img src='"+frontImgVal+"' style='width: 250px;height:200px;'>",
    placement : 'bottom',
    trigger: 'hover',
    html:true
  });
presentBack = true;
</script>

</c:if>
<c:if test="${user.card[0].backImg != null}">
<script type="text/javascript">
var frontImgVal = $('#bImgId').val();
$('#fileUploadBackId').popover({
    content: "<img src='"+frontImgVal+"' style='width: 250px;height:200px;'>",
    placement : 'bottom',
    trigger: 'hover',
    html:true
  });
presentBack = true;
</script>
</c:if>




