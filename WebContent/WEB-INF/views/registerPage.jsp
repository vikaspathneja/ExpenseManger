<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Expense Manager</title>

<jsp:include page="scripts/scripts.jsp"></jsp:include>
<jsp:include page="css/css.jsp"></jsp:include>
<!-- login -->	
<link rel="stylesheet" href="resources/css/loginPage.css">

<script type="text/javascript"
	src="resources/js/validation/registration_validation.js"></script>


</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="login" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="registerUser" class="active" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">

								<form:form id="register-form" modelAttribute="registerUserPojo"
									action="registerUser" method="post" style="display: block;"
									onsubmit="return validate()">
									<div class="form-group">
										<form:input tabindex="1" path="userName" id="name"
											class="form-control" placeholder="Username" value="" />
										<span id="name_result" style="color: red;"></span>

									</div>
									<div class="form-group">
										<form:input path="userEmail" tabindex="2" id="email"
											class="form-control" placeholder="Email Address" value="" />
										<span id="email_result" style="color: red;"></span>
									</div>
									<div class="form-group">
										<form:password path="userPassword" id="password" tabindex="3"
											class="form-control" placeholder="Password" />
										<span id="pass_result" style="color: red;"></span>
									</div>
									<div class="form-group">
										<form:hidden path="role" id="role" value="user" 
											class="form-control" />
									</div>
									<div class="form-group">
										<form:input path="userMobileNumber" tabindex="4" id="mob_num"
											class="form-control" placeholder="Mobile Number" value="" />
										<span id="mob_result" style="color: red;"></span>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit"
													id="register-submit" tabindex="5"
													class="form-control btn btn-register" value="Register Now">
											</div>
										</div>
									</div>
								</form:form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>