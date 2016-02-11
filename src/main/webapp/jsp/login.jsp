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

    <!-- Image Background Page Header -->
    <!-- Note: The background image is set within the business-casual.css file. -->
    <header class="business-header">
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="tagline">Perficient HR India</h1>
                </div>
            </div>
        </div>
    </header>

	<div class="col-sm-4 col-sm-push-4 form-login" >
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
