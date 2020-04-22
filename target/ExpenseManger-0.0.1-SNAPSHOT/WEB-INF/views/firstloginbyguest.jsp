<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Expense Manager</title>
<jsp:include page="scripts/scripts.jsp"></jsp:include>
<jsp:include page="css/css.jsp"></jsp:include>
<!-- login -->
<link rel="stylesheet" href="resources/css/loginPage.css">
<script>
function getuniqueno(){
	uniquevalue=document.getElementById("unique").value;
	//if(uniquevalue=="" || uniquevalue==0.00){
	//	document.getElementById("uniquehidden").value=uniquevalue;
	//	return true;
	//}	
	if(uniquevalue<2147483648){
		return true;
	}
	else if (/[^0-9\/]/.test(uniquevalue)){
	alert("Only Numeric Value Allowed");
	return false;
	}
}
function changevalue(){
	document.getElementById("uniquehidden").value=document.getElementById("unique").value;
}
</script>
</head>
<body>
<h3 align="center">Expense Manager | Easy Way To Manage Expenses</h3>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
						
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form:form id="login-form" modelAttribute="userPojo"
									name="loginForm" method="post" style="display: block;"
									onsubmit="return getuniqueno();">
									Login Via Unique Number 
									<div class="form-group">
									<!-- <input type="text" tabIndex="2" id="unique" name="unique" maxLength="8" onChange="changevalue();"
									class="form-control" placeholder= "Enter Your Unique Number"/> -->
									<form:input type="text" id="unique" name="unique" maxlength="8" onchange="changevalue();"
									class="form-control" placeholder= "Enter Your Unique Number" path=""/>
									<form:hidden tabindex="2" path="userId" id="uniquehidden" name="uniquehidden" maxlength="8"
									class="form-control" />
									</div>
									
									<div class="form-group">
										<div class="row">
										<div class="col-sm-4 col-sm-offset-4">
										<input type="submit" tabindex="4" class="form-control btn btn-login" 
												value="Login As Guest">
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
	</div></body>
</html>