<html ng-app>
<head>
    <title>Hello World, AngularJS </title>
<!--    <script type="text/javascript"
        src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>-->
   <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.0.8/angular-resource.min.js"></script>
 <script type="text/javascript">
   
function CreateCtrl($scope,$http) {
              $scope.save = function() {
	
//        10.132.160.215
            $http.post("/trac/project",$scope.json)
                .success(function(data,status,headers,config){
                    alert(data.response.code);
              }).error(function(data,status,headers,config){
			alert("Error"+data);
              });
  
  }
}
        
 

   </script>
</head>
<body>
     
    <div ng-controller="CreateCtrl">
     
      <form ng-submit="save()"> 
        
        JSON <textarea ng-model="json" rows="20" cols="30"></textarea><BR/>
        <input class="btn-primary" type="submit" value="add">
      </form>
    </div>
  
  
  
     
</body>
</html>