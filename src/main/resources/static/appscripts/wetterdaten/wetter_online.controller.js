"use strict";

/* Fragt mit Hilfe des Services WetterOnlineService Wetterinformationen ueber die OpenweatherMap-REST-Schnittstelle aus dem Internet ab
 * 
 */
wetterApp.controller('WetterOnlineCtrl', function($scope, $log, $routeParams, $resource, $cookies, $locale, WetterOnlineService
		, Flash, OrtService) {

	var ort = $routeParams.ort;
	$scope.isAdmin = false;
	$scope.orte = {};
	
	//16.02.18: https://plnkr.co/edit/C1Xly1dmOwMPaap00umX?p=preview
	$scope.myHtml = "Hallo from: <h2>WetterApp</h2>";
 
	$log.debug('+++ Ort: ' + ort);
	$log.debug('+++ Locale: ' + $locale.id);

	
	// -------------- Main-------------------------------------------
	
	
	WetterOnlineService['byOrtsbezeichnung']({ort: ort}).$promise
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
		
	})
  	.catch(function(err) {
  		$log.error('loading OpenWeatherMap-Data failed: ' + angular.toJson(err.data || err));
		Flash.create('error', 'Fehler beim Laden der Wetterdaten aus dem Internet! - Bitte pr&uuml;fen Sie die Onlineverbindung! - ' + err);
	});;  

});

wetterApp.filter("trustHtml", ['$sce', function($sce) {
	  return function(htmlCode){
	    return $sce.trustAsHtml(htmlCode);
	  }
	}]);
