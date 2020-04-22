<%@ page language="java" import="java.io.*" errorPage="" %>
<head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script src="https://apis.google.com/js/client:platform.js?onload=start" async defer></script>
 <script>
 function start() {
  gapi.load('auth2', function() {
    auth2 = gapi.auth2.init({
		/* below line for production environment  */
      //  client_id: '19436701882-qblupa58at0bka8rij3ruodj9vkit2se.apps.googleusercontent.com',
      /*below line for local  */
      client_id: '19436701882-vf714918tl2dnhbe13b6m68hjrlitoad.apps.googleusercontent.com',
      // Scopes to request in addition to 'profile' and 'email'
      //scope: 'additional_scope'
    });
  });
}
function updateimage(){
	alert(document.getElementById("uuidfromuploadcare").value);
	document.getElementById("profile_picture").src=image_uuid;
	
}
window.fbAsyncInit = function() {
    FB.init({
      appId      : '1855958714620398',
      cookie     : true,
      xfbml      : true,
      version    : 'v2.8'
    });
    FB.AppEvents.logPageView();   
  };
</script>
<script charset="utf-8" src="//ucarecdn.com/libs/widget/2.10.3/uploadcare.full.min.js"></script>
</head>
<%
Object userid=session.getAttribute("currentuserid");
Number n=(Number)userid;
long longuserid=n.longValue();
if(longuserid==(long)0){
	session.invalidate();
	response.setHeader("refresh","1;URL=login");
}
String pictureurl=(String)session.getAttribute("profileurl");
if(pictureurl==null){
	pictureurl="";
}
//out.println(pictureurl);
%>
<%String filepathdirectory=System.getenv("OPENSHIFT_DATA_DIR"); 
String filename="/images/userIcon.png";
%>
<nav class="navbar navbar-inverse" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#sidebar-collapse">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
		</div>
		<a class="navbar-brand" href="dashboard"><strong>Expense
				Manager Unique No:<%=userid %></strong></a>
		<ul class="nav navbar-nav navbar-right" style="margin: auto;">
			<li><a href="dashboard">Dashboard</a></li>
			<li><a href="expense">Expense</a></li>
			<li class="active"><a href="income">Income</a></li>
			<!-- href="payorsend" -->
			<li><a href="https://www.payumoney.com/paybypayumoney/#/05C62B80B76E4D2999232D42BA78E071">Pay or Send</a></li>
			<!-- <li><div class="pm-button"><a href="https://www.payumoney.com/paybypayumoney/#/05C62B80B76E4D2999232D42BA78E071"><img src="https://www.payumoney.com//media/images/payby_payumoney/buttons/211.png" /></a></div></li> -->
            <!-- <li><a href="#">Request Money</a></li>
            <!-- <li><a href="#">Load Money</a></li> 
            <!-- <li><a href="#">Split Bill </a></li>
            <li><a href="#">Accounts</a></li>
            <li><a href="#">Offers</a></li> 
            <!-- <li><a href="#">Budget</a></li> -->

            <li class="dropdown" style="height: 50px;"><a href="#"
				class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="true">
<!-- 				<img class="img-circle" src="F:/cdac/cdac project/MY_LIVE_PROJECT/source backups/ExpenseManager/WebContent/static_contents/images/userIcon.png" width="30px" -->
           <!-- <img class="img-circle" src="resources/images/userIcon.png" -->
           <img class="img-circle" id="profile_picture" width="30px" name="profile_picture" src="<%=pictureurl%>"           
					height="30px" alt="user icon">
					&nbsp;<span class="caret"></span></a>
				<ul class="dropdown-menu">
			<!--<li role="separator" class="divider"></li> -->
					<li><a href="changeuserimage" onclick="updateimage();">Update Image</a></li>
						<li><a href="uploadtocloud" onclick="updateimage();">Update Image To Cloud</a></li>
					<li><a href="myprofiledetails">Profile Details</a></li>
					<li><a href="logout" onclick="signOut();">Log Out</a></li>
				</ul></li>
		</ul>
	</div>
</nav>
<script>
function signOut(){
	//alert("reahced in singout of google");
	auth2.disconnect();	
	FB.logout();
}
</script>
