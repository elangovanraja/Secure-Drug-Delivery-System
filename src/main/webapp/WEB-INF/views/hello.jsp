<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page session="false"%>
<html>
<body>
	<html>
<head>
<script>
    history.forward();
</script>
	<title>Hospital Management Systems</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

</head>
<body>

<div class="jumbotron"><h2 class="text-center">Hospital Management Systems</h2></div>

	<!-- Data Toogle Navbar -->
	<div class="container-fluid navigation-header">
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span> 
					</button>
					<a class="navbar-brand" href="#">HOME</a>
				</div>
    
				<div class="collapse navbar-collapse" id="myNavbar">      						
					<ul class="nav navbar-nav navbar-right">
						<li id = "signUpId"><a href="signUp.htm"><span class="glyphicon glyphicon-user"></span> Sign Up</a></li>
						<li id = "logIn"><a href="login.htm" ><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
						
					</ul>

    			</div>
  			</div>
		</nav>				
	</div>
	<p>${user.userName}</p>
</body>
</html>
	