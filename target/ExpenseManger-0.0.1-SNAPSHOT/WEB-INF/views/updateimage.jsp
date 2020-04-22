<%@page import="com.project.reversepojos.TableUser"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Expense Manager</title>
<%Object userid=session.getAttribute("currentuserid");
Number n=(Number)userid;
long longuserid=n.longValue();
if(longuserid==(long)0){
	session.invalidate();
	response.setHeader("refresh","1;URL=login");
}
%>
</head>
<body>
<form:form modelAttribute="userImagePojo" method="post" enctype="multipart/form-data">
<input type="file" id="userimagefile" name="textFile" />
<form:hidden path="userId" value="<%=longuserid%>"/>
<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" tabindex="4"
													class="form-control btn btn-login" value="Upload">
											</div>
										</div>
										 
									</div>
</form:form>
</body>
</html>