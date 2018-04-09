<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/form.css">
</head>
<br />
<jsp:include page="../common/header.jsp"></jsp:include>
<div class="inner contact">
	<div class="col-sm-12 clearfix row">		
		<div id="main-user">
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
				<table class="table table-hover">
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
				    	<c:forEach items="${anxUser.levelUser}" var="levelUser">
					    	<tr>
								<td class="col-sm-2">
						           <c:out value = "${levelUser.level.description}"/>
						        </td>
						        <td>
						         <c:out value = "${levelUser.level.requirement}"/>
						       	</td>
						        <td>
						          <c:out value = "${levelUser.level.cashIn}"/>
						        </td>
						        <td>
						          <c:out value = "${levelUser.level.cashOut}"/>
						       </td>
						        <td>
						          <c:out value = "${levelUser.levelLimit}"/>&nbsp
						        </td>
						    </tr> 
		       			</c:forEach>
				    </tbody>
				</table>
				
				
	        </div>
		</div>
	</div>	
	
</div>


