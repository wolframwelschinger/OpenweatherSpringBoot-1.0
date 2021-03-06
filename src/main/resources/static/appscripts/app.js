"use strict";

var wetterApp = angular.module('wetterApp', [
  'ngRoute'
  , 'ngResource'
  , 'ngAnimate'
  //, 'ngTouch'
  , 'ui.bootstrap'	// <-- UI Bootstrap
  , 'ngFlash' 		// <-- Meldungen ueber angular-flash.js (@see https://github.com/sachinchoolur/angular-flash)  
  , 'ngCookies'
  , 'ngLocale'
  , 'ngSanitize'
]);

console.log('app.js');

wetterApp.config(function ($routeProvider) {
    console.log('config: ' + $routeProvider); 
    /* Liest die gepeicherten Wetterdaten aus der DB */
    $routeProvider.when('/wetterdaten', {                  
        templateUrl: 'templates/wetteraufzeichnung_list.html',
        controller: 'WetteraufzeichnungListCtrl'
    })
    /* Fragt das Wetter an einem bestimmten Ort ab*/
    .when('/wetterByOrt/:ort', {
      templateUrl: 'templates/wetter.html',
      controller: 'WetterOnlineCtrl'
    })
    /* Fragt das Wetter an einem bestimmten Ort ab*/
    .when('/wetterByOrtSelection', {
      templateUrl: 'templates/wetter_dbstored.html',
      controller: 'WetterOnlineByOrtSelectionCtrl'
    })
    .when('/orte', {
      templateUrl: 'templates/ort_list.html',
      controller: 'OrtListCtrl'
    })
    .when('/ortNew', {
      templateUrl: 'templates/ort.html',
      controller: 'OrtDetailCtrl'
    })
    .when('/ortEdit/:id', {
      templateUrl: 'templates/ort.html',
      controller: 'OrtDetailCtrl'
    }) 
    .when('/ort/delete/:id', {
      templateUrl: 'templates/ort.html',
      controller: 'OrtDetailCtrl'
    }) 
    .otherwise({
      redirectTo: '/wetterByOrt/Berlin'
    });
});
