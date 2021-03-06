'use strict';

var app = angular.module('wetterApp');

/* Fragt das Wetter online ueber die OpenweatherMap-REST-Schnittstelle aus dem Internet ab 
 * 
 * https://devdactic.com/improving-rest-with-ngresource/
 */

app.factory('WetterOnlineService', ['$resource', function ($resource) {
	
    return $resource('wetter/', {}, {
        query: {
            method: 'GET',
            isArray: false
        },
        byOrtsbezeichnung: {
            method: 'GET',
            url: 'wetter/byOrt/:ort',
            isArray: false
        },
        byOrtId: {
        		method: 'GET',
        		url: 'wetter/wetterByOrtId/:id',
        		isArray: false
        },
        berlin_pankow: {
            method: 'GET',
            url: 'wetter/berlin_pankow',
            isArray: false
        },
        palma_de_mallorca: {
            method: 'GET',
            url: 'wetter/palma_de_mallorca',
            isArray: false
        }
    })	
    
}]);

