var mainApp = angular.module("perficientHr", 
		['profile.services','employee.services','dashboard.services',
		 'designation.services','project.services',
		 'ngRoute', 'datatables', 'datatables.bootstrap']);

var rand = Math.floor(Math.random()*(3-1+1)+1);

mainApp.config(function($routeProvider) {
	$routeProvider
	    .when('/home', {
	        templateUrl: 'html/dashboard'+rand+'.html',
	        controller: 'dashboardController'
	    })
	    .when('/employees', {
	        templateUrl: 'html/employees.html',
	        controller: 'employeeController'
	    })
	    .when('/profile', {
	        templateUrl: 'html/profile.html',
	        controller: 'profileController'
	    })
	    .when('/pto', {
	        templateUrl: 'html/pto.html',
	        controller: 'ptoController'
	    })
	    .when('/candidate', {
	        templateUrl: 'html/candidate.html'
	    })
	    .when('/designations', {
	        templateUrl: 'html/designations.html',
	        controller: 'designationController'
	    })
	    .when('/projects', {
	        templateUrl: 'html/projects.html',
	        controller: 'projectController'
	    })
	    .when('/importpto', {
	        templateUrl: 'html/importpto.html'
	    })
	    .otherwise({
	        redirectTo: '/home'
	    });
});

var $menu = $('#menu');
var $btnMenu = $('.btn-menu');
var $img = $('img'); 

// mmenu customization
$menu.mmenu({
  navbars: [{
	position: "top",
	content: [ "searchfield", "breadcrumbs" ],
	height: 2
  }],
  extensions: ['widescreen', 'theme-dark', 'effect-menu-slide'],
  offCanvas: {
    position  : "left",
    zposition : "back"
  },
  searchfield: true
}).on('click', 'a[href^="#/"]', function() {
	console.log('link: ', $(this));
	window.location.href=$(this).attr('href');
	return false;
});

// toggle menu
var api = $menu.data("mmenu");

$('#sidePanel').on('click', function(e) {
	e.preventDefault();
	if ( $(this).hasClass('mm-opened' )) {
		api.close();
	} else {
		api.open();
	}
});

// change toggle behavior for subpanels
$menu.find( ".mm-next" ).addClass("mm-fullsubopen");