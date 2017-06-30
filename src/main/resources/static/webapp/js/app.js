'use strict';

var appmodule = angular.module('app', ['ngRoute','BookModule']).
config(['$routeProvider', function ($routeProvider) {	
	$routeProvider.when('/home', { templateUrl: 'home.html', controller: 'homeController'})
    .when('/addcomp', { templateUrl: 'addcomp.html', controller: 'addcompController' })
    .when('/compbooks', { templateUrl: 'compbooks.html', controller: 'compbooksController' })
    .otherwise({ redirectTo: '/home' });
}]);

angular.module('BookModule', []);