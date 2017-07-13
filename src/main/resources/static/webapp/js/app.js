'use strict';

var appmodule = angular.module('app', ['ngRoute','BookModule']).
config(['$routeProvider', function ($routeProvider) {	
	$routeProvider.when('/home', { templateUrl: 'home.html', controller: 'homeController'})
    .when('/addcomp', { templateUrl: 'addcomp.html', controller: 'addcompController' })
    .when('/compbooks', { templateUrl: 'compbooks.html', controller: 'compbooksController' })
    .when('/geteditbook', { templateUrl: 'editbook.html', controller: 'editbookController' })
    .when('/geteditprofile', { templateUrl: 'editprofile.html', controller: 'editprofileController' })
    .when('/addforlat', { templateUrl: 'addforlater.html', controller: 'addforlaterController' })
    .otherwise({ redirectTo: '/home' });
}]);

angular.module('BookModule', []);