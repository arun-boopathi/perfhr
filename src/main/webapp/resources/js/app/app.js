var mainApp = angular.module("perficientHr", 
		['profile.services','employee.services','dashboard.services',
		 'designation.services','project.services', 'projectmember.services', 'pto.services', 'leave.services',
		 'reportJobtitle.services', 'notification.services',
		 'ngRoute', 'ngResource', 'ngAnimate',
		 'mwl.calendar', 'ui.bootstrap',
		 'datatables', 'datatables.bootstrap', 'datatables.buttons', 'datatables.columnfilter',
		 'ngSanitize', 'ui.select', 'isteven-multi-select']);

mainApp.value('user', {
    loggedUser:{}
});

/*Set the selected menu/sub-menu open on refresh/reload*/
var pageEle = '#'+window.location.href.split('#')[1];
$("#menu li a[href='"+pageEle+"']").parent().addClass('Selected');

var rand = Math.floor(Math.random()*(3-1+1)+1);

mainApp.config(function($routeProvider) {
  $routeProvider
    .when('/home', {
        templateUrl: 'html/dashboard1.html',
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
        templateUrl: 'html/pto.html'
    })
    .when('/candidate', {
        templateUrl: 'html/candidate.html'
    })
    .when('/interview', {
        templateUrl: 'html/interview.html'
    })
    .when('/designations', {
        templateUrl: 'html/designations.html',
        controller: 'designationController'
    })
    .when('/projects', {
        templateUrl: 'html/projects.html',
        controller: 'projectController'
    })
    .when('/projectmembers', {
        templateUrl: 'html/projectmembers.html',
        controller: 'projectMembersController'
    })
    .when('/importpto', {
        templateUrl: 'html/importpto.html',
        controller: 'importPtoController'
    })
    .when('/wfh', {
        templateUrl: 'html/wfh.html'
    })
    .when('/notifications', {
        templateUrl: 'html/notifications.html'
    })
    .when('/report/jobtitle', {
        templateUrl: 'html/reports_jobtitle.html',
        controller : 'reportsJobtitleController'
    })
    .when('/report/reports_pto', {
        templateUrl: 'html/reports_pto.html'
    })
    .when('/report/reports_wfh', {
        templateUrl: 'html/reports_wfh.html'
    })
    .otherwise({
        redirectTo: '/home'
    });
});

var $menu = $('#menu');
var $btnMenu = $('.btn-menu');
var $img = $('img'); 

/*$("#menu").mmenu({
    setSelected: true
});*/

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
  setSelected: true,
  onClick: {
	  setSelected: true
  },
  searchfield: true
}).on('click', 'a[href^="#/"]', function() {
	window.location.href=$(this).attr('href');
	return false;
});
// toggle menu
var api = $menu.data("mmenu");

$('#sidePanel').on('click', function(e) {
	e.preventDefault();
	if ($(this).hasClass('mm-opened')) {
		api.close();
		$menu.hide();
	} else {
		api.open();
		$menu.show();
	}
});

// change toggle behavior for subpanels
$menu.find(".mm-next").addClass("mm-fullsubopen");

//Dashboard 1 click event on actions
$('#divContainer').on('click', '.feature i',  function(e) {
	window.location.href=$(this).attr('nav');
});