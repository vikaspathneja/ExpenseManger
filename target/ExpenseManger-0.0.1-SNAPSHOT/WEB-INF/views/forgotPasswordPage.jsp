<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Expense Manager</title>

<jsp:include page="scripts/scripts.jsp"></jsp:include>
<jsp:include page="css/css.jsp"></jsp:include>
<!-- login -->	
<link rel="stylesheet" href="resources/css/loginPage.css">

</head>
<body>
<div class="row">
				<div class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3">
					<div class="alert-placeholder"></div>
					<div class="panel panel-success">
						<div class="panel-body">
							<div class="row">
								<div class="col-lg-12">
									<div class="text-center"><h2><b>Recover Account</b></h2></div>
									<form:form id="register-form"  method="post" role="form" autocomplete="off" modelAttribute="userPojo">
										<div class="form-group">
										<form:input path="userEmail" tabindex="2" id="email"
											class="form-control" placeholder="Email Address" value="" />
										<span id="email_result" style="color: red;"></span>
										</div>
									<div class="form-group">
											<div class="row">
												<div class="col-lg-6 col-lg-offset-3 col-md-6 col-md-offset-3 col-sm-12 col-xs-12">
													<input type="submit" name="recover-submit" id="recover-submit" tabindex="2" class="form-control btn btn-success" value="Recover Account" />
												</div>
											</div>
										</div>
										<!-- <input type="hidden" class="hide" name="token" id="token" value=""> -->
									</form:form>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
</body>
</html>