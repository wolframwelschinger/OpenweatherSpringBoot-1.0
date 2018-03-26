'use strict';

var app = angular.module('wetterApp');

app.controller('PageCtrl', ['$scope', /*'Login',*/ '$location', '$log', '$http', '$route', '$locale', /*'providedValue',*/ 'Flash', function ($scope, /*Login,*/ $location, $log, $http, $route, $locale, /*providedValue,*/ Flash) {
    
    $scope.$on('$viewContentLoaded', function () {
        $log.debug('$viewContentLoaded');
        $log.debug('[FlashMessagesCtrl]: running ctrl');
        Flash.clear();
        $log.debug('[FlashMessagesCtrl]: all messages cleared');
    });

	/**
	 * Steuert die Hervorhebung des ausgewählten Navigationspunktes
	 * path: String or Regexp location path to compare
	 */
	$scope.isActive = function (path) {
	    $log.debug('>>>> ' + path + ' - ' + $location.path() + ' - path.length=' + path.length);
		if (angular.isString(path)) {
//            return path === $location.path();
//		    var result = path === $location.path() || $location.path().indexOf(path + '/') > -1
		    
		    // nur ersten Teil der Location vergleichen
		    var pathArray = $location.path().split('/');
//		    $log.debug(pathArray);
            var result = path === $location.path() || ( path !== '/' && path === '/' + pathArray[1]);

            $log.debug('>>>> path=' + path + ' - $location.path()=' + $location.path() + " - " + result);
            return result;
		} else if(angular.isFunction(path.test)) {
            $log.debug("function path: " + path.test($location.path()) + " path.length=" + path.length);
			return path.test($location.path());
		} else {
			$log.warn('>>>> neither String nor Regexp: ' + path);
		}
		
		return false;
	}
	
	$scope.isHome = function() {
		return $scope.isActive('/');
	}
	
	$scope.isWetterByOrt = function() {
		return $scope.isActive(/^\/wetterByOrt\/.*/);
	}	
	
	
	$scope.isDoc = function() {
		return $scope.isActive(/^\/doc\/.*/);
	}
	
	$scope.isAuthenticated = function() {
        return !!Login.authentication;
    }
	
	$scope.getAuthentication = function() {
        return Login.authentication;
    }
    
    $scope.isAuthenticated = function() {
        return !!Login.authentication;
    }
   

    $scope.hasRole = function(role) {
        return Login.hasRole(role);
    }

    $scope.hasRight = function(right) {
        return (!!Login.authentication && Login.hasRight(right));
    }

	$log.debug('>>>>>>>>>>>>>>>>>>>>>>>> PageCtrl >>>>>>>>>>>>>>>>>>');
	
	/* Ab hier Texte fuer GUI */
	$scope.locale = $locale.id;
	/* Texte in den Scope packen, stehen in texte_de_DE.js */
	//$scope.texte = providedValue;
		
}]);
