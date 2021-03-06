'use strict';

/**
 *
 */

angular.module('BookModule')
    .controller('addforlaterController', ['$scope', '$interval', '$location', '$http', '$anchorScroll', function ($scope, $interval, $location, $http, $anchorScroll) {
        console.log('addforlaterController created.');

        $scope.compbook={
            book_title:'',
            book_author_name:'',
            book_read_rating:null,
            book_read_comments:null,
            book_read_date:null,
            book_read_format:null,
            book_read_source:null,
            book_notes:null,
            book_publish_date:null,
            book_ISBN_13:null
        };

        $scope.showSubmitError=false;

        $scope.searchISBN=function(formname){
            console.log('The following isbn was submitted by the user ---');
            console.dir($scope.compbook.book_ISBN_13);

            var bookAPI="https://www.googleapis.com/books/v1/volumes?q=isbn:"+$scope.compbook.book_ISBN_13;
            var httpReq = $http.get(bookAPI);//search the google API for ISBN and get back book details

            httpReq.success(function(dataFromServer, status, headers, config) {
                //console.log(dataFromServer);
                if(!dataFromServer || !dataFromServer.items)
                {
                    console.log('The Book details were empty');
                    alert("ISBN search failed! Please enter the book details manually");
                    return;
                }
                console.log('The Book details from google api: '+dataFromServer.items[0].volumeInfo.title);
                $scope.compbook.book_title = dataFromServer.items[0].volumeInfo.title;
                $scope.compbook.book_author_name = dataFromServer.items[0].volumeInfo.authors[0];

                var dateArr = dataFromServer.items[0].volumeInfo.publishedDate.split("-");  // ex input "2010-01-18"
                if(dateArr.length === 3){
                	$scope.compbook.book_publish_date =  dateArr[1]+ "/" +dateArr[2]+ "/" +dateArr[0];
                }

            });
            httpReq.error(function(data, status, headers, config) {
                alert("ISBN search failed! Please enter the book details manually");
            });
        };//end of searchISBN function


        $scope.submitCompBook=function(formname){
            console.log('The following book were submitted by the user ---');
            console.dir($scope.compbook);

            if(formname.$pristine || formname.$invalid){
                $scope.showSubmitError=true;
                $anchorScroll();
                return;
            }
            
            if($scope.compbook.book_publish_date) {
            	if(new Date($scope.compbook.book_publish_date).getTime() > new Date().getTime()){
                    alert('The Publish Date cannot be in the future');
                    return;
                }
                var pubdateArr = $scope.compbook.book_publish_date.split("/");
                $scope.compbook.book_publish_date = pubdateArr[2] + "/" + pubdateArr[0] + "/" + pubdateArr[1];
            }
            var httpReq = $http.post("/book/addForlaterBook", $scope.compbook);

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