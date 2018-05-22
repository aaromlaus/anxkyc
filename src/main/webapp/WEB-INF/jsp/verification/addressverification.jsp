<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
<link rel="stylesheet" href="/css/addressverification.css">
<script src="/js/addressverification.js"></script>
<style type="text/css">
.container {
    min-height: 100px;
    white-space: nowrap;
       display: table;
   height: auto;
}
</style>
</head>

<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<jsp:include page="../common/headermenu.jsp"></jsp:include>
	<div class="inner contact">
		<div class="col-sm-12 clearfix row">
			<c:if test="${not empty msgDetails}">
				<div class="${msgCss }">${msgDetails }</div>
			</c:if>
		</div>

		<div class="container" id="containerId">
			<div class="stepwizard">
				<div class="stepwizard-row stepwizard-row-single setup-panel">
					
					<div class="stepwizard-step" style="width:100%; height:95%;">
						<a href="#identification" type="button"
							class="btnx btn-circle"> <i id="step2btn"
							class="fa fa-home" style="font-size: 50px; color: #337ab7"></i>
						</a>
						<p class="side-border">Address Verification</p>
					</div>
				</div>
			</div>
			
			  <form:form class="verification-form" modelAttribute="user"  style="height:75%;" method="post" action="submitAddressVerification">
					<div class="verification-row" style="text-align: center;">
						<h4>Please provide your complete, current residential address.</h4>
						<div id="currentAddressId">
							<jsp:include page="../verification/addresstable.jsp">
								<jsp:param value="current" name="prefix"/>
								<jsp:param value="${user.currentAddress.unitNo}" name="unitNo"/>
								<jsp:param value="${user.currentAddress.city}" name="city"/>
								<jsp:param value="${user.currentAddress.state}" name="state"/>
								<jsp:param value="${user.currentAddress.postalCode}" name="postalCode"/>
								<jsp:param value="${user.currentAddress.country}" name="country"/>
							</jsp:include>
							
						</div>
						<h4>Please provide your complete, current permanent address.</h4>
						<label><input type="checkbox" name="currentAddressSame" id="sameAddressId" onchange="sameAddress();" <c:if test='${user.currentAddressSame}'>checked</c:if>> My permanent address is the same as my residential address</input></label>
						<div id="permanentAddressId" <c:if test='${user.currentAddressSame}'>style="display:none"</c:if>>
							<jsp:include page="../verification/addresstable.jsp">
								<jsp:param value="permanent" name="prefix"/>
								<jsp:param value="${user.permanentAddress.unitNo}" name="unitNo"/>
								<jsp:param value="${user.permanentAddress.city}" name="city"/>
								<jsp:param value="${user.permanentAddress.state}" name="state"/>
								<jsp:param value="${user.permanentAddress.postalCode}" name="postalCode"/>
								<jsp:param value="${user.permanentAddress.country}" name="country"/>
							</jsp:include>
						</div>
						<h4>Please choose a document type and upload a clear image of your document. <br>
						Kindly make sure that the document is registered under your residential address.</h4>
						<table  style="margin: 0 auto; width: 55%">
							<tr>
								<td>
									<label class="mar-t-10">Type of Document</label>
									<select  required="required" name="addressVerificationDoc.type">
										<option value="">Select Type of Document</option>
										<c:forEach var="docType" items="${docTypeList}">
										   <option value="${docType.key}" <c:if test="${user.addressVerificationDoc.type eq docType.key}">selected</c:if>>${docType.value}</option>
										</c:forEach>
									</select>
								</td>
							</tr>
							<tr>
								<td>
									<div id="docUploadId">
										<c:if test="${empty user.addressVerificationDoc.fileName}">
											<input  required="required" type="file" class="form-control btn btn-default btn-md" id="docFileId" name="addressVerificationDoc.fileName" value="${user.addressVerificationDoc.fileName }" onchange="showDocPreview()" >
							          	</c:if>
							          	<c:if test="${not empty user.addressVerificationDoc.fileName}">
							          		<br>
							          		<input type="text" readonly class="fileName mar-l-20" name="addressVerificationDoc.fileName"  id="docFileLblId" value="${user.addressVerificationDoc.fileName}"/>
							          		<a href="#" class="mar-l-10" onclick="removeDocument();" id="docFileLinkId" title="Remove">
									          <span class="glyphicon glyphicon-remove"></span>
									        </a>
							          	</c:if>
									</div>
								</td>
							</tr>
							<tr>
								<td>
								    <label class="mar-t-20">Expiration Date</label>
									<input   required="required" type="date" name="addressVerificationDoc.expirationDateStr" value="${user.addressVerificationDoc.formattedExpirationDate}">
								</td>
							</tr>
							<tr>
								<td colspan="3">
								    <label class="mar-b-50 sm mar-r-15" >Does your document show your own name?</label>
									<input type="radio" value="Yes" name="addressVerificationDoc.documentShowsName" <c:if test="${user.addressVerificationDoc.documentShowsName}">checked</c:if>> <label class="sm">Yes</label>
  			 						<input type="radio" value="No"  name="addressVerificationDoc.documentShowsName" <c:if test="${not user.addressVerificationDoc.documentShowsName}">checked</c:if>> <label class="sm">No</label>
								</td>
							</tr>
						</table>
				          <button class="btn btn-default btn-sm " type="button">Cancel</button>
				          <button class="btn btn-success btn-sm " type="submit">Submit</button>
					</div>
					<input id="fileStr" required="required" type="hidden" name="addressVerificationDoc.file" value="${user.addressVerificationDoc.fileStr}">
			</form:form>
		</div>
		
	</div>
</body>
<c:if test="${not empty user.addressVerificationDoc.fileName}">
	<script type="text/javascript">
	var docFile = $('#fileStr').val();
	$('#docFileLblId').popover({
	    content: "<img src='"+docFile+"' style='width: 250px;height:200px;'>",
	    placement : 'bottom',
	    trigger: 'hover',
	    html:true
	  });
	</script>
</c:if>



