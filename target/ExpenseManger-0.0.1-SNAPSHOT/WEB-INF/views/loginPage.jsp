<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- original singin meta data commented for producation-->
<meta name="google-site-verification" content="Sq95DtmV5QplS8i3h2hCZckhM51PlQYqFwKhPwZ5ev0" />
<title>Expense Manager </title>
<script type="text/javascript">
</script>
<jsp:include page="scripts/scripts.jsp"></jsp:include>
<jsp:include page="css/css.jsp"></jsp:include>
<!-- login-->
<link rel="stylesheet" href="resources/css/loginPage.css">
<script src="https://apis.google.com/js/platform.js" async defer></script>
<script type="text/javascript"	src="resources/js/validation/login_validation.js"></script>
<script type="text/javascript">
 function rolechange(){
	var roleitem=document.getElementById("roleitem").value;
	document.getElementById("rolehidden").value=document.getElementById("roleitem").value;
}
function showimage(){
	$( document ).ready(function() {
		$("#loadingimage").show();
		$("#containerdivision").hide();
	});
}
</script>
<!-- <script src="https://apis.google.com/js/platform.js?onload=renderButton" async defer></script> -->
<!-- <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js">-->
 <script type="text/javascript">
function onSuccess(googleUser) {
 // console.log('Logged in as:Id ' + googleUser.getBasicProfile().getId());
  //console.log('Logged in as:Name ' + googleUser.getBasicProfile().getName());
  //console.log('Logged in as:Email ' + googleUser.getBasicProfile().getEmail());
 // console.log('Logged in as:ImageUrl ' + googleUser.getBasicProfile().getImageUrl());
  showimage();
  var id_token = googleUser.getAuthResponse().id_token;
  var url="loginbygoogle?id_token="+id_token;
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
	 if (this.readyState == 4 && this.status == 200) {
    //alert("login successful");
    window.location="dashboard";  
    }
  };
  //alert("reached here before google ajax ");
  xhttp.open("GET",url, true);	
  xhttp.send();  
  //
}
function onFailure(error) {
  console.log(error);
  alert("Error While Login Through Google");
  $("#login-form").show();
}
/* function renderButton() {
    gapi.signin2.render('my-signin2', {
      'scope': 'profile email',
      'width': 100,
      'height': 40,
      'longtitle': false,
      'theme': 'light',
      'onsuccess': onSuccess,
      'onfailure': onFailure
    });
  }
 */
/*  function onSignIn(googleUser) {
	  var profile = googleUser.getBasicProfile();
	  console.log('ID: ' + profile.getId()); // Do not send to your backend! Use an ID token instead.
	  console.log('Name: ' + profile.getName());
	  console.log('Image URL: ' + profile.getImageUrl());
	  console.log('Email: ' + profile.getEmail());
}
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
    });
  }
  */
</script>
  <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet" type="text/css">
  <script src="https://apis.google.com/js/api:client.js"></script>
  <script>
  var googleUser = {};
  var startApp = function() {
    gapi.load('auth2', function(){
      // Retrieve the singleton for the GoogleAuth library and set up the client.
      auth2 = gapi.auth2.init({
		client_id:'1000357136963-79hv2bhd5apn8vbd7lmuaa365eqnsk7c.apps.googleusercontent.com', 
        cookiepolicy: 'single_host_origin',
        // Request scopes in addition to 'profile' and 'email'
        //scope: 'additional_scope'
      });
      attachSignin(document.getElementById('customBtn'));
    });
  };

  function attachSignin(element) {
    //console.log(element.id);
    auth2.attachClickHandler(element, {},
        function(googleUser) {
       // alert("signed in");
        onSuccess(googleUser);
        }, function(error) {
        	onFailure(error);
          alert(JSON.stringify(error, undefined, 2));
        });
  }
  </script>
  <style type="text/css">
    #customBtn {
      display: inline-block;
      background: white;
      color: #444;
      width: 112px;
      border-radius: 5px;
      border: thin solid #888;
      box-shadow: 1px 1px 1px grey;
      white-space: nowrap;
      height: 39px;
    }
    #customBtn:hover {
      cursor: pointer;
    }
    span.label {
      font-family: serif;
      font-weight: normal;
    }
    span.icon {
      /* background: url('/identity/sign-in/g-normal.png') transparent 5px 50% no-repeat; */
      display: inline-block;
      vertical-align: middle;
      width: 42px;
      height: 42px;
    }
    span.buttonText {
      display: inline-block;
      vertical-align: middle;
      padding-left: 42px;
      padding-right: 42px;
      font-size: 14px;
      font-weight: bold;
      /* Use the Roboto font that is loaded in the <head> */
      font-family: 'Roboto', sans-serif;
    }
  </style>
<script>
window.fbAsyncInit = function() {
    FB.init({
      appId      : '1855958714620398',
      cookie     : true,
      xfbml      : true,
      version    : 'v2.8'
    });
    FB.AppEvents.logPageView();   
  };
    function checkLoginState() {
	  var fb_token="";
	  var fb_userid="";
	  var fb_fullname="";
	  var fb_picture_url="";
	  var fb_email="";
	  showimage();
	    FB.getLoginStatus(function(response) {
			//console.log(response);
	        //statusChangeCallback(response);
	        if (response.status === 'connected') {
	        	 console.log("user id"+response.authResponse.userID);
	        	 fb_userid=response.authResponse.userID;
	        	console.log("user access token"+response.authResponse.accessToken);
	        	fb_token=response.authResponse.accessToken;
	        	 FB.api('/me',{ locale: 'en_US', fields: 'name, email' }, function(response) {
		        	 console.log('Successful login user name: ' + response.name+"\n"+' user email:-'+response.email);
	        		fb_email=response.email;
	        		fb_fullname=response.name;
	        		fb_picture_url="http://graph.facebook.com/"+fb_userid+"/picture?type=square";
	        		//alert("fb details before going to ajax"+"useri====d"+fb_userid+"\n"+"useremail===="+fb_email+"\n"+"fbfullname==="+fb_fullname+"\n"+"fbpicture==="+fb_picture_url+"\n"+"fbtoken"+fb_token);
	        		testAPI(fb_email,fb_userid,fb_fullname,fb_picture_url,fb_token);
	        		});
	        	 }
	    });
	  }	   function testAPI(responseemail,responseruserid,resonpsefullname,fbpicture,fbtoken) {
		    console.log(responseruserid+"/n"+responseemail+"/n"+fbpicture+"/n"+resonpsefullname);
		    var url="loginbyfacebook?fb_email="+responseemail+"&fb_userid="+responseruserid+"&fb_fullname="+resonpsefullname+"&fb_picture_url="+fbpicture;
		    var xhttp = new XMLHttpRequest();
				 xhttp.onreadystatechange = function() {
			 if (this.readyState == 4 && this.status == 200) {
					window.location="dashboard";  
			}};
				xhttp.open("GET",url, true);	
				xhttp.send(); 
		} 
</script>
<div id="fb-root"></div>
<script>(function(d, s, id) {
  var js, fjs = d.getElementsByTagName(s)[0];
  if (d.getElementById(id)) return;
  js = d.createElement(s); js.id = id;
  js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.8";
  fjs.parentNode.insertBefore(js, fjs);
}(document, 'script', 'facebook-jssdk'));	
</script> 
</head>
<body >
<script>
	//$("#loadingimage").show();
</script>
	<noscript>
      <div style="width: 22em; position: absolute; left: 50%; right:50%,margin-left: -11em; color: red; background-color: white; border: 1px solid red; padding: 4px; font-family: sans-serif">
        Your web browser must have JavaScript enabled
        in order for this application to display correctly.
      </div>
    </noscript>
	<h3 align="center">Expense Manager | Easy Way To Manage Expenses</h3>
	<div align="center"><img id="loadingimage" alt="Please Wait....Sign in with Google is still in progress" src="resources/images/simple.gif" style="display: none;"></div>
	
	<div class="container" id="containerdivision">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="login" class="active" id="login-form-link">Login</a>
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
									name="loginForm" method="post" style="display: block;"
									onsubmit="return validate()">
									<div class="form-group">
										<form:input path="userEmail" tabindex="2" id="email"
											class="form-control" placeholder="Email Address" value="" />
										<span id="email_result" style="color: red;"></span>
									</div>
									<div class="form-group">
										<form:password tabindex="2" path="userPassword" id="password"
											class="form-control" placeholder="Password" />
										<span id="pass_result" style="color: red;"></span>
									</div>
									<!-- commented for time being in future work will do 
									<div class="form-group text-center">
										<input type="checkbox" tabindex="3" class="" name="remember"
											id="remember"> <label for="remember">
											Remember Me</label>
									</div> 
									-->
									<br>
									 <div class="form-group" >
										<div class="row"  >  <!----> <!-- --> <!-- class="col-sm-3 col-sm-offset-4"  -->
											<div  align="center" >
											<input type="submit" tabindex="4"  class="btn btn-primary btn-lg"  
													value="Sign In">
											</div>
											
									</div>
									<br/><div align="center"><a href="forgotPasswordEmail" tabindex="5"  class="forgot-password">Forgot Password?</a><br></div>
										             <br/><p align="center"> - - - - - - - - - - - or Sign In With - - - - - - - - - - - </p>
									</div>
									<div class="form-group">
										<div class="row">
										<!-- In the callback, you would hide the gSignInWrapper element on a  successful sign in -->
  <div id="gSignInWrapper" class="col-lg-offset-3 col-lg-2 col-md-2 col-sm-6 col-xs-6">
    <div id="customBtn" class="customGPlusSignIn">
      <span class="icon"><img alt="Google" style="height:38px; width:auto; padding:1px 1px 1px 1px;" src="resources/images/google.png"><b>  Google</b></span>
      <div id="name"></div>
  <script>startApp();</script>
    </div>
  </div>										<!--below line is division of google signin button-->
												<!-- <div class="fb-login-button col-lg-offset-3 col-lg-2 col-md-2 col-sm-6 col-xs-6" > -->
												 <!-- <span id="my-signin2" style="height: 50%"></span></div> -->
												<div class="fb-login-button col-lg-offset-1 col-lg-5 col-md-5 col-sm-6 col-xs-6" data-max-rows="1" data-size="xlarge" data-show-faces="false" data-auto-logout-link="false" onlogin="checkLoginState();"></div>
										</div>
												<div align="center"><br/>
														<a href="loginbyguest" tabindex="5" 
														class="forgot-password" style="color: green;">
														Login As Guest</a><br> 
														<a href="loginbyuniquenumber" tabindex="5"   
														class="forgot-password" style="color: green;">
														Login By Unique Number
														</a>
														</div>
														<br>
														
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