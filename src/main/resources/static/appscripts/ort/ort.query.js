'use strict';
/*
 * anwendungsfallspezifische Query fuer das Filtern, Sortieren und Paging,
 * wird ueber den Controller veroeffentlicht
 */
var app = angular.module('wetterApp');

app.factory('OrtQuery', ['$log', 'Query', 'OrtService', function($log, Query, OrtService) {
	var query = Query.createNew(OrtService);
	query.sort = {
		orderBy: 'ort',
		reverse: false
	}
	//query.paging = undefined;
	query.paging = {
			currentPage: 1,
			itemsPerPageEdit: 10
		}
	query.isFilterEmpty = function() {
		return !query.filter || !query.filter.keyword;
	}
	return query;
}]);
