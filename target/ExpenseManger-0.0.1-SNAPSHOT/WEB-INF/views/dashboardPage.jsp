<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.HashMap"%>
<%@ page import="com.project.reversepojos.TableUser" language="java"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!--material dashboard starts-->
<!doctype html>
<!--
  Material Design Lite
  Copyright 2015 Google Inc. All rights reserved.

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      https://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License
-->
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="A front-end template that helps you build fast, modern mobile web apps.">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0">
    <title>Expense Manager</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="scripts/scripts.jsp"></jsp:include>
<jsp:include page="css/css.jsp"></jsp:include>
 <script>
 function gainloss(){
	<% HashMap hm=(HashMap)session.getAttribute("chartdata");
	Double totalincome=Double.parseDouble(hm.get("totalincome").toString());
	Double totalexpense=Double.parseDouble(hm.get("totalexpense").toString());
	Double lossgain=totalincome-totalexpense;
	if(lossgain>0){ %>	
	document.getElementById("gainlossspan").style.color="green";
	<%}%>
}
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
 function NotificationPermission(){
		document.addEventListener('DOMContentLoaded', function () {
			  if (!Notification) {
			    alert('Desktop notifications not available in your browser. Try Chromium.'); 
			    return;
			  }
			  if (Notification.permission !== "granted")
			    Notification.requestPermission();
				});
	}
	var activelink=localStorage.getItem("activelink");
	if(activelink==null || activelink==""){
		localStorage.setItem("activelink", "dashboardlinkactive");
	}
</script>

<!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="resources/images/android-desktop.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Material Design Lite">
    <link rel="apple-touch-icon-precomposed" href="resources/images/ios-desktop.png">

    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="resources/images/touch/ms-touch-icon-144x144-precomposed.png">
    <meta name="msapplication-TileColor" content="#3372DF">

    <link rel="shortcut icon" href="resources/images/favicon.png">

    <!-- SEO: If your mobile URL is different from the desktop URL, add a canonical link to the desktop page https://developers.google.com/webmasters/smartphone-sites/feature-phones -->
    <!--
    <link rel="canonical" href="http://www.example.com/">
    -->

    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto:regular,bold,italic,thin,light,bolditalic,black,medium&amp;lang=en">
    <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">
    <link rel="stylesheet" href="https://code.getmdl.io/1.3.0/material.cyan-light_blue.min.css">
    <link rel="stylesheet" href="resources/css/materialstyles.css">
    <style>
    #view-source {
      position: fixed;
      display: block;
      right: 0;
      bottom: 0;
      margin-right: 40px;
      margin-bottom: 40px;
      z-index: 900;
    }
    </style>
  </head>
  <body ng-app="expenseManager" onload="gainloss();">
	<%TableUser tb=(TableUser)session.getAttribute("valid_user");
		//out.println(tb.toString());
		//out.println(pictureurl);
		%>
 <div class="demo-layout mdl-layout mdl-js-layout mdl-layout--fixed-drawer mdl-layout--fixed-header">
      <header class="demo-header mdl-layout__header mdl-color--grey-100 mdl-color-text--grey-600">
        <div class="mdl-layout__header-row">
        <span class="mdl-layout-title">Dashboard</span>
        <%-- <jsp:include page="header/header_dashboard.jsp"></jsp:include> --%>
          <div class="mdl-layout-spacer"></div>
          <!-- <div class="mdl-textfield mdl-js-textfield mdl-textfield--expandable">
            <label class="mdl-button mdl-js-button mdl-button--icon" for="search">
              <i class="material-icons">search</i>
            </label>
            <div class="mdl-textfield__expandable-holder">
              <input class="mdl-textfield__input" type="text" id="search">
              <label class="mdl-textfield__label" for="search">Enter your query...</label>
            </div>
          </div> -->
          <button class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon" id="hdrbtn">
            <i class="material-icons">more_vert</i>
          </button>
          <ul class="mdl-menu mdl-js-menu mdl-js-ripple-effect mdl-menu--bottom-right" for="hdrbtn">
            <!-- <li class="mdl-menu__item">About</li>
            <li class="mdl-menu__item">Contact</li> -->
            <li class="mdl-menu__item"><a href="myprofiledetails" class="mdl-navigation__link" data-toggle="modal" data-target="#ProfileForm">Profile Information</li>
            <li class="mdl-menu__item"><a href="logout"  onclick="signOut();" class="mdl-navigation__link">Logout</a></li>
          </ul>
        </div>
      </header>
      <div class="demo-drawer mdl-layout__drawer mdl-color--blue-grey-900 mdl-color-text--blue-grey-50">
        <header class="demo-drawer-header">
          <!-- <img src="resources/images/user.jpg" class="demo-avatar" > -->
          <img src="<%=pictureurl %>" id="profile_picture"  name="profile_picture"  class="demo-avatar" src="<%=pictureurl%>"> 
			<div class="demo-avatar-dropdown">
            <span>Profile</span>
            <div class="mdl-layout-spacer"></div>
            <button id="accbtn" class="mdl-button mdl-js-button mdl-js-ripple-effect mdl-button--icon">
              <i class="material-icons" role="presentation">arrow_drop_down</i>
              <span class="visuallyhidden">Accounts</span>
            </button>
            <ul class="mdl-menu mdl-menu--bottom-right mdl-js-menu mdl-js-ripple-effect" for="accbtn">
           <a href="uploadtocloud" class="mdl-navigation__link" data-toggle="modal" data-target="#modalForm">   <li class="mdl-menu__item"><i class="material-icons">add_a_photo&nbsp;</i>Update Image</li></a>
            <a href="myprofiledetails" class="mdl-navigation__link" data-toggle="modal" data-target="#ProfileForm">  <li class="mdl-menu__item"><i class="material-icons">account_circle&nbsp;</i>Profile</li></a>
              <li class="mdl-menu__item">
					<a href="logout"  onclick="signOut();" class="mdl-navigation__link"><i class="material-icons">settings_power&nbsp;</i>Logout</li></a>
            </ul>
          </div>
        </header>
        <nav class="demo-navigation mdl-navigation mdl-color--blue-grey-800">
		<a  id="dashboardlinkactive" class="mdl-navigation__link" href="dashboard" onclick="changeactiveclass('dashboardlinkactive');"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">dashboard</i>Dashboard</a>
		<a id="expenselinkactive"  class="mdl-navigation__link" href="expense" onclick="changeactiveclass('expenselinkactive');"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">trending_down</i>Expenses</a>
		<a id="incomelinkactive"  class="mdl-navigation__link" href="income" onclick="changeactiveclass('incomelinkactive');"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">trending_up</i>Incomes</a>
		 <a class="mdl-navigation__link" href="https://www.payumoney.com/paybypayumoney/#/264131"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">send</i>Pay</a>
         <!-- <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">home</i>Home</a> -->
         <!--  <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">inbox</i>Inbox</a>
          <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">delete</i>Trash</a>
          <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">report</i>Spam</a>
          <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">forum</i>Forums</a>
          <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">flag</i>Updates</a>
          <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">local_offer</i>Promos</a>
          <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">shopping_cart</i>Purchases</a> -->
          <a class="mdl-navigation__link" href="chatting"><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">people</i>Social</a>
         <!--<div class="mdl-layout-spacer"></div>
          <a class="mdl-navigation__link" href=""><i class="mdl-color-text--blue-grey-400 material-icons" role="presentation">help_outline</i><span class="visuallyhidden">Help</span></a>-->
        </nav>
      </div>
      <!-- <div id="line_top_x" class="mdl-grid demo-content" align="right"></div> -->
	  <main class="mdl-layout__content mdl-color--grey-100">
	  <!-- <div id="donutchart" style="width: 900px; height: 500px;" ></div> -->
	  <div id="donutchart" class="mdl-grid demo-content" style="height: auto ;width: auto; "></div>
	  <div id="expensechart" class="mdl-grid demo-content" style="height: auto"></div>
	  <div ng-controller="dashboardController">
	   <div id="line_top_x" style="width: auto; height: auto;" class="mdl-grid demo-content"></div>
		
		  <%-- <jsp:include page="footer.jsp"></jsp:include> --%>
		</div>
		<h4 style="text-align: center;">Loss or Gain<span id="gainlossspan"  style="color:red">
<b><%=lossgain%></b></span></h4>
<h4 style="text-align: center;">Recent Transaction History</h4>
		<div id="test_div">
					<table class="table table-striped table-hover table-users"
						id="dynamic_table">
						<thead>
							<tr>
								<!-- <th>Id</th> -->
								<th>Amount</th>
								<th>Category</th>
								<th>Date</th>
								<th>Description</th>
								<th></th>
								<th></th>
							</tr>
						</thead>
						<tbody id="tbody_id">
						<form:form modelAttribute="expense_data">
						<c:forEach items="${expense_data}" var="expense" varStatus="status">
									<tr>
										<td>${expense.expenseAmount}</td>
										<td>${expense.expenseCategoryId}</td>
										<td>${expense.expenseDate}</td>
										<td>${expense.expenseDescription}</td>
									</tr>
								</c:forEach>
							</form:form>
						</tbody>
					</table>
				</div>
      </main>
     <div>
    </div>
    </div>
      <svg xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" version="1.1" style="position: fixed; left: -1000px; height: -1000px;">
        <defs>
          <mask id="piemask" maskContentUnits="objectBoundingBox">
            <circle cx=0.5 cy=0.5 r=0.49 fill="white" />
            <circle cx=0.5 cy=0.5 r=0.40 fill="black" />
          </mask>
          <g id="piechart">
            <circle cx=0.5 cy=0.5 r=0.5 />
            <path d="M 0.5 0.5 0.5 0 A 0.5 0.5 0 0 1 0.95 0.28 z" stroke="none" fill="rgba(255, 255, 255, 0.75)" />
          </g>
        </defs>
      </svg>
      <svg version="1.1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 500 250" style="position: fixed; left: -1000px; height: -1000px;">
        <defs>
          <g id="chart">
            <g id="Gridlines">
              <line fill="#888888" stroke="#888888" stroke-miterlimit="10" x1="0" y1="27.3" x2="468.3" y2="27.3" />
              <line fill="#888888" stroke="#888888" stroke-miterlimit="10" x1="0" y1="66.7" x2="468.3" y2="66.7" />
              <line fill="#888888" stroke="#888888" stroke-miterlimit="10" x1="0" y1="105.3" x2="468.3" y2="105.3" />
              <line fill="#888888" stroke="#888888" stroke-miterlimit="10" x1="0" y1="144.7" x2="468.3" y2="144.7" />
              <line fill="#888888" stroke="#888888" stroke-miterlimit="10" x1="0" y1="184.3" x2="468.3" y2="184.3" />
            </g>
            <g id="Numbers">
              <text transform="matrix(1 0 0 1 485 29.3333)" fill="#888888" font-family="'Roboto'" font-size="9">500</text>
              <text transform="matrix(1 0 0 1 485 69)" fill="#888888" font-family="'Roboto'" font-size="9">400</text>
              <text transform="matrix(1 0 0 1 485 109.3333)" fill="#888888" font-family="'Roboto'" font-size="9">300</text>
              <text transform="matrix(1 0 0 1 485 149)" fill="#888888" font-family="'Roboto'" font-size="9">200</text>
              <text transform="matrix(1 0 0 1 485 188.3333)" fill="#888888" font-family="'Roboto'" font-size="9">100</text>
              <text transform="matrix(1 0 0 1 0 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">1</text>
              <text transform="matrix(1 0 0 1 78 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">2</text>
              <text transform="matrix(1 0 0 1 154.6667 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">3</text>
              <text transform="matrix(1 0 0 1 232.1667 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">4</text>
              <text transform="matrix(1 0 0 1 309 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">5</text>
              <text transform="matrix(1 0 0 1 386.6667 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">6</text>
              <text transform="matrix(1 0 0 1 464.3333 249.0003)" fill="#888888" font-family="'Roboto'" font-size="9">7</text>
            </g>
            <g id="Layer_5">
              <polygon opacity="0.36" stroke-miterlimit="10" points="0,223.3 48,138.5 154.7,169 211,88.5
              294.5,80.5 380,165.2 437,75.5 469.5,223.3 	"/>
            </g>
            <g id="Layer_4">
              <polygon stroke-miterlimit="10" points="469.3,222.7 1,222.7 48.7,166.7 155.7,188.3 212,132.7
              296.7,128 380.7,184.3 436.7,125 	"/>
            </g>
          </g>
        </defs>
      </svg>
     <!--<a href="https://github.com/google/material-design-lite/blob/mdl-1.x/templates/dashboard/" target="_blank" id="view-source" class="mdl-button mdl-js-button mdl-button--raised mdl-js-ripple-effect mdl-button--colored mdl-color-text--white">View Source</a>-->
    <script src="https://code.getmdl.io/1.3.0/material.min.js"></script>
      <div id="ProfileForm" class="modal fade" role="dialog" 
					data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content"></div>
					</div>
		</div>
		<div id="modalForm" class="modal fade" role="dialog" 
					data-backdrop="static" data-keyboard="false">
					<div class="modal-dialog">
						<!-- Modal content-->
						<div class="modal-content"></div>
					</div>
		</div>
	</body>

	<!-- google donut chart -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
    google.charts.load("current", {packages:["corechart"]});
      google.charts.setOnLoadCallback(drawChart);
     function drawChart() {
        var data = google.visualization.arrayToDataTable([
          ['Task', 'Hours per Day'],
          ['income',     <%=totalincome%>],
          ['expense',      <%=totalexpense%>]
          ]);
        var options = {
          title: '',
          pieHole: 0.2,
          'is3D':false,
          //backgroundColor: 'transparent',
          //backgroundColor: '#F5F5F5',
         height:400,
         };
        var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
        google.visualization.events.addListener(chart, 'select', selectHandler);
        function selectHandler() {
            var selectedItem = chart.getSelection()[0];
            if (selectedItem) {
              var topping = data.getValue(selectedItem.row, 0);
              //alert('The user selected ' + topping);
              window.location=topping;
            }
          }
        chart.draw(data, options);
      }
      </script>
  
 
  <!-- line chart -->
<!--     <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
  <script type="text/javascript">  
  google.charts.load('current', {'packages':['line'],callback: drawLineChart});
  google.charts.setOnLoadCallback(drawLineChart);

  function drawLineChart(){
	  //alert("draw chart fun");
	  var response=callchartajax();
	  //var response={"expenseAmount":343,"incomeAmount":727}; 
		//var response=[{"expenseAmount":343,"mydate":new Date('2015-01-01'),"incomeAmount":727},{"expenseAmount":32332,"mydate":new Date('2015-01-02'),"incomeAmount":111},{"expenseAmount":34231,"mydate":new Date('2015-01-3'),"incomeAmount":321}];
		//alert(response);
		//document.getElementById('line_top_x').innerHTML=response.expenseAmount;
			chartData = new google.visualization.DataTable();
			//chartData.addColumn('number', 'Number');
			//chartData.addColumn('date', 'Day');
			chartData.addColumn('number', 'Income');
			chartData.addColumn('number', 'Expense');
			//chartData.addColumn('number', 'Budget');
			var row = [];
						$(response).each(function(index, element) {
							    //alert('index'+index+'expense: ' + element.expenseAmount+ ', income: ' + element.incomeAmount);
								//row.push([index, response.incomeAmount, response.expenseAmount]);
							    chartData.addRow([element.expenseAmount,element.incomeAmount]);	
						 	});
			var chartOptions = {
			        chart: {
			            title: '',
			            subtitle: '',
			          },			          
					   width: 800,
			          height: 400, 
			         /*  axes: {
			            x: {
			              0: {side: 'top'}
			            }
			          } */
			        }
			 //chartData = new google.visualization.DataTable();		     
		     var chart = new google.charts.Line(document.getElementById('line_top_x'));
			//var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
			
		     chart.draw(chartData, chartOptions);
		
		
	}

  function callchartajax() {
	  var xhttp = new XMLHttpRequest();
	  xhttp.onreadystatechange = function() {
	    if (this.readyState == 4 && this.status == 200) {
	     return this.responseText;
	    }
	  };
		//below line for production
	  //xhttp.open("GET", "https://my-expensemanger.rhcloud.com/dashboard/graph1", true);
	  //below line for local
	  xhttp.open("GET", "http://localhost:9090/ExpenseManager/dashboard/graph1", true);
	  xhttp.send();
	}
  </script>
 --> 
     </html>
