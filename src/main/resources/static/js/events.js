var appEvents = angular.module('appEvents', []);

appEvents.controller('EventsCtrl', function ($scope, $http) {

    $scope.loadEvents = function() {
        $http.get("events")
            .success(function(data){
                $scope.eventsList = data;
            });
    }

    $scope.loadEvents();

});