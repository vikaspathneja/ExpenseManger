<%@ page language="java" import="java.io.*" errorPage="" %>
<%
Object userid=session.getAttribute("currentuserid");
Number n=(Number)userid;
long longuserid=n.longValue();
if(longuserid==(long)0){
	session.invalidate();
	response.setHeader("refresh","1;URL=login");
}
String pictureurl=(String)session.getAttribute("profileurl");
if(pictureurl==null || pictureurl==""){
	pictureurl="resources/images/user.jpg";
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
			<li><a id="dashboardlinkactive"  href="dashboard" onclick="changeactiveclass('dashboardlinkactive');">Dashboard</a></li>
			<li><a id="expenselinkactive" href="expense"  onclick="changeactiveclass('expenselinkactive');">Expense</a></li>
			<li><a id="incomelinkactive" href="income" onclick="changeactiveclass('incomelinkactive');">Income</a></li>
			<!-- href="payorsend" -->
			<li><a id="payumoneyfieldid" href="https://www.payumoney.com/paybypayumoney/#/264131" onclick="changeactiveclass('payumoneyfieldid');">Pay</a></li>
			<!-- <li><a id="payumoneyfieldid" href="https://www.payumoney.com/paybypayumoney/#/05C62B80B76E4D2999232D42BA78E071" onclick="changeactiveclass('payumoneyfieldid');">Pay or Send</a></li> -->
			<!-- <li><div class="pm-button"><a href="https://www.payumoney.com/paybypayumoney/#/05C62B80B76E4D2999232D42BA78E071"><img src="https://www.payumoney.com//media/images/payby_payumoney/buttons/211.png" /></a></div></li> -->
            <!-- <li><a href="#">Request Money</a></li>
            <!-- <li><a href="#">Load Money</a></li>--> 
             <!-- <li><a href="splitbill" data-toggle="modal" data-target="#FormSplitBill">Split Bill </a></li> -->
            <!-- <li><a href="#">Accounts</a></li> -->
            <li><a href="#" onclick="alert('comming soon....');">Offers</a></li> 
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
					<!-- <li><a href="changeuserimage" onclick="updateimage();">Update Image</a></li> -->
						<li><a href="uploadtocloud" data-toggle="modal" data-target="#modalForm">Update Image</a></li>
					<li><a href="myprofiledetails" data-toggle="modal" data-target="#ProfileForm">Profile Details</a></li>
					<li><a href="logout" onclick="signOut();">Log Out</a></li>
				</ul></li>
		</ul>
	</div>
</nav>

 <div id="modalForm" class="modal fade" role="dialog" 
					data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content"></div>
					</div>
</div>

<div id="ProfileForm" class="modal fade" role="dialog" 
					data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content"></div>
					</div>
</div>
<div id="FormSplitBill" class="modal fade" role="dialog" 
					data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content"></div>
					</div>
</div>
