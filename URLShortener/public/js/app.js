// Declare app level module which depends on filters, and services
angular.module('URLShortener', ['ngResource', 'ngRoute', 'ui.bootstrap', 'ui.date'])
  .config(['$routeProvider', function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/home/home.html',
        controller: 'HomeController'})
      .when('/analytics', {
        templateUrl: 'views/home/analytics.html',
        controller: 'AnalyticsController'
      })
      .otherwise({redirectTo: '/'});
  }]);
