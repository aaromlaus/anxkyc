<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
<script src="/js/myaccount.js"></script>
</head>
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/headermenu.jsp"></jsp:include>
<div class="inner contact">
	<div class="col-sm-12 clearfix row">
		<div id="main-user">
			<c:if test="${not empty msgDetails}">
				<div class="${msgCss }">${msgDetails }</div>
			</c:if>

			<div id="form-block">
				<h1>
					<spring:message code="kyc.label.current.limits"/> <strong>${anxUser.userLevel.description}</strong>
				</h1>
				<form:hidden path="anxUser.emailAddress" id="currentEmail" />
				<input type="hidden" value="userdashBoard" id="currentPage">				
				<form:hidden path="anxUser.phoneNumber" id="currentPhone" />
				<div class="clear"></div>
				<div class="col-sm-12 bg-info">
					<table class="table table-hover" id="levelTable">
						<thead>
							<tr>
								<th colspan="2" style="text-align: center;"><spring:message code="kyc.label.cash.out"/></th>
								<th colspan="2" style="text-align: center;"><spring:message code="kyc.label.cash.in"/></th>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td>Daily (24 hours)</td>
								<td>PHP 0 remaining</td>
								<td>Daily (24 hours)</td>
								<td>PHP ${anxUser.userLevel.cashIn } remaining</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-12 clearfix row">
		<div id="main-user">
			<div id="form-block">
				<h1><spring:message code="kyc.label.increase.limit"/></h1>
				<div class="clear"></div>
				<div class="col-sm-12 bg-info">
					<table class="table table-hover" id="levelTable">
						<thead>
							<tr>
								<th class="col-sm-2"><spring:message code="kyc.label.levels"/></th>
								<th><spring:message code="kyc.label.requirements"/></th>
								<th>Cash In (PHP)</th>
								<th>Cash Out (PHP)</th>
								<th><spring:message code="kyc.label.limits"/></th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${userLevels}" var="userLevels">
								<c:if test="${not userLevels.userLevelName.contains('pending')}">
									<tr class="accordion-toggle" data-toggle="collapse"
										data-target="#${userLevels.userLevelId}">
										<td class="col-sm-2">Level <c:out
												value="${userLevels.userLevelGroup}" />
										</td>
										<td><c:out value="${userLevels.requirement}" /></td>
										<td><c:out value="${userLevels.cashIn}" /></td>
										<td><c:out value="${userLevels.cashOut}" /></td>
										<td><span
											class="${(userLevels.userLevelGroup <= anxUser.userLevel.userLevelGroup) ? 'glyphicon glyphicon-ok':''}"></span>&nbsp
										</td>
									</tr>
								</c:if>
								<c:if
									test="${userLevels.userLevelName.equalsIgnoreCase('level1')}">
									<tr class="collapse out ${userLevels.userLevelId}"
										id="${userLevels.userLevelId}">
										<td colspan="5" class="container">
											<div class="col-sm-12 bg-grey-pad-6">
												<div class="text-center">
													<span
														class="glyphicon ${anxUser.phoneNumber.length() >0 ? 'glyphicon-phone':'glyphicon-envelope'}  font-size-60 ">

													</span> <br />
													<p class="mar-top-10">${anxUser.phoneNumber.length() >0 ? 'Phone Verification':'Email Verification'}</p>
													<a class="btn btn-success mar-top-10">Completed</a>
												</div>
											</div>
										</td>
									</tr>
								</c:if>
								<c:if
									test="${userLevels.userLevelName.equalsIgnoreCase('level2')}">
									<tr class="collapse out ${userLevels.userLevelId}"
										id="${userLevels.userLevelId}">
										<td colspan="5 " class="container bg-grey-pad-6">
											<div class="col-sm-3 pad-l-r-0">
												<div class="text-center">
													<c:if
														test="${anxUser.emailAddress.length() > 0 && anxUser.phoneNumber.length() > 0}">
														<span class="glyphicon  glyphicon-envelope font-size-60">

														</span>
														<br />
														<p class="mar-top-10">Email Verification</p>
														<a class="btn btn-success mar-top-10">Completed</a>
													</c:if>
													<c:if
														test="${anxUser.emailAddress.length() == 0 || anxUser.phoneNumber.length() == 0}">
														<span
															class="glyphicon  ${anxUser.emailAddress.length() >0 ? 'glyphicon-phone':'glyphicon-envelope'} font-size-60">

														</span>
														<br />
														<p>${anxUser.emailAddress.length() > 0 ? 'Phone Verification':'Email Verification'}</p>
														<c:if
															test="${(userLevels.userLevelGroup <= anxUser.userLevel.userLevelGroup)}">
															<a class="btn btn-success mar-top-10">Completed</a>
														</c:if>
														<c:if
															test="${!(userLevels.userLevelGroup <= anxUser.userLevel.userLevelGroup)}">
															<a class="btn btn-primary mar-top-10" data-toggle="modal"
																data-target="${anxUser.emailAddress.length() > 0 ? '#phoneVerification':'#sendEmailCode'}"
																onClick="clearErrorMessage();">Verify</a>
														</c:if>
													</c:if>
												</div>
											</div>
											<div class="col-sm-4 pad-l-r-0">
												<div class="text-center">
													<span class="glyphicon glyphicon-user font-size-60">

													</span> <br />
													<p class="mar-top-10">Identification Verification</p>
													<c:if
														test="${(userLevels.userLevelGroup <= anxUser.userLevel.userLevelGroup)}">

														<a class="btn btn-success mar-top-10">Completed</a>
													</c:if>
													<c:if
														test="${!(userLevels.userLevelGroup <= anxUser.userLevel.userLevelGroup)}">
														<a class="btn btn-primary mar-top-10">Verify</a>
													</c:if>
												</div>
											</div>
											<div class="col-sm-5 pad-l-r-0">
												<form:form method="POST" action="/profile/upload/"
													enctype="multipart/form-data">
													<div class="level-2-grp">
														<h3 class="row-header-headings"><spring:message code="kyc.label.image.upload.verification"/></h3>
														<div class="input-group image-preview">
															<input type="text"
																class="form-control image-preview-filename"
																style="width: 100%;" disabled="disabled">
															<!-- don't give a name === doesn't send on POST/GET -->
															<div class="input-group-btn">
																<!-- image-preview-clear button -->
																<button type="button"
																	class="btn btn-default image-preview-clear"
																	style="display: none;">
																	<span class="glyphicon glyphicon-remove"></span><spring:message code="kyc.label.clear"/>
																</button>
																<!-- image-preview-input -->
																<div class="btn btn-default image-preview-input">
																	<span class="glyphicon glyphicon-folder-open"></span> <span
																		class="image-preview-input-title"><spring:message code="kyc.label.browse"/></span> <input
																		type="file" accept="image/png, image/jpeg, image/gif"
																		name="file" />
																	<!-- rename it -->
																</div>
																<button type="submit" class="btn btn-primary start"
																	data-ng-click="submit()">
																	<i class="glyphicon glyphicon-upload"></i> <span><spring:message code="kyc.label.start.upload"/></span>
																</button>
															</div>
														</div>
													</div>
												</form:form>
											</div>
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</div>
			</div>
		</div>
	</div>

</div>
<jsp:include page="../modal/sendEmailCodeModal.jsp"></jsp:include>
<jsp:include page="../modal/sendPhoneCodeModal.jsp"></jsp:include>
<jsp:include page="../modal/codeVerificationModal.jsp"></jsp:include>
<script>
	$(document).on('click', '#close-preview', function() {
		$('.image-preview').popover('hide');
		// Hover befor close the preview
		$('.image-preview').hover(function() {
			$('.image-preview').popover('show');
		}, function() {
			$('.image-preview').popover('hide');
		});
	});

	$(function() {
		// Create the close button
		var closebtn = $('<button/>', {
			type : "button",
			text : 'x',
			id : 'close-preview',
			style : 'font-size: initial;',
		});
		closebtn.attr("class", "close pull-right");
		// Set the popover default content
		$('.image-preview').popover({
			trigger : 'manual',
			html : true,
			title : "<strong>Preview</strong>" + $(closebtn)[0].outerHTML,
			content : "There's no image",
			placement : 'bottom'
		});
		// Clear event
		$('.image-preview-clear').click(function() {
			$('.image-preview').attr("data-content", "").popover('hide');
			$('.image-preview-filename').val("");
			$('.image-preview-clear').hide();
			$('.image-preview-input input:file').val("");
			$(".image-preview-input-title").text("Browse");
		});
		// Create the preview image
		$(".image-preview-input input:file").change(
				function() {
					var img = $('<img/>', {
						id : 'dynamic',
						width : 250,
						height : 200
					});
					var file = this.files[0];
					var reader = new FileReader();
					// Set preview image into the popover data-content
					reader.onload = function(e) {
						$(".image-preview-input-title").text("Change");
						$(".image-preview-clear").show();
						$(".image-preview-filename").val(file.name);
						img.attr('src', e.target.result);
						$(".image-preview").attr("data-content",
								$(img)[0].outerHTML).popover("show");
					}
					reader.readAsDataURL(file);
				});
	});
</script>


