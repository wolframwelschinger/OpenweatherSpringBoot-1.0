'use strict';
/*
 * anwendungsfallspezifische Query fuer das Filtern, Sortieren und Paging,
 * wird ueber den Controller veroeffentlicht
 */
var app = angular.module('wetterApp');

app.factory('WetteraufzeichnungQuery', ['$log', 'Query', 'WetteraufzeichnungService', function($log, Query, WetteraufzeichnungService) {
	var query = Query.createNew(WetteraufzeichnungService);
	query.sort = {
		orderBy: 'id',
		reverse: true
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
