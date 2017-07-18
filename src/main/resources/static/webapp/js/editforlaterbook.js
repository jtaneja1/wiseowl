'use strict';

angular.module('BookModule')
    .controller('editforlaterbookController', ['$scope', '$interval', '$location', '$http','$filter', '$anchorScroll', function ($scope, $interval, $location, $http, $filter,$anchorScroll) {
        console.log('editforlaterbookController created.');

        $scope.geteditforlaterbook=function() {
            console.log('Retrieving the for later book to be edited ---');
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
                
                var pubdateArr = $scope.toeditbook.book_publish_date.split("-");
                $scope.toeditbook.book_publish_date = pubdateArr[1]+"/"+pubdateArr[2]+"/"+pubdateArr[0];
                console.log('The following Book was retrieved for editing: '+console.dir($scope.toeditbook));
            });

            httpReq.error(function(data, status, headers, config) {
                alert("Book retrieval for editing failed!");
            });
        };

        $scope.geteditforlaterbook();

        $scope.showSubmitError=false;
        $scope.updateforlaterBook=function(formname){
            console.log('The following book was updated and submitted by the user ---');
            $scope.toeditbook.create_user=null;
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

            
            if($scope.toeditbook.book_publish_date) {
                var pubdateArr = $scope.toeditbook.book_publish_date.split("/");
                $scope.toeditbook.book_publish_date = pubdateArr[2] + "/" + pubdateArr[0] + "/" + pubdateArr[1];
            }

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