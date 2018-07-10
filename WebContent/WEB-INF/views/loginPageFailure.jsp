<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expense Manager </title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
<div class="alert alert-warning">
  <strong>Warning!</strong> Please enter correct login id or password.
</div>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="login"
								 class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="registerUser" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
						<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form:form id="login-form" modelAttribute="userPojo"
									method="post" style="display: block;">
									<div class="form-group">
										<form:input tabindex="1" path="userEmail"
											class="form-control" placeholder="Username" value="" required="required"/>
									</div>
									<div class="form-group">
										<form:password tabindex="2" path="userPassword"
										class="form-control" placeholder="Password" required="required"/>
									</div>
									<div class="form-group text-center">
										<input type="checkbox" tabindex="3" class="" name="remember"
											id="remember"> <label for="remember">
											Remember Me</label>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" 
													tabindex="4" class="form-control btn btn-login"
													value="Log In">
											</div>
										</div>
									</div>
									<div class="form-group">
										<div class="row">
											<div class="col-lg-12">
												<div class="text-center">
													<a href="forgotPassword" tabindex="5"
														class="forgot-password">Forgot Password?</a>
												</div>
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