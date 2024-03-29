<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html >
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="description" content="Learn how to use the Firebase platform on the Web">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Chat</title>

  <!-- Disable tap highlight on IE -->
  <meta name="msapplication-tap-highlight" content="no">

  <!-- Web Application Manifest -->
  <link rel="manifest" href="resources/chat/manifest.json">

  <!-- Add to homescreen for Chrome on Android -->
  <meta name="mobile-web-app-capable" content="yes">
  <meta name="application-name" content="Friendly Chat">
  <meta name="theme-color" content="#303F9F">

  <!-- Add to homescreen for Safari on iOS -->
  <meta name="apple-mobile-web-app-capable" content="yes">
  <meta name="apple-mobile-web-app-status-bar-style" content="black-translucent">
  <meta name="apple-mobile-web-app-title" content="Friendly Chat">
  <meta name="apple-mobile-web-app-status-bar-style" content="#303F9F">

  <!-- Tile icon for Win8 -->
  <meta name="msapplication-TileColor" content="#3372DF">
  <meta name="msapplication-navbutton-color" content="#303F9F">

  <!-- Material Design Lite -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
  <link rel="stylesheet" href="https://code.getmdl.io/1.1.3/material.orange-indigo.min.css">
  <script defer src="https://code.getmdl.io/1.1.3/material.min.js"></script>

  <!-- App Styling -->
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
  <link rel="stylesheet" href="resources/chat/styles/main.css">
</head>
<body>
<div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-header">

  <!-- Header section containing logo -->
  <header class="mdl-layout__header mdl-color-text--white mdl-color--light-blue-700">
    <div class="mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-grid">
      <div class="mdl-layout__header-row mdl-cell mdl-cell--12-col mdl-cell--12-col-tablet mdl-cell--12-col-desktop">
        <h3><i class="material-icons">&nbsp;&nbsp;&nbsp;chat_bubble_outline</i>Community Chat</h3>
      </div>
      <div id="user-container">
        <div hidden id="user-pic"></div>
        <div hidden id="user-name"></div>
        <button hidden id="sign-out" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-color-text--white">
          Sign-out
        </button>
        <button hidden id="sign-in" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-color-text--white">
          <i class="material-icons">account_circle</i>Sign-In
          </button>
           
        </div>
    </div>
  </header>

  <main class="mdl-layout__content mdl-color--grey-100">
<a href="dashboard"><img alt="Back" width="50px" height="50px" src="resources/chat/images/back.png"></a>
        <div id="messages-card-container" class="mdl-cell mdl-cell--12-col mdl-grid">
    <!-- Messages container -->
      <div id="messages-card" class="mdl-card mdl-shadow--2dp mdl-cell mdl-cell--12-col mdl-cell--6-col-tablet mdl-cell--6-col-desktop">
        <div class="mdl-card__supporting-text mdl-color-text--grey-600">
          <div id="messages">
            <span id="message-filler"></span>
          </div>
          <form id="message-form" action="#">
            <div class="mdl-textfield mdl-js-textfield mdl-textfield--floating-label">
              <input class="mdl-textfield__input" type="text" id="message">
              <label class="mdl-textfield__label" for="message">Message...</label>
            </div>
            <button id="submit" disabled type="submit" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect">
              Send
            </button>
            </form>
			<form id="image-form" action="#">
            <input id="mediaCapture" type="file" accept="image/*,capture=camera">
            <button id="submitImage" title="Add an image" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-color--amber-400 mdl-color-text--white">
              <i class="material-icons">image</i>
        </button>
        
          </form>
          
        </div>
      </div>
	 <div id="must-signin-snackbar" class="mdl-js-snackbar mdl-snackbar">
	 <div class="mdl-snackbar__text"></div>
        <button class="mdl-snackbar__action" type="button"></button>
     </div>
	</div><br/>
	
	<div align="right"><p><b>Note:-</b>This is Community Chat for sharing information,ideas,Help Each Other...Respect That..<br/>Don't Use abusive Language While Communication <br/> We are not responsible for any harm and feelings while communicating !!</p><br/></div>
  </main>
</div>

<!-- Firebase -->
<!-- ***********************************************************************************************************************
     * TODO(DEVELOPER): Paste the initialization snippet from: Firebase Console > Overview > Add Firebase to your web app. *
     *********************************************************************************************************************** -->

<script src="https://www.gstatic.com/firebasejs/3.6.9/firebase.js"></script>
<script>
  // Initialize Firebase
  var config = {
    apiKey: "AIzaSyCRfufCz3DGn7_FYivz2ZkVPVddY9SEs04",
    authDomain: "chat-75adf.firebaseapp.com",
    databaseURL: "https://chat-75adf.firebaseio.com",
    storageBucket: "chat-75adf.appspot.com",
    messagingSenderId: "890850339910"
  };
  firebase.initializeApp(config);
</script>
<script src="resources/chat/scripts/main.js"></script>

</body>

</html>