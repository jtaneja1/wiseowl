'use strict';

angular.module('BookModule')
    .controller('forlaterbooksController', ['$scope', '$interval', '$location', '$http','$filter', '$anchorScroll', function ($scope, $interval, $location, $http, $filter,$anchorScroll) {
        console.log('forlaterbooksController created.');

        $scope.getforlaterbooks=function(){
            console.log('Retrieving all books added for later by the user ---');

            var httpReq = $http.get("/book/getforlaterbooks/-1");

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
        };

        $scope.getforlaterbooks();


        $scope.sortType     = 'book_title';
        $scope.sortReverse  = true;  	

    }]);