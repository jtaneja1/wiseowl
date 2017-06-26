'use strict';

/**
 * 
 */

angular.module('BookModule')
.controller('addcompController', ['$scope', '$interval', '$location', '$http', '$anchorScroll', function ($scope, $interval, $location, $http, $anchorScroll) {
    console.log('addcompController created.');
    
    //$scope here is the controller scope or the scope associated with this partial html
    //compbook represents the Book.java on the server and the field names need to be exactly the same as in Book.java
    
    
    $scope.compbook={
    		book_title:'',//all these properties will be filled by Angular automatically, since we defined ng-model="compbook.book_title" in the form
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
    
    			
    			
    $scope.submitCompBook=function(formname){//this method is invoked when the form button is clicked as we defined ng-click="submitCompBook(addcompBookForm)" in the form
    		console.log('The following book were submitted by the user ---');
    		console.dir($scope.compbook);
    		
    		if(formname.$pristine || formname.$invalid){//if user submits the form without any changes || with errors
    			$scope.showSubmitError=true;//show the error message on top of the page
    			$anchorScroll();//scroll to the top of the page to show errors
    			return;
    			}
    		
    		var httpReq = $http.post("/book/addCompBook", $scope.compbook);//posts the json object to the server where it gets converted into Book.java by Jackson library
    		
    		httpReq.success(function(dataFromServer, status, headers, config) {
    				    				
    			  if(dataFromServer=='')//a blank is recd if the server session has ended and the user is trying to submit data (java returns null) 
    			  {
    				console.log('The Book was NOT added. redirecting now ');
    				window.location.href = "login.html";
    			  }
    	          console.log('The Book was added '+dataFromServer.book_title);
    	          $location.path("/home");//equivalent of redirect except that angular intercepts and does the work for us
    	          
    	    });
    		httpReq.error(function(data, status, headers, config) {
    	          alert("Submitting form failed!");    	          
    	    });    		    		
    };//end of submitCompBook function
    
    //$scope.bookAuthorPattern=new RegExp("^[a-zA-Z]");  //validation for author name to only accept alpha characters  
        
    /* individual error message for each element 
     * Shows up under the following circumstances:
     * a)User makes a change(dirty) AND the value is invalid(invalid) OR
     * b)User submits the form (showSubmitError) without entering any value(invalid). 
     * */
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
};//end of getError function
    
}]);