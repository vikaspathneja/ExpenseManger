angular.module('expenseManager')
.controller('expenseAllController', ['$http', '$scope', function($http, $scope){
	$http.get('expense/food')
	.then(function(response){
		$scope.expenses = response.data;
	});
}]);

angular.module('expenseManager')
.controller('expenseFoodController', ['$http', '$scope', function($http, $scope){
	$http.get('expense/food1')
	.then(function(response){
		$scope.expenses = response.data;
	});
}]);

