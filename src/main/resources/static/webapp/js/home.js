'use strict';

angular.module('BookModule')
.controller('homeController', ['$rootScope', '$scope', '$interval', '$location', '$http', '$anchorScroll', function ($rootScope, $scope, $interval, $location, $http, $anchorScroll) {
    console.log('homeController created.');
    
    $scope.getcompbooks=function(){
    	
    	console.log('Retrieving username ---');
    	var usernameReq = $http.get("/user/getUser");
    	usernameReq.success(function(dataFromServer, status, headers, config) 
		{			     				
			  if(dataFromServer==='')
			  {
				console.log('The User details were NOT retrieved since session has expired. Redirecting now ');
				window.location.href = "login.html";
			  }
			  //console.dir(dataFromServer);
			  $rootScope.user=dataFromServer;
			  console.dir($rootScope.user);	          
	          	          	          
	    });
    			
    	usernameReq.error(function(data, status, headers, config) 
    	{
    	      alert("User details retrieval failed!");    	          
    	});
		
		console.log('Retrieving last 3 books read by the user ---');				
		var httpReq = $http.get("/book/getcompbooks/3");		
		httpReq.success(function(dataFromServer, status, headers, config) 
		{			     				
			  if(dataFromServer==='')
				console.log('The Books were NOT retrieved since session has expired. Redirecting now ');
				window.location.href = "login.html";
			  }
			  console.dir(dataFromServer);
			  $scope.compbooklist=dataFromServer;   
	          	          	          
	    });
		
		httpReq.error(function(data, status, headers, config) 
		{
	          alert("Book retrieval failed!");    	          
	    });    		    		
    };
    
    $scope.getcompbooks();
}]);