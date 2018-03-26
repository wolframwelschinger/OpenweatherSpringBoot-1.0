"use strict";

/* Fragt ueber den Service WetterService die Wetterdaten aus der DB ab 
 * 
 */
wetterApp.controller('WetteraufzeichnungListCtrl', function($scope, $log, $resource, $cookies, $locale, WetteraufzeichnungService, WetteraufzeichnungQuery) {

  $scope.isAdmin = false;

  $scope.orte = undefined;
  $scope.query = WetteraufzeichnungQuery;    		// <-- Query-Instanz (Filtern und Paging)
  $scope.query.clearFilter();
  // Filter
  //$scope.query.filter.ort = 'Berlin Pankow';
  $scope.query.load('wetteraufzeichnung');	  		// <-- Ausloesen der Query    
  
  console.log('+++ Locale: ' + $locale.id);

  /**
   * Callback-Handler fuer das erfolgreiche Laden des REST-Response
   */
  WetteraufzeichnungQuery.onLoadSuccess = function(results) {
      $log.debug(results);
      $log.debug('>>> Anzahl der gefetchten Datensaetze: ' + results.length)
      $scope.wetterdaten = results;  // <-- Fetching des REST-Response 
  }
  
  /**
   * Callback-Handler fuer Fehler beim Laden des REST-Response
   */
  WetteraufzeichnungQuery.onLoadFailure = function(err) {
      $log.debug('err' + err);
      //Flash.create('error', err);
  }

  /**
   * Callback vor dem Ausführen des REST-Requests
   */
  WetteraufzeichnungQuery.onLoad = function() {
      $log.debug('onLoad');
  } 

  /*
  / Laden der Wetter-Entries per REST-Call aus der DB 
  //WetterdatenService.all().$promise
  WetterdatenService['all']().$promise
  .then(function(data){
	  console.log('Daten wurden geladen');
	  console.log(data);
	  $scope.wetterdaten = data;
  });
  */

});
