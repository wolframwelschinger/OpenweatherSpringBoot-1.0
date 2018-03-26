'use strict';

var app = angular.module('wetterApp');

/* Fragt Wetterdaten aus der DB ab
 * 
 * https://devdactic.com/improving-rest-with-ngresource/
 */

app.factory('WetteraufzeichnungService', ['$resource', function ($resource) {
	
    return $resource('restservices/wetteraufzeichnung/', {}, {
        query: {
            method: 'GET',
            isArray: false
        },
        all: {
            method: 'GET',
            url: 'wetter/all',
            isArray: true
        },
        byId: {
            method: 'GET',
            url: 'wetter/byId/:id',
            id: '@wetter'
        }

    })	

    
}]);


