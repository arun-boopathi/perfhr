/* Profile controller */
mainApp.controller('profileController', function($scope, profileAPIservice, employeeAPIservice) {
      $scope.finishLoading = function() {
       	  $(".alert-info").addClass('hidden');
       	  $('#closeBtn').hide();
      };
      $scope.data = null;
     /* profileAPIservice.getProfileDetails().success(function (response) {
          $scope.data = response; 
      });*/
      
      $scope.submit = function() {
    	  console.log('data: ', $scope.data);
    	  /*if ($scope.data) {
    	      $('.alert-info').removeClass('hidden');
    	      employeeAPIservice.updateEmployee($scope.data).success(function (response) {
    	      	   $scope.msg = 'User updated successfully.';         	
    	      }).error(function(error){
    	      	   $scope.msg = 'An Error Occured. Unable to update User.';
    	      });
          }*/  
      };
});