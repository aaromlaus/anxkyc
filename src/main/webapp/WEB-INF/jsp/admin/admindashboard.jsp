<%@page import="java.io.File"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<head>
<script src="http://code.jquery.com/jquery-1.10.2.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.16/js/jquery.dataTables.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" />
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/table.css">
</head>
<br />
<jsp:include page="../common/header.jsp"></jsp:include>
<jsp:include page="../common/headermenu.jsp"></jsp:include>
<body>
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

<div class="modal fade" role="dialog" id="previewImage">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">
						<span class="glyphicon glyphicon-envelope mar-r-10"></span>Email
						Verification
					</h4>
				</div>
				<div class="modal-body">
					<p><input type="text" placeholder="Email Address" class="my-account-input" id="emailVerificationAddress"></p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">Cancel</button>
					<button type="button" class="btn btn-primary" onclick="sendEmailVerification();">Send</button>
				</div>
			</div>

		</div>
	</div>
	<div class="modal fade" id="imagemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
        <h4 class="modal-title" id="myModalLabel">Image preview</h4>
      </div>
      <div class="modal-body">
        <img src="" id="imagepreview" style="width: 100%;" >
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
      </div>
    </div>
  </div>
</div>
</body>
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
	
	function addRowHandlers() {
	    var table = document.getElementById("anxtable");
	    var rows = table.getElementsByTagName("tr");
	    for (i = 0; i < rows.length; i++) {
	        var currentRow = table.rows[i];
	        var createClickHandler = 
	            function(row) 
	            {
	                return function() { 
                          var cell = row.getElementsByTagName("td")[0];
                          var id = cell.innerHTML;
                          $.ajax({
                      		url : '/api/getUploadedId',
                      		data : JSON.stringify({"id":id}),
                      		method : "POST",
                      		contentType : 'application/json',
                      		success : function(data) {
                      			$('#imagepreview').attr("src",data);
                				$('#imagemodal').modal('show');
                      		},
                      		error : function(data) {
                      			alert("error loading image");
                      		}
                      	});
                     };
	            };

	        currentRow.onclick = createClickHandler(currentRow);
	    }
	}
	window.onload = addRowHandlers();
</script>

