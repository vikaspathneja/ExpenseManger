 <%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Expense Manager</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<% String status=(String)session.getAttribute("checkuserstatus");
	if(status==null){%>
	<h3 align="center" class="err"> Session Out, Please Login Again</h3>
	<%response.setHeader("refresh","1;URL=login");
	}else if((status.equals("registersuccessfully"))==true){ %>
	<h3 align="center" class="err"> Register Successfully</h3>
	<%response.setHeader("refresh","1;URL=dashboard");
	}else if((status.equals("useralreadyregisterwiththisemail"))==true){ %>
	<h3 align="center" class="err"> Email Already Registered ,Try Again With Different Email*</h3>
	<%response.setHeader("refresh","1;URL=login");
	} else if((status.equals("registeruserfailed"))==true){ %>
	<h3 align="center" class="err"> User Registration Failed,Try Again*</h3>
	<%response.setHeader("refresh","2;URL=registerUser");
	}else if((status.equals("passwordnotmatch"))==true){ %>
	<h3 align="center" class="err"> Invalid Password</h3>
	<%response.setHeader("refresh","1;URL=login");
	}else if(status.equals("EmailSentSuccessfully")==true) { %>
	<h3 align="center" class="err"> Verification Email Sent Successfully*</h3>
	<% response.setHeader("refresh","1;URL=login");
	}else if(status.equals("usernotfoundinforgetpassword")==true) { %>
	<h3 align="center" class="err"> User Not Exist*</h3>
	<% response.setHeader("refresh","1;URL=login");
	}else if(status.equals("erroringooglelogin")==true) { %>
	<h3 align="center" class="err"> Error While Login Through Google *Try Again or Login With Different Method </h3>
	<% response.setHeader("refresh","1;URL=login");
	}else if(status.equals("someerroroccurredinsendingemail")==true || status.equals("EmailSentFailed")==true) { %>
	<h3 align="center" class="err"> Error In Sending Verification Email*</h3>
	<%response.setHeader("refresh","1;URL=login");
	}else if(status.equals("newuser")==true) { %>
	<h3 align="center" class="err"> User does not exist, Please Register first</h3>
	<%response.setHeader("refresh","1;URL=registerUser");
	}else if(status.equals("imageuploadfailedinelsepart")==true) { %>
	<h3 align="center" class="err"> Image Uploading Failed**</h3>
	<%response.setHeader("refresh","1;URL=dashboard");
	}else if(status.equals("uniquenumbernotfound")==true){%>
	<h3 align="center" class="err"> Unique Number Not Found,Please Enter Right Unique Number</h3>
	<%response.setHeader("refresh","1;URL=login");
	}else if(status.equals("session_out")==true){%>
	<h3 align="center" class="err"> Session Out, Please Login Again</h3>
	<%}else if(status.equals("last_login_attempt")==true){%>
	<h3 align="center" class="err"> This is your last attempt , Otherwise userwise will be blocked</h3>
	<% response.setHeader("refresh","1;URL=login");
	}else if(status.equals("blocked")==true) { %>
	<h3 align="center" class="err">User Blocked ,Because maximum attempt for login over</h3>
	<% response.setHeader("refresh","1;URL=login");
   }else{%>
	<h3 align="center" class="err">Some Other Error Happend,Please Login Again</h3>
	<%response.setHeader("refresh","1;URL=login"); 
	} %>
	 </body>
</html> 

