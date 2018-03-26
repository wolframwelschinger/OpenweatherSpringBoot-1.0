/**
 * Code aus https://github.com/sachinchoolur/angular-flash übernommen.
 * 
 * Anpassungen:
 * let durch var ersetzt
 * Setzen von autoDismiss ergänzt
 * Type 'error' wird wie 'danger' behandelt
 * err-Objekt wird für die Ausgabe ausgewertet
 */

const appFlash = angular.module('ngFlash', []);

console.log('angular-flash.js');

appFlash.run([
    '$rootScope', function($rootScope) {
        return $rootScope.flashes = [];
    }
]);

appFlash.directive('dynamic', [
    '$compile', function($compile) {
        return {
            restrict: 'A',
            replace: true,
            link: function(scope, ele, attrs) {
                return scope.$watch(attrs.dynamic, function(html) {
                    ele.html(html);
                    return $compile(ele.contents())(scope);
                });
            }
        };
    }
]);

appFlash.directive('applytransclude', [
    '$compile', function($compile) {
        return {
            restrict: 'A',
            link: function(scope, ele, attrs) {
                scope._transclude(scope, function(clone, scope) {
                    ele.empty().append(clone);
                });
            }
        };
    }
]);

appFlash.directive('closeFlash', [
    '$compile', '$rootScope', 'Flash', function($compile, $rootScope, Flash) {
        return {
            link: function(scope, ele, attrs) {
                return ele.on('click', function() {
//                    let id = parseInt(attrs.closeFlash, 10);
                    var id = parseInt(attrs.closeFlash, 10);
                    Flash.dismiss(id);
                    $rootScope.$apply();
                });
            }
        };
    }
]);

appFlash.directive('flashMessage', [
    'Flash', function(Flash) {
        return {
            restrict: 'E',
            scope: {
                duration: '=',
                showClose: '=',
                autoDismiss: '=',
                onDismiss: '&',
                name: '@'
            },
            link: function(scope, ele, attrs, ctrl, transclude) {
                Flash.setTimeout(scope.duration);
                Flash.setShowClose(scope.showClose);
                Flash.setAutoDismiss(scope.autoDismiss);
                function onDismiss(flash) {
                    if (typeof scope.onDismiss !== 'function') return;
                    scope.onDismiss({flash: flash});
                }

                Flash.setOnDismiss(onDismiss);

                if (Flash.config.templateTransclude) {
                    scope._transclude = transclude;
                }
            },
            transclude: Flash.config.templateTransclude,
            template: `
                <div ng-repeat="flash in $root.flashes track by $index" ng-if="flash.config.container === name" class="alert-container">
                    ` + Flash.config.template + `
                </div>
            `
        };
    }
]);
    
appFlash.provider('Flash', function() {
//    let defaultConfig = {};
//    let templatePresets = {
    var defaultConfig = {};
    var templatePresets = {
        bootstrap: {
            html: `
                <div role="alert" id="{{flash.config.id}}" class="alert {{flash.config.class}} alert-{{flash.type}} alert-dismissible alertIn alertOut">
                    <div type="button" class="close" ng-show="flash.showClose" close-flash="{{flash.id}}">
                        <span aria-hidden="true">&times;</span>
                        <span class="sr-only">Close</span>
                    </div>
                    <span dynamic="flash.text"></span>
                </div>
            `,
            transclude: false
        },
        transclude: {
            html: `<div applytransclude></div>`,
            transclude: true
        }
    };

    this.setTimeout = function(timeout) {
        if (typeof timeout !== 'number') return;
        defaultConfig.timeout = timeout;
    };
    this.setShowClose = function(value) {
        if (typeof value !== 'boolean') return;
        defaultConfig.showClose = value;
    };
    this.setTemplate = function(template) {
        if (typeof template !== 'string') return;
        defaultConfig.template = template;
    };
    this.setTemplatePreset = function(preset) {
        if (typeof preset !== 'string'
            || !(preset in templatePresets)) return;

//        let template = templatePresets[preset];
        var template = templatePresets[preset];
        this.setTemplate(template.html);
        defaultConfig.templateTransclude = template.transclude;
    };
    this.setOnDismiss = function(callback) {
        if (typeof callback !== 'function') return;
        defaultConfig.onDismiss = callback;
    };

    this.setAutoDismiss = function (dismiss) {
        if (typeof dismiss !== 'boolean') return;
        defaultConfig.autoDismiss = dismiss;
    };

    this.setTimeout(5000);
    this.setShowClose(true);
    this.setTemplatePreset('bootstrap');
    this.setAutoDismiss(true);

    this.$get = ['$rootScope', '$interval', function($rootScope, $interval) {
        const dataFactory = {};
//        let counter = 0;
        var counter = 0;

        dataFactory.setTimeout = this.setTimeout;
        dataFactory.setShowClose = this.setShowClose;
        dataFactory.setOnDismiss = this.setOnDismiss;
        dataFactory.setAutoDismiss = this.setAutoDismiss;
        dataFactory.config = defaultConfig;

        dataFactory.create = function(type, text, timeout, config, showClose) {
            if (type == 'error') {
                type = 'danger';
            }

            var message = text;
            if (typeof (text['data']) !== 'undefined' && text['data'] !== null) {
                // Ausgabe von Exceptions
                message = '' + text.data.status + ' ' + text.data.error;
                if (typeof (text.data['message'] === 'string')) {
                    message = message + '. ' + text.data.message;
                }
            }

            
            if ($rootScope.flashes.length === 1 && defaultConfig.autoDismiss) {
                dataFactory.dismiss($rootScope.flashes[0].id);
            }
            if (!text) return false;
//            let $this, flash;
            var $this, flash;
            $this = this;
            flash = {
                type: type,
//                text: text,
                text: message,
                config: config,
                id: counter++
            };
            flash.showClose =
                typeof showClose !== 'undefined' ?
                    showClose : defaultConfig.showClose;
            if (defaultConfig.timeout && typeof timeout === 'undefined') {
                flash.timeout = defaultConfig.timeout;
            }
            else if (timeout) {
                flash.timeout = timeout;
            }
            $rootScope.flashes.push(flash);
            if (flash.timeout) {
                flash.timeoutObj = $interval(function() {
                    $this.dismiss(flash.id);
                }, flash.timeout, 1);
            }
            return flash.id;
        };
        dataFactory.pause = function(index) {
            if ($rootScope.flashes[index].timeoutObj) {
                $interval.cancel($rootScope.flashes[index].timeoutObj);
            }
        };
        dataFactory.dismiss = function(id) {
            const index = findIndexById(id);
            if (index !== -1) {
                const flash = $rootScope.flashes[index];
                dataFactory.pause(index);
                $rootScope.flashes.splice(index, 1);
                if (typeof defaultConfig.onDismiss === 'function') {
                    defaultConfig.onDismiss(flash);
                }
            }
        };
        dataFactory.clear = function() {
            while ($rootScope.flashes.length > 0) {
                dataFactory.dismiss($rootScope.flashes[0].id);
            }
        };
        dataFactory.reset = dataFactory.clear;
        function findIndexById(id) {
            return $rootScope.flashes.map((flash) => flash.id).indexOf(id);
        }

        return dataFactory;
    }];
});


