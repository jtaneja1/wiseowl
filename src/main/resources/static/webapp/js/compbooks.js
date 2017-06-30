'use strict';

/**
 * 
 */

angular.module('BookModule')
.controller('compbooksController', ['$scope', '$interval', '$location', '$http','$filter', '$anchorScroll', function ($scope, $interval, $location, $http, $filter,$anchorScroll) {
    console.log('compbooksController created.');
    
    $scope.getcompbooks=function(){
		console.log('Retrieving all books read by the user ---');
				
		var httpReq = $http.get("/book/getcompbooks/-1");
		
		httpReq.success(function(dataFromServer, status, headers, config) 
		{
				    				
			  if(dataFromServer==='') 
			  {
				console.log('The Books were NOT retrieved since session has expired. Redirecting now ');
				window.location.href = "login.html";
			  }
			  console.dir(dataFromServer);
			  $scope.compbooklist=dataFromServer;
	          console.log('The following Books were retrieved : '+console.dir($scope.compbooklist));
	          	          	          
	    });
		
		httpReq.error(function(data, status, headers, config) 
		{
	          alert("Book retrieval failed!");    	          
	    });    		    		
    };//end of submitCompBook function
    
    $scope.getcompbooks();
    
    
    $scope.sortType     = 'book_read_date'; 
    $scope.sortReverse  = true;  			
    
}]);