<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/table.css">
</head>
<br />
<jsp:include page="../common/header.jsp"></jsp:include>

<div class="container">
	<div style="margin-top: 3%;">
	<table id="anxtable" class="table">
		<thead>
			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Email Address</th>
				<th>Mobile Number</th>
				<th>User Level</th>
				<th></th>
			</tr>
		</thead>
		<tbody>
			<c:choose>
				<c:when test="${empty userList }">
					<tr><td colspan="6" align="center">Nothing to approve</td></tr>
				</c:when>
				<c:otherwise>
					<c:forEach items="${userList }" var="user">
						<tr>
							<td>${user.userId }</td>
							<td>${user.firstName } ${user.middleName } ${user.lastName } </td>
							<td>${user.emailAddress }</td>
							<td>${user.phoneNumber }</td>
							<td>${user.userLevel.description }</td>
							<td>
								<button class="btnApprove" onclick="updateUserLevel('${user.userId }', 'approve')">Approve</button>
								<button class="btnReject" onclick="updateUserLevel('${user.userId }', 'reject')">Reject</button>
							</td>
						</tr>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</tbody>
	</table>
	</div>
</div>

<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script type="text/javascript">

	$(document).ready(function() {
		$('#anxtable').DataTable();
	});

	function updateUserLevel(userId, status) {
		var strconfirm = confirm("Are you sure you want to " + status + "?");
		if(strconfirm) {
			location.href = "./updateUserLevel?userId=" + userId + "&status=" + status;
		}
	}
</script>

