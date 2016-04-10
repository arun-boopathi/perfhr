<!DOCTYPE html>
<html lang="en-us">
<head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Perficient</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="css/font-awesome.min.css">
<link rel="stylesheet" href="css/login.css">
<script>
	
</script>
</head>
<body>
	<div class="form responsive">
		<div class="header"><h2>Sign In</h2></div>
		<p style="color:#faffbd; margin: 5px;"><strong>${msg}</strong></p>
		<div class="login">
			<form name="loginForm" method="post" action="login" modelAttribute="loginBean">
			<ul>
				<li>
					<span class="un"><i class="fa fa-user"></i></span><input type="text" name="username" autofocus required="" class="text" placeholder="Perficient Email"></li>
				<li>
					<span class="un"><i class="fa fa-lock"></i></span><input type="password" name="password" required="" class="text" placeholder="User Password"></li>
				<li>
					<input type="submit" value="LOGIN" class="btn">
				</li>
				<li>
					<div class="span"><span class="ch"><input type="checkbox" id="r"> <label for="r">Remember Me</label></span></div>
				</li>
			</ul>
			</form>
		</div>
	</div>
</body>
</html>