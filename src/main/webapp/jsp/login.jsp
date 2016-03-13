<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Perficient HR</title>
    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <link href="css/common.css" rel="stylesheet">
    <link href="css/login.css" rel="stylesheet">
</head>

<body>
    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" target="_blank" href="http://www.perficient.com/">Perficient</a>
            </div>
            <!-- Collect the nav links, forms, and other content for toggling -->
            <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                <ul class="nav navbar-nav">
                    <li>
                        <a href="#">About</a>
                    </li>
                    <li>
                        <a href="#">Services</a>
                    </li>
                    <li>
                        <a href="#">Contact</a>
                    </li>
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </div>
        <!-- /.container -->
    </nav>

	<!-- <div class="col-sm-4 col-sm-push-4 form-login" >
	    <form name="loginForm" novalidate method="post" action="login" modelAttribute="loginBean">
	        <div class="form-group">
	            <label for="username">Username</label>
	            <input type="text" id="username" name="username" class="form-control" placeholder="Enter username" autofocus="true" autocomplete="off" required>
	        </div>
	        <div class="form-group">
	            <label for="password">Password</label>
	            <input type="password" id="password" name="password" class="form-control" placeholder="Enter password" required>
	        </div>
	        <div class="form-group text-center">
	            <input type="submit" class="btn btn-primary" value="Log In"/>
	        </div>
	        <div class="form-group  text-center">
				<a href="#" tabindex="5" class="forgot-password">Forgot Password?</a>
			</div>
	    </form>
	</div> -->
	
	
	<div class="login-body">
	    <article class="container-login center-block">
			<section>
				<div class="tab-content tabs-login col-lg-12 col-md-12 col-sm-12 cols-xs-12">
					<div id="login-access" class="tab-pane fade active in">
						<h2><i class="glyphicon glyphicon-log-in"></i> Perficient</h2>						
						<form name="loginForm" method="post" action="login" modelAttribute="loginBean" accept-charset="utf-8" autocomplete="off" role="form" class="form-horizontal">
							<div class="form-group ">
								<label for="login" class="sr-only">Email</label>
									<input type="text" class="form-control" id="username" name="username" 
										placeholder="Email" tabindex="1" value="" />
							</div>
							<div class="form-group ">
								<label for="password" class="sr-only">Password</label>
									<input type="password" class="form-control" name="password" id="password"
										placeholder="Password" value="" tabindex="2" />
							</div>
							<div class="checkbox">
									<label class="control-label" for="remember_me">
										<input type="checkbox" name="remember_me" id="remember_me" value="1" class="" tabindex="3" /> Remember Me
									</label>
							</div>
							<br/>
							<div class="form-group ">				
									<button type="submit" name="log-me-in" id="submit" tabindex="5" class="btn btn-lg btn-primary">Login</button>
							</div>
						</form>			
					</div>
				</div>
			</section>
		</article>
	</div>

    <!-- Page Content -->
    <div class="container navbar-fixed-bottom">
        <hr>
        <!-- Footer -->
        <footer>
            <div class="row">
                <div class="col-lg-12">
                    <p>Copyright &copy; Perficient 2016</p>
                </div>
            </div>
            <!-- /.row -->
        </footer>

    </div>
    <!-- /.container -->

    <!-- jQuery -->
    <script src="js/jquery.js"></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="js/bootstrap.min.js"></script>

</body>

</html>
