"use strict";

/*
 * Fragt ueber den Service WetterService die Wetterdaten aus der DB ab
 * 
 */
wetterApp.controller('OrtDetailCtrl', function($scope, $log, $routeParams, $resource, $cookies, $locale, $location, OrtService) {

    console.log('OrtDetailCtrl');
    console.log('$routeParams' + angular.toJson($routeParams));
		
	
	$scope.isAdmin = false;
	$scope.isNew = false;
	
	var model = {'id' : $routeParams.id};
	var isDeleting = false;
	var isSaving = false;
	var isEditing = true;
	
	// Neuanlage oder vohandener Datensatz
	$scope.isNew = function() {
		console.log('isNew?');
		console.log('model.id: ' + model.id);
		if (model.id == undefined){
			console.log('new record');
			return true;
		} else {
			console.log('edit record');
			return false;
		}
	}
	
	// Gibt an, ob die Maske im bearbeitungsmodus ist
	$scope.isEditing = function() {
		return true;
		// return isEditing || $scope.isNew();
	};  
	
	/* Datensatz laden */
	$scope.load = function(){
		console.log('load Record');
		console.log('model: ' + angular.toJson(model));
		// Laden eines Datensatzes. model enthaelt den Filter als JSON-Objekt {'id' : $routeParams.id}
		OrtService['byId'](model).$promise
		.then(function(data){
			//console.log('getById: ' + angular.toJson(data));
			$scope.ort = data;
		})
		.catch(function(err){
			console.log('getById failed: ' + angular.toJson(err.data || err));
		});
		/**/
	}
	
	/* Datensatz editieren*/
	$scope.onEdit = function() {
		console.log('onEdit');
		isEditing = true;
		$scope.load();
	}	
	
	
	/* Datensatz speichern */	
	$scope.onSave = function(data){
		console.log('onSave: '); /* + data.id); */
		isSaving = true;
		isEditing = false;	  
	  
		// do save
		OrtService['save'](null, data).$promise
		.then(function(data) {
			console.log('saving succeeded: ' + angular.toJson(data));
			isSaving = false;
			// Laden der Werte aus der Datenbank model.id = data.id;
			$log.debug('loading ' + model.id); 
			$scope.load();
			
		})
		.catch(function(err) {
			console.log('saving failed: ' + angular.toJson(err.data || err));
			isSaving = false;
		     // Laden der Werte aus der Datenbank
			$scope.load();
		});
		
		// navigate to list view
		$location.path('orte');
	}
  
	/* Datensatz loeschen */
	$scope.onDelete = function(data){
		console.log('onDelete: ');
		// do delete
		OrtService['delete'](data).$promise
		.then(function(data) {
			$log.info('deleting succeeded: ' + angular.toJson(data));
			isDeleting = false;
			// Zurück zur Liste
			$location.path('anwendung');
		})
		.catch(function(err) {
			$log.error('deleting failed: ' + angular.toJson(err.data || err));
			isDeleting = false;
		});	
	}

	/* Zurueck zur Uebersicht */
	$scope.onCancel = function(){
		console.log('cancel: Ort');
		$location.path('orte');
	}
  
	
	// Initialisierung
	console.log('Initialisierung...');
	if (!$scope.isNew()){
		console.log('edit record...');
		$scope.load();
	} else {
		console.log('new record...');
		$scope.ort = {
				'id' : undefined
				, 'ort' : undefined
				, 'urlOpenweatherOrtAktuell' : undefined
				, 'urlOpenweatherOrtVorhersage' : undefined
		};
	}

	
});
