'use strict';

/* Define all modules here(all feature sets). The only purpose modules serve is as a consolidator of controllers
 * Define controllers(use cases) in separate JS files
 * Each use case = controller = separate JS file
 * Each controller belongs to a module (feature set) and is defined with a .controller() function
 * The job of the controller js is to prepare the data necessary for displaying the next view
 * The app module can access other module's controllers by first defining the module as a dependency
 */

// Declare app level module which depends on filters,services and other modules
var appmodule = angular.module('app', ['ngRoute','BookModule']). //BookModule is defined as a dependency of the app module. App module can now refer to its controllers
config(['$routeProvider', function ($routeProvider) {	
	//each HTML partial is associated with a hash bang URL. here we define hash bang URLs which will be appended 
	//to index.html in the browser window, for example, index.html#/start
	//When linking to these URLs, we use href='#/workout' in the HTML code, but in the JS code, we omit the #
	$routeProvider.when('/home', { templateUrl: 'home.html', controller: 'homeController'}) //BookModule controller. The scope of homeController is only within the home.html page
    .otherwise({ redirectTo: '/home' });
}]);

angular.module('BookModule', []);