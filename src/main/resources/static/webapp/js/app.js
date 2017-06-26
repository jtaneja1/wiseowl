'use strict';

var appmodule = angular.module('app', ['ngRoute','BookModule']).
config(['$routeProvider', function ($routeProvider) {	
	$routeProvider.when('/home', { templateUrl: 'home.html', controller: 'homeController'})
    .when('/addcomp', { templateUrl: 'addcomp.html', controller: 'addcompController' }) 	//BookModule controller
    .otherwise({ redirectTo: '/home' });
}]);

angular.module('BookModule', []);