<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>


<div class="row">
	<div class="col-sm-12">
		<nav class="headermenu">
			<div class="container-fluid">
				<div class="navbar-header"></div>
					<ul class="nav navbar-nav navbar-left">
						<li><a href="/home"><span class="glyphicon glyphicon-home"></span>
								Home</a></li>
					</ul>
				<c:if test="${not empty sessionScope.fullname && !isAccount}">
					<ul class="nav navbar-nav navbar-left">
						<li><a href="/profile/myaccount/"><span class="glyphicon glyphicon-user"></span>
								${sessionScope.fullname }</a></li>
					</ul>
				</c:if>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/logout" id="logoutId"><span
							class="glyphicon glyphicon-log-out"></span> <spring:message code="kyc.label.logout"/></a></li>
				</ul>
			</div>
		</nav>
	</div>
</div>
