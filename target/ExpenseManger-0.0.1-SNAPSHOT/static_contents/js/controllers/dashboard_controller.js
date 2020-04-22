angular.module('expenseManager')
.controller('dashboardController', ['$scope', '$http', function($scope, $http){	
	//google.charts.load('current', {'packages':['line']});
    
/*	function drawLineChart(){
		
//		below line for local
//		$http.get('/dashboard/graph1')
		
//		for production
		$http.get('https://my-expensemanger.rhcloud.com/dashboard/graph1')
		
		.then(function(response){
			
			$scope.chartData = new google.visualization.DataTable();
			$scope.chartData.addColumn('number', 'Expense');
			$scope.chartData.addColumn('number', 'All Income');
			$scope.chartData.addColumn('number', 'All Expenses');
			var row = [];
			response.data.forEach(function(obj, index){
				row.push([index, obj.incomeAmount, obj.expenseAmount]);
			});
						
			$scope.chartData.addRows(row);
	
			
			
			var chartOptions = {
			        chart: {
			            title: 'All Expenses till date',
			            subtitle: 'in Rupees',
			 
			          },
			          width: auto,
			          height: auto,
			          axes: {
			            x: {
			              0: {side: 'top'}
			            }
			          }
			        },
			 chartData = new google.visualization.DataTable();		     
		     var chart = new google.charts.Line(document.getElementById('line_top_x'));
			//var chart = new google.visualization.PieChart(document.getElementById('donutchart'));
			
		     chart.draw($scope.chartData, chartOptions);
		});
		
				
	}
	
	google.charts.setOnLoadCallback(drawLineChart);
}]);



//
//angular.module('expenseManager')
//.controller('dashboardController', ['$scope', '$http', function($scope, $http){	
//	google.charts.load('current', {'packages':['line']});
//    
//	function drawChart(){
//		var expenseObj;
//		var incomeObj;
//		var expenseLength,incomeLength;
//		$http.get('/expmgr/dashboard/graph1')
//		.then(function(response){		
//			response.data.forEach(function(obj){
//				expenseLength = expenseLength + 1;
//			});
//			expenseObj = response.data;
//		});
//		$http.get('/expmgr/dashboard/graph2')
//		.then(function(response){
//			response.data.forEach(function(obj){
//				incomeLength = incomeLength + 1;
//			});
//			incomeObj = response.data;
//		});
//		max=incomeLength;
//		if(expenseLength>incomeLength)
//			max=expenseLength;
//		
//		var row = [];
//			
//		for(var i=0;i<max;i++){
//			row.push([i,expenseObj.expenseAmount,expenseObj.expenseAmount-100]);
//		}
//		
//		var chartData = new google.visualization.DataTable();
//		chartData.addColumn('number', 'Expense');
//		chartData.addColumn('number', 'All Expenses');
//		chartData.addColumn('number', 'All Income');
//		chartData.addRows(row);
//		var chartOptions = {
//		        chart: {
//		            title: 'Expense in current Month',
//		            subtitle: 'in Rupees'
//		          },
//		          width: 1000,
//		          height: 500,
//		          axes: {
//		            x: {
//		              0: {side: 'top'}
//		            }
//		          }
//		        };
////		 chartData = new google.visualization.DataTable();		     
//	     var chart = new google.charts.Line(document.getElementById('line_top_x'));
//
//		
//	     chart.draw(chartData, chartOptions);		
//	}
//	
//	google.charts.setOnLoadCallback(drawChart);*/
}]);