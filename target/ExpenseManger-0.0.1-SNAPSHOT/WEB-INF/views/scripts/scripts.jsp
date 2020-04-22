<script type="text/javascript" src="resources/js/lib/jquery-2.2.4.min.js"></script>
<script type="text/javascript" src="resources/js/lib/angular.min.js"></script>


<!-- gchart scripts  -->
<script type="text/javascript" src="resources/js/lib/moment.min.js"></script>
<!-- <script type="text/javascript" src="resources/js/lib/loader.js"></script> -->

<!-- bootstrap scripts -->    
<script type="text/javascript" src="resources/js/lib/bootstrap.min.js"></script>
<script type="text/javascript" src="resources/js/lib/bootstrap-datetimepicker.min.js"></script>
<script type="text/javascript" src="resources/js/lib/bootstrap-datepicker.min.js"></script>
<!-- angular controller scripts -->
<script type="text/javascript" src="resources/js/main.js"></script>

<script type="text/javascript" src="resources/js/controllers/expense_controller.js"></script>
<!-- table -->
<script type="text/javascript" src="resources/js/dyanamic_table.js"></script>
<!-- date time -->
<!-- <script type="text/javascript" src="resources/js/datetimepicker.js"></script> -->
<!--Start of Zendesk Chat Script-->
<script type="text/javascript">
window.$zopim||(function(d,s){var z=$zopim=function(c){z._.push(c)},$=z.s=
d.createElement(s),e=d.getElementsByTagName(s)[0];z.set=function(o){z.set.
_.push(o)};z._=[];z.set._=[];$.async=!0;$.setAttribute("charset","utf-8");
$.src="https://v2.zopim.com/?20p7mTDs5D6PssyGrI5GeEI9RMKdKceF";z.t=+new Date;$.
type="text/javascript";e.parentNode.insertBefore($,e)})(document,"script");
</script>
<!--End of Zendesk Chat Script-->

<!--Push Notification -->
<script src="https://js.pusher.com/3.2/pusher.min.js"></script>
  <script>
    // Enable pusher logging - don't include this in production
    //Pusher.logToConsole = true;
    var pusher = new Pusher('5553f21ba3904c591e19', {
      encrypted: true
    });

    var channel = pusher.subscribe('my-channel');
    channel.bind('my-event', function(data) {
	//console.log(data.message);
	//document.getElementById("notify").innerHTML=data.message;
	//document.getElementById("notify").href=data.message;
	notifyMe(data.message);
	});
	    
  </script>
<script>
// request permission on page load
document.addEventListener('DOMContentLoaded', function () {
  if (!Notification) {
    alert('Desktop notifications not available in your browser. Try Chromium.'); 
    return;
  }

  if (Notification.permission !== "granted")
    Notification.requestPermission();
	});

function notifyMe(message) {
  if (Notification.permission !== "granted")
    Notification.requestPermission();
  else {
    var notification = new Notification('Expense Manager', {
      icon: 'https://lh4.googleusercontent.com/-ZK4cvgZqDQs/AAAAAAAAAAI/AAAAAAAAACQ/_rPQdJYMLZ4/photo.jpg',
      body: message,
    });

    notification.onclick = function () {
      window.open(message);      
    };

  }

}
</script>
<!--End of Push Notification Scripts-->
<script>
function checkactivelink(){
	//alert(localStorage.getItem("activelink"));
	var activelinkid=localStorage.getItem("activelink")
	document.getElementById(activelinkid).style.color="white";
}
</script> 
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
  <script src="https://apis.google.com/js/client:platform.js?onload=start" async defer></script>
 <script>
   function start() {
  gapi.load('auth2', function() {
	//  alert("gapi loaded");
	 
    auth2 = gapi.auth2.init({
	         client_id: '1000357136963-79hv2bhd5apn8vbd7lmuaa365eqnsk7c.apps.googleusercontent.com', 
    });
   });
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
  function signOut(){
	    auth2.signOut();
	    auth2.disconnect();
	    FB.logout();
	}
	function changeactiveclass(id){
		localStorage.setItem("activelink", id);
	}
	</script>