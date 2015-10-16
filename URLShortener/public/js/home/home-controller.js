    angular.module('URLShortener')
      .controller('HomeController', ['$scope', '$http', '$window', function ($scope, $http, $window) {

       $scope.resetUrl = function() {
                                        $scope.longURL = "";
                                        $scope.shortURL = "";
                                    };

                                    $scope.resetAlarm = function(){
                                        $scope.error = "";
                                        $scope.error2 = "";
                                        $scope.error3 = "";
                                    };

                                    $scope.generate = function() {
                                        return "http://localhost:8080/convertToShortUrl?longUrl="
                                                + $scope.longURL;
                                    }

                                    $scope.genShort = function() {

                                        $scope.risp="";
                                        $scope.shortU = "";
                                        $scope.link ="";
                                           if($scope.longURL==null){
                                           $scope.risp = "Empty long URL";
                                           }else{
                                        if ( $scope.longURL.substring(0, 4)
                                                .localeCompare("www.") == '0'
                                                || $scope.longURL.substring(0, 7)
                                                        .localeCompare("http://") == '0'
                                                || $scope.longURL.substring(0, 8)
                                                        .localeCompare("https://") == '0'
                                                && $scope.longURL.indexOf(" ") == '-1') {
                                            $http
                                                    .get($scope.generate())
                                                    .success(
                                                            function(risposta) {
                                                                $scope.ss = risposta.responseData;
                                                                if ($scope.ss.result == "okay") {
                                                                    $scope.nuovo = $scope.ss.shortUrl;
                                                                    $scope.shortU = "Your short URL:  ";
                                                                    $scope.link = $scope.nuovo;

                                                                }else{
                                                                    $scope.risp = $scope.ss.result;
                                                                }
                                                            });

                                        } else {

                                            $scope.risp = "Invalid long URL";
                                        }
                                       }
                                        $scope.resetUrl();
                                    }

                                    $scope.sa = function() {
                                                                        return "http://localhost:8080/saveShort?shortUrl="
                                                                                + $scope.shortURL
                                                                                + "&longUrl="
                                                                                + $scope.longURL;
                                                                    }

                                                                    $scope.save = function() {
                                                                        $scope.ris="";
                                                                        $scope.detail = "";
                                                                        $scope.linkCustom ="";
                                                                        if($scope.longURL==null && $scope.shortURL==null){
                                                                         $scope.ris="Empty long URL and short URL";
                                                                        }else{
                                                                        if($scope.longURL==null){
                                                                            $scope.ris="Empty long URL";
                                                                        }else{
                                                                        if($scope.shortURL==null || $scope.shortURL.localeCompare(" ")=='0'){
                                                                            $scope.ris="Empty short URL";
                                                                        }else{
                                                                        if ($scope.longURL.substring(0, 4)
                                                                                .localeCompare("www.") == '0'
                                                                                || $scope.longURL.substring(0, 7)
                                                                                        .localeCompare("http://") == '0'
                                                                                || $scope.longURL.substring(0, 8)
                                                                                        .localeCompare("https://") == '0' ) {
                                                                            $http
                                                                                .get($scope.sa())
                                                                                .success(
                                                                                        function(response) {
                                                                                            $scope.res = response.responseData;
                                                                                            if ($scope.res.result != "okay") {
                                                                                                $scope.ris = $scope.res.result;
                                                                                            } else {
                                                                                                $scope.detail = "Your custom short URL: ";
                                                                                                $scope.linkCustom = $scope.shortURL;

                                                                                            }
                                                                                        });

                                                                    }else{
                                                                        $scope.ris="Invalid long URL";
                                                                    }

                                                                    }
                                                                    }
                                                                    }
                                                                }

                                                                    $scope.go = function() {
                                                                        return "http://localhost:8080/viewWindow?shortUrl="
                                                                                + $scope.getUrl;
                                                                    }

                                                                            <!-- funzione chiamata generate short URL -->
                                                                    $scope.goLong = function() {
                                                                        $scope.getUrl = $scope.link;
                                                                        $scope.name="";
                                                                        $scope.resetAlarm();
                                                                        $http
                                                                                .get($scope.go())
                                                                                .success(
                                                                                        function(response) {
                                                                                            $scope.aggiungi = response.responseData;
                                                                                            if ($scope.aggiungi.result == "okay") {
                                                                                                $window
                                                                                                        .open($scope.aggiungi.longUrl);
                                                                                            }
                                                                                        });
                                                                        $scope.resetUrl();
                                                                }


                                                                    $scope.goCustomLong = function() {
                                                                        $scope.getUrl=$scope.linkCustom;
                                                                        $scope.resetAlarm();
                                                                        $http
                                                                                .get($scope.go())
                                                                                .success(
                                                                                        function(response) {
                                                                                            $scope.aggiungi = response.responseData;
                                                                                            if ($scope.aggiungi.result == "okay") {
                                                                                                $window
                                                                                                        .open($scope.aggiungi.longUrl);
                                                                                            }
                                                                                        });
                                                                        $scope.resetUrl();
                                                                }

                                                                 <!-- funzione chiamata da view page -->
                                                                                                    $scope.goweb = function() {
                                                                                                        $scope.name="";
                                                                                                        $scope.resetAlarm();

                                                                                                        if ($scope.shortUrl == null || $scope.shortUrl.localeCompare(" ")!= '0')
                                                                                                        {
                                                                                                            $scope.getUrl= $scope.shortUrl;
                                                                                                        $http
                                                                                                                .get($scope.go())
                                                                                                                .success(
                                                                                                                        function(response) {
                                                                                                                            $scope.aggiungi = response.responseData;
                                                                                                                            if ($scope.aggiungi.result == "okay") {
                                                                                                                                $window
                                                                                                                                        .open($scope.aggiungi.longUrl);
                                                                                                                                $scope.name = "Redirect to "
                                                                                                                                        + $scope.aggiungi.longUrl
                                                                                                                            } else {
                                                                                                                                $scope.error2 = $scope.aggiungi.result;
                                                                                                                            }
                                                                                                                        });
                                                                                                    }
                                                                                                    else{
                                                                                                        $scope.name="Empty short URL";
                                                                                                    }
                                                                                                }

                                                                                                $scope.vGraph = function() {
                                                                                                                                    return "http://localhost:8080/getGraphPage?shortUrl="
                                                                                                                                            + $scope.shortUrl;
                                                                                                                                }

    $scope.viewPageGraph = function() {
     if($scope.shortUrl==null){
                                            $scope.error3="Empty short URL";
                                        }else{

                                        $scope.resetAlarm();
                                            $http
                                                .get($scope.vGraph())
                                                .success(
                                                        function(risposta) {
                                                            $scope.nom = risposta.responseData;
                                                            if ($scope.nom.result == "okay") {

                                                            $window.open("/#/analytics");

                                                                }else{
                                                                $scope.error3 = $scope.nom.result;
                                                                }

                                                                });
                                                                }
                                                                }

$scope.vStats = function() {
                                            return "http://localhost:8080/getStats?shortUrl="
                                                    + $scope.shortUrl;
                                        }

                                        $scope.viewStats = function() {
                                               if($scope.shortUrl==null){
                                                   $scope.ristat="Empty short URL";
                                               }else{
                                            $scope.nome = "";
                                            $scope.request = "";
                                            $scope.graph = "";
                                            $scope.resetAlarm();
                                                   $http
                                                    .get($scope.vStats())
                                                    .success(
                                                            function(risposta) {
                                                                $scope.nom = risposta.responseData;
                                                                if ($scope.nom.result == "okay") {
                                                                    $scope.nome = $scope.nom.stats;
                                                                    $scope.request = $scope.nom.ipAddress;
                                                             } else {
                                                                    $scope.error = $scope.nom.result;
                                                                                                                    }
                                                                                                                });
                                                                                                 }

                                                                                            }

      }]);
