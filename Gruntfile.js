// Gruntfile.js

// our wrapper function (required by grunt and its plugins)
// all configuration goes inside this function
module.exports = function(grunt) {
  
	// URI paths for our tasks to use.
    grunt_uriSrc =  'src/main/webapp/resources/';
    grunt_uriDist = grunt_uriSrc+'/dist/';
	
    // ===========================================================================
    // CONFIGURE GRUNT ===========================================================
    // ===========================================================================
    grunt.initConfig({

    // get the configuration info from package.json ----------------------------
    // this way we can use things like name and version (pkg.name)
    pkg: grunt.file.readJSON('package.json'),

    // all of our configuration will go here
    // configure jshint to validate js files -----------------------------------
    sass: {                              // Task
	    dist: {                            // Target
	      options: {                       // Target options
	        style: 'expanded'
	      },
	      files: {                         // Dictionary of files
	    	  'src/main/webapp/resources/sass_compiled/login.css': 'src/main/webapp/resources/sass/login.scss',       // 'destination': 'source'
	    	  'src/main/webapp/resources/sass_compiled/main.css': 'src/main/webapp/resources/sass/main.scss',
	    	  'src/main/webapp/resources/sass_compiled/vendors.css': 'src/main/webapp/resources/sass/vendors.scss',
			  'src/main/webapp/resources/sass_compiled/theme2.css': 'src/main/webapp/resources/sass/themes/theme2.scss',
			  'src/main/webapp/resources/sass_compiled/theme1.css': 'src/main/webapp/resources/sass/themes/theme1.scss',
			  'src/main/webapp/resources/sass_compiled/theme3.css': 'src/main/webapp/resources/sass/themes/theme3.scss',
			  'src/main/webapp/resources/sass_compiled/theme4.css': 'src/main/webapp/resources/sass/themes/theme4.scss',
			  'src/main/webapp/resources/sass_compiled/responsive.css': 'src/main/webapp/resources/sass/responsive/responsive.scss'
	      }
	    }
	},
    cssmin: {
        options: {
          banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n'
        },
        dev: {
        	files: {
        		'src/main/webapp/resources/css/login.min.css': ['src/main/webapp/resources/sass_compiled/login.css']
        	}
        }
    },
    concat: {
    	dev: {
        	files: {
        		'src/main/webapp/resources/js/dev/login.js': [grunt_uriSrc+'/js/lib/jquery.js', grunt_uriSrc+'js/lib/bootstrap.js', grunt_uriSrc+'js/login/login.js'],
        		'src/main/webapp/resources/js/dev/lib.js': [grunt_uriSrc+'/js/lib/jquery.js',grunt_uriSrc+'/js/lib/jquery-ui.js',grunt_uriSrc+'/js/lib/angular.js',
        		                                            grunt_uriSrc+'/js/lib/bootstrap.js',grunt_uriSrc+'/js/lib/angular-route.js',grunt_uriSrc+'/js/lib/angular-resource.js',
        		                                            grunt_uriSrc+'/js/lib/jquery.dataTables.js',grunt_uriSrc+'/js/lib/angular-datatables.js',grunt_uriSrc+'/js/lib/angular-datatables.util.js',
        		                                            grunt_uriSrc+'/js/lib/angular-datatables.options.js',grunt_uriSrc+'/js/lib/angular-datatables.instances.js',grunt_uriSrc+'/js/lib/angular-datatables.factory.js',
        		                                            grunt_uriSrc+'/js/lib/angular-datatables.renderer.js',grunt_uriSrc+'/js/lib/angular-datatables.directive.js',grunt_uriSrc+'/js/lib/jquery.mmenu.all.min.js',
        		                                            grunt_uriSrc+'/js/lib/moment.min.js',grunt_uriSrc+'/js/lib/angular-bootstrap.js',grunt_uriSrc+'/js/lib/angular-animate.js',
        		                                            grunt_uriSrc+'/js/lib/angular-messages.js',grunt_uriSrc+'/js/lib/angular-datatables.bootstrap.options.js',grunt_uriSrc+'/js/lib/angular-datatables.bootstrap.tabletools.js',
        		                                            grunt_uriSrc+'/js/lib/angular-datatables.bootstrap.colvis.js',grunt_uriSrc+'/js/lib/datatables.columnfilter.js',grunt_uriSrc+'/js/lib/angular-datatables.columnfilter.js',
        		                                            grunt_uriSrc+'/js/lib/angular-datatables.responsive.js',grunt_uriSrc+'/js/lib/angular-datatables.buttons.js',grunt_uriSrc+'/js/lib/datatables.buttons.js',
        		                                            grunt_uriSrc+'/js/lib/dataTables.tableTools.js',grunt_uriSrc+'/js/lib/angular-datatables.buttons.html5.js',grunt_uriSrc+'/js/lib/angular-datatables.buttons.print.js',
        		                                            grunt_uriSrc+'/js/lib/angular-datatables.buttons.colVis.js',grunt_uriSrc+'/js/lib/angular-datatables.buttons.flash.js',grunt_uriSrc+'/js/lib/bootstrap-calendar.min.js',
        		                                            grunt_uriSrc+'/js/lib/angular-bootstrap-calendar-tpls.js',grunt_uriSrc+'/js/lib/angular-sanitize.js',grunt_uriSrc+'/js/lib/angularjs-dropdown-multiselect.js',
        		                                            grunt_uriSrc+'/js/lib/ui-bootstrap-tpls-1.3.2.js',grunt_uriSrc+'/js/lib/select.js',grunt_uriSrc+'/js/lib/jquery-confirm.js'],
        		'src/main/webapp/resources/css/vendors.css' : grunt_uriSrc+'sass_compiled/vendors.css',
        		'src/main/webapp/resources/css/perf.css' : grunt_uriSrc+'sass_compiled/main.css',
				'src/main/webapp/resources/css/theme2.css' : grunt_uriSrc+'sass_compiled/theme2.css',
				'src/main/webapp/resources/css/theme1.css' : grunt_uriSrc+'sass_compiled/theme1.css',
				'src/main/webapp/resources/css/theme3.css' : grunt_uriSrc+'sass_compiled/theme3.css',
				'src/main/webapp/resources/css/theme4.css' : grunt_uriSrc+'sass_compiled/theme4.css',
				'src/main/webapp/resources/css/responsive.css' : grunt_uriSrc+'sass_compiled/responsive.css'
        	}
        }
    },
    uglify: {
        options: {
        	banner: '/*\n <%= pkg.name %> <%= grunt.template.today("yyyy-mm-dd") %> \n*/\n'
        }, 
        dev: { 
        	files: { 
        		'src/main/webapp/resources/js/dev/login.min.js': [grunt_uriSrc+'/js/lib/jquery.js', grunt_uriSrc+'js/lib/bootstrap.js', grunt_uriSrc+'js/login/login.js'],
        		'src/main/webapp/resources/js/dev/lib.min.js' : [grunt_uriSrc+'/js/dev/lib.js']
        	}
        }, 
        production: {
        }
     }
  });


  // ============= // CREATE TASKS ========== //
  grunt.registerTask('default', ['sass', 'cssmin', 'concat']);
  
  //this task will only run the dev configuration 
  grunt.registerTask('dev', ['sass', 'cssmin', 'concat:dev', 'uglify:dev']);

  
  // ===========================================================================
  // LOAD GRUNT PLUGINS ========================================================
  // ===========================================================================
  // we can only load these if they are in our package.json
  // make sure you have run npm install so our app can find these
  grunt.loadNpmTasks('grunt-contrib-uglify');
  grunt.loadNpmTasks('grunt-contrib-cssmin');
  grunt.loadNpmTasks('grunt-contrib-sass');
  grunt.loadNpmTasks('grunt-contrib-concat');

};
