'use strict';

var app = angular.module('wetterApp');

/* Fragt Wetterdaten aus der DB ab, speichert bzw. loescht diese
 * 
 * https://devdactic.com/improving-rest-with-ngresource/
 */

app.factory('ReportService', ['$resource', function ($resource) {
	
    return $resource('restservices/report/', {}, {
        query: {
            method: 'GET',
            isArray: false
        }       
    })	
    
}]);
