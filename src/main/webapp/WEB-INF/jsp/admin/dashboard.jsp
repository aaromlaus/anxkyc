<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<head>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
<link rel="stylesheet" href="/css/table.css">
<!-- DATA TABLE -->
<link rel="stylesheet" type="text/css" href="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/css/jquery.dataTables.css">
</head>
<br />
<jsp:include page="../common/header.jsp"></jsp:include>

<div class="container">
	<table id="anxuser" >
		<tr>
			<th>ID</th>
			<th>Name</th>
			<th>Username</th>
			<th>User Level</th>
			<th></th>
		</tr>
		<c:forEach items="${userList }" var="user">
			<tr>
				<td>${user.userId }</td>
				<td>${user.firstName } ${user.middleName } ${user.lastName } </td>
				<td>${user.username }</td>
				<td>${user.userLevel.description }</td>
				<td><a href="./">Approve</a></td>
			</tr>
		</c:forEach>
	</table>

</div>

<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jQuery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" charset="utf8" src="http://ajax.aspnetcdn.com/ajax/jquery.dataTables/1.9.4/jquery.dataTables.min.js"></script>
<script type="text/javascript">
    $(document).ready(function () {
        $('#anxuser').dataTable();
    });
</script>

