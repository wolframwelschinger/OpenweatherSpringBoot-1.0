"use strict";

/* Fragt ueber den Service WetterService die Wetterdaten aus der DB ab 
 * 
 * $modal --> uibModal ist der Popup-Service von UI-Bootstrap
 * https://stackoverflow.com/questions/18733680/unknown-provider-modalprovider-modal-error-with-angularjs
 * 
 */
wetterApp.controller('PopupCtrl', function($scope, $log, $uibModal, $resource, $cookies, $locale) {

/* Hinweistext fuer die Loeschabfrage */    
$scope.popupDeleteNote = function(/*data*/) {
//        return 'Sind Sie sicher, dass der Katalogwert mit dem Namen "' + data.bezeichnung + '" (id=' + data.id + ') unwiderruflich gelöscht werden soll?';
        return 'Sind Sie sicher, dass der Katalogwert unwiderruflich gelöscht werden soll?';
}  
    
$scope.open = function(){
	$log.debug('opening popup');
	var deleteNote = $scope.popupDeleteNote(/*data*/);
	var modalInstance = $uibModal.open({
		templateUrl: 'templates/popup.html',
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
}
  
});
