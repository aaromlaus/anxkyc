<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
</head>
<br />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/headermenu.jsp"></jsp:include>
<div class="inner contact">
	<div class="col-sm-12 clearfix row">		
		<div id="main-user">
			<c:if test="${not empty msgDetails}">
				<div class="${msgCss }">
				    ${msgDetails }
				</div>
			</c:if>
			<div class="bg-primary text-center">				
				Your Current Limits: <strong>${anxUser.userLevel.description}</strong>
			</div>
			<div class="clear"></div>
			<div class="col-sm-12 bg-info">
				<div class="col-sm-6 text-center">
					<span class="col-sm-12 font-weight-bold">
						<strong>Cash Out</strong>
					</span>
					<div class="clear"></div>
					<span class="col-sm-6">
						Daily (24 hours)
					</span>
					<span class="col-sm-6">
						PHP 0 remaining
					</span>
				</div>
				<div class="col-sm-6 text-center">
					<strong>Cash In</strong>
					<div class="clear"></div>
					<span class="col-sm-6">
						Daily (24 hours)
					</span>
					<span class="col-sm-6">
						PHP 2000 remaining
					</span>
				</div>
			</div>		
		</div>
	</div>
	<div class="col-sm-12 clearfix row">		
		<div id="main-user ">
			<div class="col-sm-12 text-center color-white-text">
				To increase your limits, click a level and complete the steps below..
			</div>
		</div>
	</div>	
	<div class="col-sm-12 clearfix row">		
		<div id="main-user">
			<div class="bg-primary text-center">				
				Your Current Limits: <strong>${anxUser.userLevel.description}</strong>
			</div>
			<div class="clear"></div>
			<div class="col-sm-12 bg-info">
				<table class="table table-hover" id="levelTable">
					 <thead>
				      <tr>
				        <th class="col-sm-2">Levels</th>
				        <th>Requirements</th>
				        <th>Cash In (PHP)</th>
				        <th>Cash Out (PHP)</th>
				        <th>Limits</th>
				      </tr>
				    </thead>
				    <tbody>
				    	<c:forEach items="${userLevels}" var="userLevels">
					    	<c:if test="${not userLevels.userLevelName.contains('pending')}">
						    	<tr class="accordion-toggle" data-toggle="collapse" data-target="#${userLevels.userLevelId}">
									<td class="col-sm-2">
							           Level <c:out value = "${userLevels.userLevelGroup}"/>
							        </td>
							        <td>
							         <c:out value = "${userLevels.requirement}"/>
							       	</td>
							        <td>
							          <c:out value = "${userLevels.cashIn}"/>
							        </td>
							        <td>
							          <c:out value = "${userLevels.cashOut}"/>
							        </td>
							        <td>
							        	<span class="${(userLevels.userLevelGroup <= anxUser.userLevel.userLevelGroup) ? 'glyphicon glyphicon-ok':''}"></span>&nbsp
							        </td>
							    </tr>
						    </c:if>
						    <c:if test="${userLevels.userLevelName.equalsIgnoreCase('level2')}">
							    <tr class="collapse out ${userLevels.userLevelId}" id="${userLevels.userLevelId}">
							    	<td colspan="5">
								    	<form:form method="POST" action="/profile/upload/" enctype="multipart/form-data">
								    		
								    			<!-- <label>Upload Image</label>
								    			<img id="img-upload" />
								    			<div class="input-group">
								    				<div class="btn btn-default btn-file">
								    					Browse <input type="file" id="imgInp">
								    				</div>
								    				<input type="text" class="form-control" readonly>
								    			</div> -->
								    	<div class="input-group image-preview">
							                <input type="text" class="form-control image-preview-filename" disabled="disabled"> <!-- don't give a name === doesn't send on POST/GET -->
							                <span class="input-group-btn">
							                    <!-- image-preview-clear button -->
							                    <button type="button" class="btn btn-default image-preview-clear" style="display:none;">
							                        <span class="glyphicon glyphicon-remove"></span> Clear
							                    </button>
							                    <!-- image-preview-input -->
							                    <div class="btn btn-default image-preview-input">
							                        <span class="glyphicon glyphicon-folder-open"></span>
							                        <span class="image-preview-input-title">Browse</span>
							                        <input type="file" accept="image/png, image/jpeg, image/gif" name="file"/> <!-- rename it -->
							                    </div>
							                </span>
							            </div>		
								    				<button type="submit" class="btn btn-primary start" data-ng-click="submit()">
									                    <i class="glyphicon glyphicon-upload"></i>
									                    <span>Start upload</span>
									                </button>							    
										</form:form>
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

 <script>
 $(document).on('click', '#close-preview', function(){ 
	    $('.image-preview').popover('hide');
	    // Hover befor close the preview
	    $('.image-preview').hover(
	        function () {
	           $('.image-preview').popover('show');
	        }, 
	         function () {
	           $('.image-preview').popover('hide');
	        }
	    );    
	});

	$(function() {
	    // Create the close button
	    var closebtn = $('<button/>', {
	        type:"button",
	        text: 'x',
	        id: 'close-preview',
	        style: 'font-size: initial;',
	    });
	    closebtn.attr("class","close pull-right");
	    // Set the popover default content
	    $('.image-preview').popover({
	        trigger:'manual',
	        html:true,
	        title: "<strong>Preview</strong>"+$(closebtn)[0].outerHTML,
	        content: "There's no image",
	        placement:'bottom'
	    });
	    // Clear event
	    $('.image-preview-clear').click(function(){
	        $('.image-preview').attr("data-content","").popover('hide');
	        $('.image-preview-filename').val("");
	        $('.image-preview-clear').hide();
	        $('.image-preview-input input:file').val("");
	        $(".image-preview-input-title").text("Browse"); 
	    }); 
	    // Create the preview image
	    $(".image-preview-input input:file").change(function (){     
	        var img = $('<img/>', {
	            id: 'dynamic',
	            width:250,
	            height:200
	        });      
	        var file = this.files[0];
	        var reader = new FileReader();
	        // Set preview image into the popover data-content
	        reader.onload = function (e) {
	            $(".image-preview-input-title").text("Change");
	            $(".image-preview-clear").show();
	            $(".image-preview-filename").val(file.name);            
	            img.attr('src', e.target.result);
	            $(".image-preview").attr("data-content",$(img)[0].outerHTML).popover("show");
	        }        
	        reader.readAsDataURL(file);
	    });  
	});
</script>


