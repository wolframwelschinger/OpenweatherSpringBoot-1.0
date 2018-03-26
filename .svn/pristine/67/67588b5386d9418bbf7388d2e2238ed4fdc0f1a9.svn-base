'use strict';

var app = angular.module('wetterApp');

/**
 * Query-Class fuer das Paging, das Filtern und die Sortierung.
 * Autor: Michael Engelhardt
 */
app.factory('Query', ['$log', function ($log) {
        
        /**
         * Constructor
         * @param {type} resourceService der Data-Service
         * @returns {undefined} anwendungsfallspezifisches Query-Object
         */
        function QueryInstance(resourceService) {
            var me = this;

            // Defaulteinstellung fuer den Filter (leer)    
            me.filter = {};
            
            // Funktion zum Zuruecksetzen des Filters
            me.clearFilter = function () {
                me.filter = {};
            }

            // Defaulteinstellung fuer das Filtern (nach ID, absteigend)    
            me.sort = {
                orderBy: 'id',
                reverse: true
            }
            
            /**
             * Toogle-Funktion fuer das Sortieren
             * @param {type} field Feld nach dem sortiert werden soll
             * @param {type} ignoreCase true, wenn Gross-/Kleinschreibung unterdrueckt werden soll
             * @returns {undefined} Ohne Rueckgabe
             */
            me.toggleSort = function (field, ignoreCase) {
                var sort = me.sort;
                if (sort.orderBy === field) {
                    sort.reverse = !sort.reverse;
                } else {
                    sort.orderBy = field;
                    sort.reverse = false;
                }
                sort.ignoreCase = !!ignoreCase;

                me.load(field);
            }
            
            /**
             * Tooltip fuer einen Link zurueckgeben
             * @param {type} field Feld
             * @returns {String} Tooltip
             */
            me.getSortLinkTooltip = function (field) {
                return (me.sort.orderBy == field && !me.sort.reverse ? 'absteigend' : 'aufsteigend') + ' sortieren';
            }

            // Default-Einstellung fuer das Paging
            me.paging = {
                currentPage: 1,
                itemsPerPageEdit: 12
            }
            
            me.itemsPerPageEdited = function () {
                me.paging.itemsPerPageEdit && me.paging.itemsPerPageEdit != me.paging.itemsPerPage && me.load('pager');
            }


            var loading = undefined;
            
            /**
             * Gibt an, ob die Seite (noch) geladen wird
             * @param {type} source die Seite
             * @returns {source|Boolean|undefined} true, wenn die Seite (noch) geladen wird
             */
            me.isLoading = function (source) {
                return source ? loading == source : loading;
            }

            /**
             * Laden der Daten ueber den REST-Service
             * @param {type} source Seite bzw, Controller
             * @returns {undefined} ohne Rueckgabe
             */
            me.load = function (source) {

                $log.debug('Query started: ' + source);

                loading = source || true;

                var opts = {};
                if (me.filter) {
                    opts.filter = me.filter;
                }
                if (me.sort) {
                    opts.sort = me.sort;
                }
                if (me.paging) {
                    opts.page = {
                        index: me.paging.currentPage - 1,
                        size: me.paging.itemsPerPageEdit || 10
                    }
                }
                
                $log.debug('Query Parameter: ' + angular.toJson(opts));

                resourceService.query(opts).$promise
                        .then(function (data) {
                            $log.debug('Query succeeded: ' + source);

                            var results = angular.isArray(data) ? data : data.content;
                            if (me.paging) {
                                me.paging.currentPage = data.number + 1;
                                me.paging.itemsPerPage = data.size;
                                me.paging.itemsPerPageEdit = data.size;
                                me.paging.totalItems = data.totalElements;
                                me.paging.fromItem = (data.number * data.size) + 1;
                                me.paging.toItem = (data.number * data.size) + data.numberOfElements;
                            }

                            if (angular.isFunction(me.onLoadSuccess)) {
                                me.onLoadSuccess(results);
                            }
                        })
                        .catch(function (err) {
                            $log.error('Query failed: ' + angular.toJson(err.data || err))

                            if (angular.isFunction(me.onLoadFailure)) {
                                me.onLoadFailure(err);
                            }
                        })
                        .finally(function () {
                            loading = undefined;

                            if (angular.isFunction(me.onLoad)) {
                                me.onLoad();
                            }
                        });
            }
            me.onLoad = undefined;
            me.onLoadSuccess = undefined;
            me.onLoadFailure = undefined;
        }
        
        // Gibt das anwendungsfallspezifische Query-Objekt zurueck
        return {
            createNew: function (resourceService) {
                return new QueryInstance(resourceService)
            }
        };
        
    }]);
