/* Profile controller */
mainApp.controller('profileController', function($scope, $controller, profileAPIservice, employeeAPIservice) {
     $scope.data = null;

     employeeAPIservice.loadAllEmployees().success(function (response) {
        $scope.employees = response.entity;
        profileAPIservice.getProfileDetails().success(function (profileResponse) {
            $scope.data = profileResponse.entity;
        });
     });

     $scope.submit = function() {
       if ($scope.data) {
          $('.alert-info').removeClass('hidden');
          employeeAPIservice.updateEmployee($scope.data).success(function () {
                 $scope.msg = 'User updated successfully.';
          }).error(function(){
                 $scope.msg = 'An Error Occured. Unable to update User.';
          });
       }
     };
     angular.extend(this, $controller('empProfileController', {
         $scope: $scope
     }));
});

mainApp.controller('empProfileController', function($scope, designationAPIservice) {
    $scope.dob = {
        opened: false
    };

    $scope.dobDate = function() {
        $scope.dob.opened = true;
    };

    $scope.joinDate = {
        opened: false
    };

    $scope.joinDate = function() {
        $scope.joinDate.opened = true;
    };
    $scope.lastWorkDate = {
        opened: false
    };

    $scope.lastWorkDate = function() {
        $scope.lastWorkDate.opened = true;
    };

    designationAPIservice.getDesignationDetails().success(function (response) {
        $scope.designations = response.entity;
    });
});