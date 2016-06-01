<!DOCTYPE html>
<html lang="en-us">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Perficient</title>
	<base href="${pageContext.request.contextPath}/">
	<link rel="stylesheet" href="dist/css/dev/login.min.css">
	<script src="dist/js/dev/login.min.js"></script>
</head>
<body class="loginpage">
	<section>
	<div class="carousel slide carousel-fade" data-ride="carousel">
	<!-- Wrapper for slides -->
		<div class="carousel-inner" role="listbox">
			<div class="item active"></div>
			<div class="item"></div>
			<div class="item"></div>
		</div>
	</div>
	<div class="opeiningscreen">
		<div class="logodiv">
			<a class="top-p" href="#"><img class="zoomin-p" src="dist/images/p.png"></a>
			<ul class="brandname">
				<li>P</li>
				<li>E</li>
				<li>R</li>
				<li>F</li>
				<li>I</li>
				<li>C</li>
				<li>I</li>
				<li>E</li>
				<li>N</li>
				<li>T</li>
			</ul>
			<ul class="brandbottom">
				<li><img src="dist/images/l1.png"></li>
				<li><img src="dist/images/l2.png"></li>
				<li><img src="dist/images/l3.png"></li>
			</ul>
		
			<div class="registerbody opacityanim">
				<div class="registerbody-left  col-xs-12">
					<form name="loginForm" method="post" action="login" modelAttribute="loginBean">
						<ul>
							<li>
								<div class="form-group">
									<label class="animatetoptobottom">Email Address:</label>
									<div class="input-divs animatetoptobottom">
										<input type="text" id="username" name="username" class="form-control" placeholder="Enter username" autofocus="true"  required>
									</div>
								</div>
							</li>
							<li>
								<div class="form-group">
								<div class="icnholder"><i class="regisr regit4"></i></div>
								<label class="animatetoptobottom">Password:</label>
								<div class="input-divs animatetoptobottom">
									<input type="password" id="password" name="password" class="form-control" placeholder="Enter password" required>
								</div>
								</div>
							</li>
							<li>
								<div class="form-group text-center">
									<input type="submit" class="btn btn-primary" value="Log In" />
								</div>
							</li>
						</ul>
					</form>
				</div>
			</div>
		</div>
	</div>
</section>
</body>
</html>