'use strict';

/**
 * 
 */

angular.module('BookModule')
.controller('homeController', ['$rootScope', '$scope', '$interval', '$location', '$http', '$anchorScroll', function ($rootScope, $scope, $interval, $location, $http, $anchorScroll) {
    console.log('homeController created.');
    
    $scope.getcompbooks=function(){
    	
    	console.log('Retrieving username ---');
    	var usernameReq = $http.get("/user/getUser");//retrieves user details
    	usernameReq.success(function(dataFromServer, status, headers, config) 
		{			     				
			  if(dataFromServer==='')//a blank is recd if the server session has ended (java returns null). == check for '' does NOT work since a blank array (if no book is read by a new user) is also interpreted as a ''
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
    			
    			
    	
		console.log('Retrieving last book read by the user ---');
		var httpReq = $http.get("/book/getcompbooks/1");
		httpReq.success(function(dataFromServer, status, headers, config) 
		{			     				
			  if(dataFromServer==='')//a blank is recd if the server session has ended (java returns null). == check for '' does NOT work since a blank array (if no book is read by a new user) is also interpreted as a ''
			  {
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
    };//end of submitCompBook function
    
    $scope.getcompbooks();
}]);