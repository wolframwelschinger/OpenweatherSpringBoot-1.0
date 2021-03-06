"use strict";

/* Fragt mit Hilfe des Services WetterOnlineService Wetterinformationen ueber die OpenweatherMap-REST-Schnittstelle
 * fuer die Orte aus dem Internet ab, die in der Datenbank gespeichert sind
 * 
 */
wetterApp.controller('WetterOnlineByOrtSelectionCtrl', function($scope, $log, $routeParams, $resource, $cookies, $locale, WetterOnlineService
		, Flash, OrtService) {

	var ort = $routeParams.ort;
	$scope.isAdmin = false;
	$scope.orte = {};
	
	//16.02.18: https://plnkr.co/edit/C1Xly1dmOwMPaap00umX?p=preview
	//$scope.myHtml = "<h2>Hallo from WetterApp/h2>";	
 
	$log.debug('+++ Ort: ' + ort);
	$log.debug('+++ Locale: ' + $locale.id);

	
	
	/**
	 * Wechsel der Auswahl des Ortes feststellen
	 */
    $scope.onOrtChanged = function() {
        $log.debug('Wechsel des Ortes: ' + $scope.selectedOrt.ort);
        $scope.selectedOrtId = $scope.selectedOrt.id;
        $log.debug('Wechsel des Ortes: ' + $scope.selectedOrt.ort + ' (id=' + $scope.selectedOrtId + ')');
        // Wetterdaten laden
        if ($scope.selectedOrtId != undefined){
        		$scope.loadWetterdaten();
        }
    }  	
	
    /**
     * Wetterdaten aktualisieren
     */
    $scope.loadWetterdaten = function() {
        $log.debug('loadWetterdaten() - Laden der Wetterdaten des Ortes mit PK=' + $scope.selectedOrtId);
	    	WetterOnlineService['byOrtId']({id: $scope.selectedOrtId}).$promise
	    	.then(function(data){
	    		$log.debug('Oline-Wetterdaten wurden geladen');
	    		$scope.wetter = data;
	    		$log.debug(data);
	    		$log.debug(data.wetterbeschreibungen);
	    		$log.debug(data.wetterbeschreibungen.length);
	    		if (data.wetterbeschreibungen.length > 0){	  
	    			$log.debug('Wetterbeschreibung vorhanden.');
	    			$log.debug(data.wetterbeschreibungen[0].description);
	    			$log.debug(data.wetterbeschreibungen[0].iconUrl);
	    			$scope.wetterBeschreibung = data.wetterbeschreibungen[0].description;
	    			$scope.wetterIconUrl = data.wetterbeschreibungen[0].iconUrl;
	    		} else {
	    			$log.debug('Keine Wetterbeschreibung vorhanden.');
	    		}
	    		
	    		$scope.myHtml = data.htmlVorhersageTile;
	    		$log.debug('Tile: ' + $scope.myHtml);

	    		
	    	})
	      	.catch(function(err) {
	      		$log.error('loading OpenWeatherMap-Data failed: ' + angular.toJson(err.data || err));
	    		Flash.create('error', 'Fehler beim Laden der Wetterdaten aus dem Internet! - Bitte pr&uuml;fen Sie die Onlineverbindung! - ' + err);
	    	});;      	
    }

    /*
    $scope.loadOrtItems = function() {
    		$log.debug('Lade Ort-Items...');
    	    var paramsOrt = { 'sort': { 'orderBy' : 'ort', 'reverse' : false }};
		OrtService['get'](paramsOrt).$promise
		.then(function(data){
			$log.debug('Ort-Daten: ' + data);
			$scope.orte = data.content;
			for (var i=0; i < $scope.orte.length; i++){
				$log.debug(i + '. Ort: ' + $scope.orte[i].ort);
			}
			$scope.selectedOrt = $scope.orte[0];
			$log.debug('selectedOrt ' + $scope.selectedOrt.ort);
		})
		.catch(function(err){
	 		$log.error('Laden der Ort-Items fehlgeschlagen: ' + angular.toJson(err.data || err));
		});
    }
    */
    
    /**
     * Fetchen der Ort fuer die Combobox-Auswahl
     */
    $scope.loadOrtItems = function() {

    		var hasPankow = false;
		$log.debug('Lade alle Ort-Items...');
		OrtService['allOrtItems']().$promise
		.then(function(data){
			$log.debug('Ort-Daten: ' + data);
			$scope.orte = data;
			for (var i=0; i < $scope.orte.length; i++){
				$log.debug(i + '. Ort: ' + $scope.orte[i].ort);
				if ($scope.orte[i].ort == 'Berlin Pankow'){
					hasPankow = true;
					$scope.selectedOrt = $scope.orte[i];
					$scope.selectedOrtId = $scope.selectedOrt.id;
					$log.debug('selectedOrt: ' + $scope.selectedOrt.ort + ', selectedOrtId: ' + $scope.selectedOrtId
							+ ', hasPankow: ' + hasPankow);					
				}
			}
			// Falls Pankow nicht gefunden wurde, ersten Eintrag vorbelegen
			if (!hasPankow){
				$scope.selectedOrt = $scope.orte[0];
				$scope.selectedOrtId = $scope.selectedOrt.id;
				$log.debug('selectedOrt: ' + $scope.selectedOrt.ort + ', selectedOrtId: ' + $scope.selectedOrtId);				
			}
	        // Wetterdaten laden
	        if ($scope.selectedOrtId != undefined){		
				$log.debug('Aktualisieren mit Wetterdaten zu Ort mit PK=' + $scope.selectedOrtId);
				$scope.loadWetterdaten();
	        } else {
	        		$log.debug('selectedOrtId: undefined!');
	        }			
		})
		.catch(function(err){
	 		$log.error('Laden der Ort-Items fehlgeschlagen: ' + angular.toJson(err.data || err));
		});
		
	}

	
	// -------------- Main-------------------------------------------
	
    // Combobox mit Orten fuellen
    $scope.loadOrtItems();
	
    // Sofern keine Auswahl ermittelt werden kann, Standard setzen
	if ($scope.selectedOrtId == undefined){
		$log.debug('selectedOrId undefined, setze 1');
		$scope.selectedOrtId=1;
	} 

	// Wetterdaten laden
	$scope.loadWetterdaten();
	


});
