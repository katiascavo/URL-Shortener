angular.module('URLShortener')
 .controller('AnalyticsController', ['$scope', '$http', '$window', function ($scope, $http, $window) {

$scope.graphf = function(){
return "http://localhost:8080/getGraph";
}

$scope.prova= function() {
 $http
                                                .get($scope.graphf())
                                                .success(
                                                        function(risposta) {
                                                            $scope.nom = risposta.responseData;
                                                            if ($scope.nom.result == "okay") {
                                                            $scope.graph = $scope.nom.graph;
 if($scope.graph.length == 28){

            var days =  [{ x: String($scope.graph[0].data), value: $scope.graph[0].numeroclick},
            {x: String($scope.graph[1].data), value: $scope.graph[1].numeroclick},
            {x: String($scope.graph[2].data),value: $scope.graph[2].numeroclick},
            {x: String($scope.graph[3].data),value: $scope.graph[3].numeroclick},
            {x: String($scope.graph[4].data),value: $scope.graph[4].numeroclick},
            {x: String($scope.graph[5].data),value: $scope.graph[5].numeroclick},
            {x: String($scope.graph[6].data),value: $scope.graph[6].numeroclick},
            {x: String($scope.graph[7].data),value: $scope.graph[7].numeroclick},
            {x: String($scope.graph[8].data),value: $scope.graph[8].numeroclick},
            {x: String($scope.graph[9].data),value: $scope.graph[9].numeroclick},
            {x: String($scope.graph[10].data),value: $scope.graph[10].numeroclick},
            {x: String($scope.graph[11].data),value: $scope.graph[11].numeroclick},
            {x: String($scope.graph[12].data),value: $scope.graph[12].numeroclick},
            {x: String($scope.graph[13].data),value: $scope.graph[13].numeroclick},
            {x: String($scope.graph[14].data),value: $scope.graph[14].numeroclick},
            {x: String($scope.graph[15].data),value: $scope.graph[15].numeroclick},
            {x: String($scope.graph[16].data),value: $scope.graph[16].numeroclick},
            {x: String($scope.graph[17].data),value: $scope.graph[17].numeroclick},
            {x: String($scope.graph[18].data),value: $scope.graph[18].numeroclick},
            {x: String($scope.graph[19].data),value: $scope.graph[19].numeroclick},
            {x: String($scope.graph[20].data),value: $scope.graph[20].numeroclick},
            {x: String($scope.graph[21].data),value: $scope.graph[21].numeroclick},
            {x: String($scope.graph[22].data),value: $scope.graph[22].numeroclick},
            {x: String($scope.graph[23].data),value: $scope.graph[23].numeroclick},
            {x: String($scope.graph[24].data),value: $scope.graph[24].numeroclick},
            {x: String($scope.graph[25].data),value: $scope.graph[25].numeroclick},
            {x: String($scope.graph[26].data),value: $scope.graph[26].numeroclick},
            {x: String($scope.graph[27].data),value: $scope.graph[27].numeroclick}
              ];

            Morris.Area({
            element: 'morris-area-chart',
            data: days,
            xkey: 'x',
            ykeys: ['value'],
            labels: ['Number of clicks'],
            pointSize: 2,
            hideHover: 'auto',
            resize: true,
            integerYLabels: true,
            parseTime: false
        });

 }else if($scope.graph.length == 29){

            var days =  [{ x: String($scope.graph[0].data), value: $scope.graph[0].numeroclick},
            {x: String($scope.graph[1].data), value: $scope.graph[1].numeroclick},
            {x: String($scope.graph[2].data),value: $scope.graph[2].numeroclick},
            {x: String($scope.graph[3].data),value: $scope.graph[3].numeroclick},
            {x: String($scope.graph[4].data),value: $scope.graph[4].numeroclick},
            {x: String($scope.graph[5].data),value: $scope.graph[5].numeroclick},
            {x: String($scope.graph[6].data),value: $scope.graph[6].numeroclick},
            {x: String($scope.graph[7].data),value: $scope.graph[7].numeroclick},
            {x: String($scope.graph[8].data),value: $scope.graph[8].numeroclick},
            {x: String($scope.graph[9].data),value: $scope.graph[9].numeroclick},
            {x: String($scope.graph[10].data),value: $scope.graph[10].numeroclick},
            {x: String($scope.graph[11].data),value: $scope.graph[11].numeroclick},
            {x: String($scope.graph[12].data),value: $scope.graph[12].numeroclick},
            {x: String($scope.graph[13].data),value: $scope.graph[13].numeroclick},
            {x: String($scope.graph[14].data),value: $scope.graph[14].numeroclick},
            {x: String($scope.graph[15].data),value: $scope.graph[15].numeroclick},
            {x: String($scope.graph[16].data),value: $scope.graph[16].numeroclick},
            {x: String($scope.graph[17].data),value: $scope.graph[17].numeroclick},
            {x: String($scope.graph[18].data),value: $scope.graph[18].numeroclick},
            {x: String($scope.graph[19].data),value: $scope.graph[19].numeroclick},
            {x: String($scope.graph[20].data),value: $scope.graph[20].numeroclick},
            {x: String($scope.graph[21].data),value: $scope.graph[21].numeroclick},
            {x: String($scope.graph[22].data),value: $scope.graph[22].numeroclick},
            {x: String($scope.graph[23].data),value: $scope.graph[23].numeroclick},
            {x: String($scope.graph[24].data),value: $scope.graph[24].numeroclick},
            {x: String($scope.graph[25].data),value: $scope.graph[25].numeroclick},
            {x: String($scope.graph[26].data),value: $scope.graph[26].numeroclick},
            {x: String($scope.graph[27].data),value: $scope.graph[27].numeroclick},
            {x: String($scope.graph[28].data),value: $scope.graph[28].numeroclick}
              ];

            Morris.Area({
            element: 'morris-area-chart',
            data: days,
            xkey: 'x',
            ykeys: ['value'],
            labels: ['Number of clicks'],
            pointSize: 2,
            hideHover: 'auto',
            resize: true,
            integerYLabels: true,
            parseTime: false
        });
 }else if($scope.graph.length == 30){

             var days =  [{ x: String($scope.graph[0].data), value: $scope.graph[0].numeroclick},
             {x: String($scope.graph[1].data), value: $scope.graph[1].numeroclick},
             {x: String($scope.graph[2].data),value: $scope.graph[2].numeroclick},
             {x: String($scope.graph[3].data),value: $scope.graph[3].numeroclick},
             {x: String($scope.graph[4].data),value: $scope.graph[4].numeroclick},
             {x: String($scope.graph[5].data),value: $scope.graph[5].numeroclick},
             {x: String($scope.graph[6].data),value: $scope.graph[6].numeroclick},
             {x: String($scope.graph[7].data),value: $scope.graph[7].numeroclick},
             {x: String($scope.graph[8].data),value: $scope.graph[8].numeroclick},
             {x: String($scope.graph[9].data),value: $scope.graph[9].numeroclick},
             {x: String($scope.graph[10].data),value: $scope.graph[10].numeroclick},
             {x: String($scope.graph[11].data),value: $scope.graph[11].numeroclick},
             {x: String($scope.graph[12].data),value: $scope.graph[12].numeroclick},
             {x: String($scope.graph[13].data),value: $scope.graph[13].numeroclick},
             {x: String($scope.graph[14].data),value: $scope.graph[14].numeroclick},
             {x: String($scope.graph[15].data),value: $scope.graph[15].numeroclick},
             {x: String($scope.graph[16].data),value: $scope.graph[16].numeroclick},
             {x: String($scope.graph[17].data),value: $scope.graph[17].numeroclick},
             {x: String($scope.graph[18].data),value: $scope.graph[18].numeroclick},
             {x: String($scope.graph[19].data),value: $scope.graph[19].numeroclick},
             {x: String($scope.graph[20].data),value: $scope.graph[20].numeroclick},
             {x: String($scope.graph[21].data),value: $scope.graph[21].numeroclick},
             {x: String($scope.graph[22].data),value: $scope.graph[22].numeroclick},
             {x: String($scope.graph[23].data),value: $scope.graph[23].numeroclick},
             {x: String($scope.graph[24].data),value: $scope.graph[24].numeroclick},
             {x: String($scope.graph[25].data),value: $scope.graph[25].numeroclick},
             {x: String($scope.graph[26].data),value: $scope.graph[26].numeroclick},
             {x: String($scope.graph[27].data),value: $scope.graph[27].numeroclick},
             {x: String($scope.graph[28].data),value: $scope.graph[28].numeroclick},
             {x: String($scope.graph[29].data),value: $scope.graph[29].numeroclick}
               ];

             Morris.Area({
             element: 'morris-area-chart',
             data: days,
             xkey: 'x',
             ykeys: ['value'],
             labels: ['Number of clicks'],
             pointSize: 2,
             hideHover: 'auto',
             resize: true,
             integerYLabels: true,
             parseTime: false
         });
  }else if($scope.graph.length == 31){

             var days =  [{ x: String($scope.graph[0].data), value: $scope.graph[0].numeroclick},
             {x: String($scope.graph[1].data), value: $scope.graph[1].numeroclick},
             {x: String($scope.graph[2].data),value: $scope.graph[2].numeroclick},
             {x: String($scope.graph[3].data),value: $scope.graph[3].numeroclick},
             {x: String($scope.graph[4].data),value: $scope.graph[4].numeroclick},
             {x: String($scope.graph[5].data),value: $scope.graph[5].numeroclick},
             {x: String($scope.graph[6].data),value: $scope.graph[6].numeroclick},
             {x: String($scope.graph[7].data),value: $scope.graph[7].numeroclick},
             {x: String($scope.graph[8].data),value: $scope.graph[8].numeroclick},
             {x: String($scope.graph[9].data),value: $scope.graph[9].numeroclick},
             {x: String($scope.graph[10].data),value: $scope.graph[10].numeroclick},
             {x: String($scope.graph[11].data),value: $scope.graph[11].numeroclick},
             {x: String($scope.graph[12].data),value: $scope.graph[12].numeroclick},
             {x: String($scope.graph[13].data),value: $scope.graph[13].numeroclick},
             {x: String($scope.graph[14].data),value: $scope.graph[14].numeroclick},
             {x: String($scope.graph[15].data),value: $scope.graph[15].numeroclick},
             {x: String($scope.graph[16].data),value: $scope.graph[16].numeroclick},
             {x: String($scope.graph[17].data),value: $scope.graph[17].numeroclick},
             {x: String($scope.graph[18].data),value: $scope.graph[18].numeroclick},
             {x: String($scope.graph[19].data),value: $scope.graph[19].numeroclick},
             {x: String($scope.graph[20].data),value: $scope.graph[20].numeroclick},
             {x: String($scope.graph[21].data),value: $scope.graph[21].numeroclick},
             {x: String($scope.graph[22].data),value: $scope.graph[22].numeroclick},
             {x: String($scope.graph[23].data),value: $scope.graph[23].numeroclick},
             {x: String($scope.graph[24].data),value: $scope.graph[24].numeroclick},
             {x: String($scope.graph[25].data),value: $scope.graph[25].numeroclick},
             {x: String($scope.graph[26].data),value: $scope.graph[26].numeroclick},
             {x: String($scope.graph[27].data),value: $scope.graph[27].numeroclick},
             {x: String($scope.graph[28].data),value: $scope.graph[28].numeroclick},
             {x: String($scope.graph[29].data),value: $scope.graph[29].numeroclick},
             {x: String($scope.graph[30].data),value: $scope.graph[30].numeroclick}
               ];

             Morris.Area({
             element: 'morris-area-chart',
             data: days,
             xkey: 'x',
             ykeys: ['value'],
             labels: ['Number of clicks'],
             pointSize: 2,
             hideHover: 'auto',
             resize: true,
             integerYLabels: true,
             parseTime: false
         });
  }



     } });


 };


   }]);
