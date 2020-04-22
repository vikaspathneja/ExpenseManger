<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- <jsp:include page="scripts/scripts.jsp"></jsp:include>
<jsp:include page="css/css.jsp"></jsp:include>
 --%>
<script type="text/javascript"
	src="resources/js/validation/user_validation.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
<head>
	<title>Expense Manager</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<script type="text/javascript" src="resources/js/validation/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="resources/js/validation/jquery-ui-1.8.18.custom.min.js"></script>
	<link href="resources/css/normalize.css" rel="stylesheet" type="text/css"/>
	<link href="resources/css/datepicker.css" rel="stylesheet" type="text/css"/>	
</head><div class="modal-header">
	<button type="button" class="close" data-dismiss="modal" onclick="window.location.reload();">&times;</button>
	<h4 class="modal-title">Update User</h4>
</div>
<div class="modal-body">
	<div class="container">
		<div class="row">
			<div class='col-sm-6'>
				<div class="form-group"></div>
				<form:form method="post" modelAttribute="userpojo"
					style="display: block;" onsubmit="return validate()">

					<div class="form-group">
						<form:input tabindex="1" path="userEmail" id="email" readOnly="readOnly"
							class="form-control" placeholder="User Email" value="${userpojo.userEmail}"/>
						<span id="email_result" style="color: red;"></span>
					</div>

					<div class="form-group">
						<select class="form-control" id="selectstatus" onchange="GetOptionsForStatus();">
							<option value="">Change Status</option>
							<option value="active">Active</option>
							<option value="blocked">Block</option>
							
						</select>
						<form:hidden id="status" path="userStatus"/>
						<span id="status_result" style="color: red;"></span>
					</div>
					
					<div class="form-group">
						<form:input tabindex="1" path="userMobileNumber" id="mobile"
							class="form-control" placeholder="Mobile Number" value="${userpojo.userMobileNumber}" />
						<span id="mobile_result" style="color: red;"></span>
					</div>

			
					<div class="form-group">
						<select class="form-control" id="selectrole" onchange="GetOptionsForRole();">
							<option value="">Select Role</option>
							<option value="admin">Admin</option>
							<option value="subadmin">Sub Admin</option>
							<option value="user">User</option>
						</select>
						<form:hidden id="role" path="role"/>
						<span id="role_result" style="color: red;"></span>
					</div>
					
					<div class="form-group">
						<form:hidden tabindex="5" path="userId" id="userid"
							class="form-control" placeholder="User Id"
							value="${sessionScope.valid_user.userId}" />

					</div>
					<div class="form-group">
						<div class="row">
							<div class="col-sm-6 col-sm-offset-3">
								<input type="submit" tabindex="4"
									class="form-control btn btn-default" value="Save">
							</div>
						</div>
					</div>
				</form:form>
			</div>
		</div>
	</div>

</div>
