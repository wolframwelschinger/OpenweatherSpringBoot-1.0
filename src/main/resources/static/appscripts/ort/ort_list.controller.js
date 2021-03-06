"use strict";

/*
 * Fragt ueber den Service WetterService die Wetterdaten aus der DB ab
 * 
 */
wetterApp.controller('OrtListCtrl', function($scope, $log, $resource, $cookies, $locale, OrtService, OrtQuery, $uibModal , Flash) {

  $scope.isAdmin = false;

  $scope.orte = undefined;
  $scope.ortFilter = 'Berlin Pankow';
  $scope.query = OrtQuery;    		// <-- Query-Instanz (Filtern und Paging)
  $scope.query.clearFilter();
  // Filter
  //$scope.query.filter.ort = 'Berlin Pankow';
  $scope.query.load('ort');  		// <-- Ausloesen der Query
  
  console.log('+++ Locale: ' + $locale.id);

  /**
	* Callback-Handler fuer das erfolgreiche Laden des REST-Response
	*/
  OrtQuery.onLoadSuccess = function(results) {
      $log.debug(results);
      $log.debug('>>> Anzahl der gefetchten Datensaetze: ' + results.length)
      $scope.orte = results;  // <-- Fetching des REST-Response
  }
  
  /**
	* Callback-Handler fuer Fehler beim Laden des REST-Response
	*/
  OrtQuery.onLoadFailure = function(err) {
      $log.debug('err' + err);
      // Flash.create('error', err);
  }

  /**
	* Callback vor dem Ausführen des REST-Requests
	*/
  OrtQuery.onLoad = function() {
      $log.debug('onLoad');
  } 
  
  
  /**
	* Hinweistext fuer die Loeschabfrage erzeugen
	*/
  $scope.popupDeleteNote = function(data) {
          return 'Sind Sie sicher, dass der Ort mit dem Namen "' + data.ort + '" (id=' + data.id + ') unwiderruflich gelöscht werden soll?';
  }  
  
  /**
	* Oeffnet den Dialog fuer die Loeschabfrage
	*/
  $scope.openConfirmDialog = function(data){
	  $log.debug('opening popup');
	  
	  var deleteNote = $scope.popupDeleteNote(data);
	  
	  var modalInstance = $uibModal.open({
		  templateUrl: 'appscripts/common/confirm-delete.html',
		  controller: ['$scope', '$uibModalInstance', function($scope, $uibModalInstance) {
			  $scope.deleteNote = deleteNote;
              $scope.ok = function() {
                      $log.debug('deleting confirmed');
                      $uibModalInstance.close(data);
              }
              $scope.cancel = function() {
                      $log.debug('deleting rejected');
                      $uibModalInstance.dismiss('rejected');
              }
		  }],
	  });
  	
	  modalInstance.result.then(function(data) {
		  		  
		  $log.debug('remove ' + data);
		  
		  // do delete
		  OrtService['delete'](data).$promise
		  .then(function(data) {
			  $log.debug('deleting succeeded: ' + angular.toJson(data));
			  /* */
			  // Fehlermeldung: unknown provider FlashProvider <- Flash <- OrtListCtrl
			  Flash.create('success', 'Ort erfolgreich geloescht (' +
				 data.deletedId + ')');
			  /* */	 
			  // Liste aktualisieren
			  $scope.query.load('ort');
		  })
		  .catch(function(err) {
			  $log.error('deleting failed: ' + angular.toJson(err.data || err));
			  //Flash.create('error', err);
		  });
	   
      }, function() {
    	  	$log.debug('Modal dismissed at: ' + new Date());
      }); 	
  	
  }  
  
  /**
   * Aktualisiert die Abfrage fuer die Ortsuebersicht
   */
  $scope.ortFilterChanged = function(){
	  $log.debug('Update OrtFilter: ' + $scope.ortFilter);
	  $scope.query.filter.ort = $scope.ortFilter;
	  $scope.query.load('ort');  		// <-- Ausloesen der Query	  
  }
  
  
  $scope.clearFilter = function(){
	  $log.debug('Reset Filter');
	  $scope.ortFilter = undefined;
	  $scope.query.clearFilter();
	  $scope.query.load('ort');
  }
  
  
  /*
	 // alle Orte in DE suchen
	 var paramsDE = { 'filter' : { 'land' : 'DE' }, 'sort' : { 'orderBy' : 'ort', 'reverse' : false } }; 
	 // alle Orte (kein Filter), sortiert nach Ort, Page 1 
	 var params = { 'filter' : {}, 'sort' : {'orderBy' : 'ort', 'reverse' : false }, 'page' : { 'index' : 0} }; 
	 // Laden der Ort-Entries per REST-Call aus der DB
	 OrtService['get'](params).$promise .then(function(data){
	 	console.log('Daten wurden geladen'); console.log(data);
	 	console.log('Anzahl der Datensaetze: ' + data.totalElements);
	 	$scope.orte =  data.content; 
	 });
   */
  
});
