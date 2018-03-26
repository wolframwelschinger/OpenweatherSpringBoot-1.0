'use strict';

var app = angular.module('wetterApp');

/* Fragt Wetterdaten aus der DB ab, speichert bzw. loescht diese
 * 
 * https://devdactic.com/improving-rest-with-ngresource/
 */

app.factory('OrtService', ['$resource', function ($resource) {
	
    return $resource('restservices/ort/', {}, {
        query: {
            method: 'GET',
            isArray: false
        },
        allOrtItems: {
            method: 'GET',
            url: 'restservices/ort/all',
            isArray: true
        },
        /*
        view: {
            method: 'GET',
            url: 'restservices/ortView',
            isArray: true
        },
        */        
        byId: {
            method: 'GET',
            url: 'restservices/ort/:id',
            id: '@id'
        },
        save: {
            method: 'POST',
            url: 'restservices/ort/'
        },
        delete: {
            method: 'DELETE',
            url: 'restservices/ort/:id',
            id: '@id'
        }        
    })	
    
}]);
