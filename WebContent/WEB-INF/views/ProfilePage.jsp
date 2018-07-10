<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" import="java.util.HashMap"%>
<%@ page language="java" import="com.project.reversepojos.*"%>

<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Expense Manager</title>

<jsp:include page="scripts/scripts.jsp"></jsp:include>
<jsp:include page="css/css.jsp"></jsp:include>
<!-- login -->
<link rel="stylesheet" href="resources/css/loginPage.css">

<script type="text/javascript"
	src="resources/js/validation/login_validation.js"></script>
<script type="text/javascript" >
</script>
<%
TableUser user=(TableUser)session.getAttribute("valid_user");
//out.println(user);
String email=user.getUserEmail();
long userId=user.getUserId();
String mobile=user.getUserMobileNumber();
String role=user.getRole();
int budget=user.getUserBudget();
String username=user.getUserName();
%>
</head>
<body>
<button type="button" class="btn btn-default pull-right"
		data-dismiss="modal">Close</button>
<h3 align="center">Profile Details</h3>						
	<!-- <div class="container"> -->
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<!-- <div class="panel panel-login"> -->
					<!-- <div class="panel-heading"> -->
						<div class="form-group" >
										<style type="text/css">span.tab-space {padding-left:10em;}</style>
										<label>UserId:</label>
										<span class="tab-space">
										<label>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<b><%=userId%></b></label>
										</span>
										<br/>
										
										<label>UserName:</label>
										<span class="tab-space">
										<label ><%=username%></label>
										</span>
										<br/>
										
										<label>UserRole:</label>
										<span class="tab-space">
										<label >&nbsp;&nbsp;<%=role%></label>
										</span>
										<br/>
										
										<label>Email:</label>
										<span class="tab-space">
										<label ><%=email%></label>
										</span>
										<br/>
										
										<label>MobileNumber:</label>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<label ><%=mobile%></label>
										</span>
										<br/>
										
										<label>UserBudget:</label>
										<span class="tab-space">
										<label ><%=budget%></label>
										</span>
										<br/>
										
						</div>
						<div class="form-group">
										<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3" align="center">
											<a href="dashboard" class="btn btn-primary btn-lg">Go To DashBoard</a>
											</div>
										</div>
										 
									</div>
						</div>
					<!-- </div> -->
					
				</div>
			</div>
	<!-- 	</div> -->
	<!-- </div> -->
</body>
</html>