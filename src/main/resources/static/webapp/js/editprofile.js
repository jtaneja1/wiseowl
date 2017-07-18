'use strict';

angular.module('BookModule')
    .controller('editprofileController', ['$scope', '$interval', '$location', '$http','$filter', '$anchorScroll', function ($scope, $interval, $location, $http, $filter,$anchorScroll) {
        console.log('editprofileController created.');

        $scope.geteditprofile=function() {
            console.log('Retrieving the profile to be edited ---');
            //var user_ID = ($location.search()).id;
            var httpReq = $http.get("/user/getUser");

            httpReq.success(function(dataFromServer, status, headers, config) {

                if(dataFromServer==='')
                {
                    console.log('The profile to edit was NOT retrieved since session has expired. Redirecting now ');
                    window.location.href = "login.html";
                }
                console.dir(dataFromServer);
                $scope.toeditprofile=dataFromServer;
                if($scope.toeditprofile.dob.indexOf("-")>-1) {
                    var dobArr = $scope.toeditprofile.dob.split("-");
                    $scope.toeditprofile.dob = dobArr[1] + "/" + dobArr[2] + "/" + dobArr[0];
                    console.log('The following Profile was retrieved for editing: ' + console.dir($scope.toeditprofile));
                }
                else {
                    var dobArr = $scope.toeditprofile.dob.split("/");
                    $scope.toeditprofile.dob = dobArr[1] + "/" + dobArr[2] + "/" + dobArr[0];
                    console.log('The following Profile was retrieved for editing: ' + console.dir($scope.toeditprofile));
                }
            });

            httpReq.error(function(data, status, headers, config) {
                alert("Profile retrieval for editing failed!");
            });
        };

        $scope.geteditprofile();

        $scope.showSubmitError=false;
        $scope.updateProfile=function(formname){
            console.log('The following profile was updated and submitted by the user ---');
            console.dir($scope.toeditprofile);
			
			if(formname.$pristine){
				alert('Nothing to update. Please make some changes before updating');
				return;
			}
			
            if(formname.$pristine || formname.$invalid){
                $scope.showSubmitError=true;
                $anchorScroll();
                return;
            }

            var dobArr = $scope.toeditprofile.dob.split("/");
            $scope.toeditprofile.dob = dobArr[2]+"/"+dobArr[0]+"/"+dobArr[1];

            console.log('The following profile is being sent to the server ---')
            console.dir($scope.toeditprofile);

            var httpReq = $http.post("/user/updateprofile", $scope.toeditprofile);

            httpReq.success(function(dataFromServer, status, headers, config) {

                if(dataFromServer=='')
                {
                    console.log('The Profile was NOT updated. redirecting now ');
                    window.location.href = "login.html";
                }
                console.log('The Profile was updated '+dataFromServer.nickname);
                window.location.href = "#/home"
                //$location.path("/home");

            });
            httpReq.error(function(data, status, headers, config) {
                alert("Submitting form failed!");
            });
        };//end of updateProfile function


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