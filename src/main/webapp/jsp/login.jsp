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
	
	<div class="content-wrapper">
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h4 class="page-head-line">Perficient Login</h4>
                </div>
            </div>
            <p class="text-danger"><strong>${msg}</strong></p>
            <div class="row">
            	<div class="col-md-6" style="border: 1px solid #101010; border-radius: 25px; padding: 20px;">
                	<form name="loginForm" method="post" action="login" modelAttribute="loginBean" accept-charset="utf-8" autocomplete="off" role="form" class="form-horizontal">
					  <div class="form-group">
					    <label for="inputEmail3" class="col-sm-2 control-label">Email</label>
					    <div class="col-sm-10">
					      <input type="text" name="username" class="form-control" id="inputEmail3" placeholder="Email">
					    </div>
					  </div>
					  <div class="form-group">
					    <label for="inputPassword3" class="col-sm-2 control-label">Password</label>
					    <div class="col-sm-10">
					      <input type="password" name="password" class="form-control" id="inputPassword3" placeholder="Password">
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <div class="checkbox">
					        <label>
					          <input type="checkbox"> Remember me
					        </label>
					      </div>
					    </div>
					  </div>
					  <div class="form-group">
					    <div class="col-sm-offset-2 col-sm-10">
					      <button type="submit" class="btn btn-success" style="width: 100%;">Sign in</button>
					    </div>
					  </div>
					</form>
                </div>
                <div class="col-md-6">
                    <div class="alert alert-info">
                        <strong> Some of its features are given below :</strong>
                        <ul>
                            <li>
                                Perficient HR Services
                            </li>
                            <li>
                                Perficient Referral Services
                            </li>
                            <li>
                                Perficient Recruitment Services
                            </li>
                        </ul>
                    </div>
                    <div class="alert alert-success">
                         <strong> Instructions To Use:</strong>
                        <ul>
                            <li>
                               Provide Perficient email to login.
                            </li>
                        </ul>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <!-- CONTENT-WRAPPER SECTION END-->
    <footer>
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    &copy; 2016  | By : <a href="http://www.perficient.com/" target="_blank">Perficient</a>
                </div>

            </div>
        </div>
    </footer>
    <!-- /.container -->
</body>

</html>
