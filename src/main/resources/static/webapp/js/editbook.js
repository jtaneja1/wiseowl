'use strict';

angular.module('BookModule')
.controller('editbookController', ['$scope', '$interval', '$location', '$http','$filter', '$anchorScroll', function ($scope, $interval, $location, $http, $filter,$anchorScroll) {
    console.log('editbookController created.');
    
    $scope.geteditbook=function() {
    	console.log('Retrieving the book to be edited ---');
    	var book_ID = ($location.search()).id;
    	var httpReq = $http.get("/book/geteditbook/"+book_ID);
    	console.log("book id is:"+book_ID);
    	
    	httpReq.success(function(dataFromServer, status, headers, config) {
    					    				
    		if(dataFromServer==='')
    		{
    			console.log('The Book to edit was NOT retrieved since session has expired. Redirecting now ');
    			window.location.href = "login.html";
    		}
    		console.dir(dataFromServer);
    		$scope.toeditbook=dataFromServer;
            if($scope.toeditbook.book_read_date){
            	var readdateArr = $scope.toeditbook.book_read_date.split("-");
                $scope.toeditbook.book_read_date = readdateArr[1]+"/"+readdateArr[2]+"/"+readdateArr[0];
            }

            if($scope.toeditbook.book_publish_date) {
                var pubdateArr = $scope.toeditbook.book_publish_date.split("-");
                $scope.toeditbook.book_publish_date = pubdateArr[1] + "/" + pubdateArr[2] + "/" + pubdateArr[0];
            }
    		console.log('The following Book was retrieved for editing: '+console.dir($scope.toeditbook));    		          	          	          
    	});
    	
    	httpReq.error(function(data, status, headers, config) {
    		          alert("Book retrieval for editing failed!");    	          
    	});
    };
    
    $scope.geteditbook();    
    
    $scope.showSubmitError=false;
    $scope.updateCompBook=function(formname){
    		console.log('The following book was updated and submitted by the user ---');
    		console.dir($scope.toeditbook);
    		
    		if(formname.$pristine){
				alert('Nothing to update. Please make some changes before updating');
				return;
			}
			
    		if(formname.$pristine || formname.$invalid){
    			$scope.showSubmitError=true;
    			$anchorScroll();
    			return;
    			}

			var readdateArr = $scope.toeditbook.book_read_date.split("/"); 
			$scope.toeditbook.book_read_date = readdateArr[2]+"/"+readdateArr[0]+"/"+readdateArr[1];

			if($scope.toeditbook.book_publish_date) {
				var pubdateArr = $scope.toeditbook.book_publish_date.split("/");
				$scope.toeditbook.book_publish_date = pubdateArr[2] + "/" + pubdateArr[0] + "/" + pubdateArr[1];
			}

        	$scope.toeditbook.create_user=null;
    		var httpReq = $http.post("/book/updateCompBook", $scope.toeditbook);
    		
    		httpReq.success(function(dataFromServer, status, headers, config) {
    				    				
    			  if(dataFromServer=='')
    			  {
    				console.log('The Book was NOT updated. redirecting now ');
    				window.location.href = "login.html";
    			  }
    	          console.log('The Book was updated '+dataFromServer.book_title);
    	          $location.path("/home");
    	          
    	    });
    		httpReq.error(function(data, status, headers, config) {
    	          alert("Submitting form failed!");    	          
    	    });    		    		
    };//end of updateCompBook function


    $scope.doesShow=function(element){
    	if((angular.isDefined(element) && element.$invalid && element.$dirty) || (angular.isDefined(element) && element.$invalid && $scope.showSubmitError)){return true;}
    	return false;    	
    };

    $scope.datePattern=/^(0[1-9]|1[0-2])\/(0[1-9]|1\d|2\d|3[01])\/(19|20)\d{2}$/;
    
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