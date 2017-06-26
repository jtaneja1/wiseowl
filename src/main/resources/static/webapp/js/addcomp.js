'use strict';

/**
 * 
 */

angular.module('BookModule')
.controller('addcompController', ['$scope', '$interval', '$location', '$http', '$anchorScroll', function ($scope, $interval, $location, $http, $anchorScroll) {
    console.log('addcompController created.');
     
    $scope.compbook={
    		book_title:'',
    		book_author_name:'',
    		book_read_rating:'',
    		book_read_comments:'',
    		book_read_date:'',
    		book_read_format:'',
    		book_read_source:'',    		
    		book_notes:null,
    		book_publish_date:null,    		    		
    		book_ISBN_13:null
    			};
      
    $scope.showSubmitError=false;
    
    			
    			
    $scope.submitCompBook=function(formname){
    		console.log('The following book were submitted by the user ---');
    		console.dir($scope.compbook);
    		
    		if(formname.$pristine || formname.$invalid){
    			$scope.showSubmitError=true;
    			$anchorScroll();
    			return;
    			}
    		
    		var httpReq = $http.post("/book/addCompBook", $scope.compbook);
    		
    		httpReq.success(function(dataFromServer, status, headers, config) {
    				    				
    			  if(dataFromServer=='') 
    			  {
    				console.log('The Book was NOT added. redirecting now ');
    				window.location.href = "login.html";
    			  }
    	          console.log('The Book was added '+dataFromServer.book_title);
    	          $location.path("/home");
    	          
    	    });
    		httpReq.error(function(data, status, headers, config) {
    	          alert("Submitting form failed!");    	          
    	    });    		    		
    };

    $scope.doesShow=function(element){
    	if((angular.isDefined(element) && element.$invalid && element.$dirty) || (angular.isDefined(element) && element.$invalid && $scope.showSubmitError)){return true;}
    	return false;    	
    };
    
    
    $scope.getErrorText = function (error) {
		if (angular.isDefined(error)) {
			if (error.required) {
				return "This is a required value";
			} else if (error.pattern) {
				return "Please enter a valid value";
			}
		}
};
    
}]);