'use strict';

var appmodule = angular.module('app', ['ngRoute','BookModule']).
config(['$routeProvider', function ($routeProvider) {	
	$routeProvider.when('/home', { templateUrl: '../html/home.html', controller: 'homeController'})
    .otherwise({ redirectTo: '/home' });
	$routeProvider.when('/addcomp', { templateUrl: '../html/addcomp.html', controller: 'homeController'})
    .otherwise({ redirectTo: '/home' });
}]);

angular.module('BookModule', []);