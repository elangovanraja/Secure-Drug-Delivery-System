<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="true"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script>
    history.forward();
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create a doctor</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

</head>
<body>
	<c:if test="${(user.name != null) && (user.type == 'Controller')}">
		<div class="jumbotron">
			<h2 class="text-center">Hospital Management System</h2>
			<h2 class="text-center">Create a Doctor</h2>
		</div>
		<c:url value="/logout" var="logoutUrl" />
		<form action="${logoutUrl}" method="post" id="logoutForm">
			<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" />
		</form>
		<!-- Data Toogle Navbar -->
		<div class="container-fluid navigation-header">
			<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="homepage.htm">HOME</a>
				</div>

				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav navbar-right">
						<li id="logOut"><a href="#"><span
								class="glyphicon glyphicon-user"></span>${user.userName}</a></li>
						<li id="logOut"><a href="javascript:formSubmit()"><span
								class="glyphicon glyphicon-log-out"></span> Logout</a></li>
					</ul>

				</div>
			</div>
			</nav>
		</div>
		<div class="container-fluid">
			<form:form commandName="doctor" action="addDoctor.htm" method="post">
				<div class="row">
					<div class="col-md-3">Doctor Name :</div>
					<div class="col-md-3">
						<form:input type="text" path="name" class="form-control"
							placeholder="Doctor Name" id="name" onkeyup="func1(this.value)"/>
						<font color="red"><form:errors path="name" /></font>
						<p class="validation" id="validateName"></p>
					</div>
					<div class="col-md-3">Doctor Specilization :</div>
					<div class="col-md-3">
						<form:input type="text" path="specialized" class="form-control"
							placeholder="Doctor Specilization" id="spec" onkeyup="func3(this.value)" />
						<font color="red"><form:errors path="specialized" /></font>
						<p class="validation" id="validateSpec"></p>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">Doctor Username :</div>
					<div class="col-md-3">
						<form:input type="text" path="userName" class="form-control"
							placeholder="Username" id="username1" onKeyUp="ajaxFunction(this.value)" />
						<font color="red"><form:errors path="userName" /></font>
						<p class="validation" id="validateUserName"></p>
					</div>
					<div class="col-md-2">
						<c:if test="${error1 == 1}">
							<p class="danger">Username already exists</p>
						</c:if>
					</div>
				</div>
				<div class="row">
					<div class="col-md-3">Doctor Password :</div>
					<div class="col-md-3">
						<form:input type="password" path="pwd" class="form-control"
							placeholder="Password" id="pwd" onFocus="checkPassword()"
					onFocusout="checkPassword2(this.value)" />
						<font color="red"><form:errors path="pwd" /></font>
						<p class="validation" id="validatePassword"></p>
					</div>

					<div class="row">
						<div class="col-md-3">Doctor SSN :</div>
						<div class="col-md-3">
							<form:input type="text" path="ssn" class="form-control"
								placeholder="SSN" id="ssn" onkeyup="func2(this.value)" />
							<font color="red"><form:errors path="ssn" /></font>
							<p class="validation" id="validateSSN"></p>
						</div>
					</div>
					<div class="col-md-5"></div>
					<div class="col-md-2">
						<input type="submit" value="Create" class="form-control" onclick="function1()" />
					</div>
					<div class="col-md-5"></div>
			</form:form>
		</div>
		<hr />
		<c:if test="${count == 1}">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-2">
						<strong class="text-center">EMPLOYEE ID</strong>
					</div>
					<div class="col-md-2">
						<strong class="text-center">DOCTOR NAME</strong>
					</div>
					<div class="col-md-2">
						<strong class="text-center">SPECILIZATION</strong>
					</div>
					<div class="col-md-2">
						<strong class="text-center">DOCTOR USERNAME</strong>
					</div>
					<div class="col-md-2">
						<strong class="text-center">DOCTOR PASSWORD</strong>
					</div>

				</div>
				<c:forEach items="${doctors}" var="ph1">
					<div class="row">
						<div class="col-md-2">
							<strong class="text-center">${ph1.personId}</strong>
						</div>
						<div class="col-md-2">
							<strong class="text-center">${ph1.name}</strong>
						</div>
						<div class="col-md-2">
							<strong class="text-center">${ph1.specialized}</strong>
						</div>
						<div class="col-md-2">
							<strong class="text-center">${ph1.userName}</strong>
						</div>
						<div class="col-md-2">
							<strong class="text-center">${ph1.pwd}</strong>
						</div>

					</div>

				</c:forEach>

			</div>
		</c:if>
		<c:if test="${count == 0}">
			<strong class="text-center">NO DOCTORS CREATED</strong>
		</c:if>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
		<script >
		function function1(){
			var name= document.getElementById("name").value;
			var spec = document.getElementById("spec").value;
			var ssn = document.getElementById("ssn").value;
			var username = document.getElementById("username1").value;
			var pwd =document.getElementById("pwd").value;
			
			if(name =="" || name ==null || spec =="" || spec ==null || ssn =="" || ssn ==null || username =="" || username ==null ||pwd =="" || pwd ==null ){
				alert("Values cannot be null");
				return false;
			}
		}
		function func1(x) {
			var flag = 0;
			if (!/^[a-zA-Z ]+$/.test(x) || x == "" || x ==null) {
				document.getElementById("validateName").innerHTML = "Name cannot contain special characters or blank";
				document.getElementById("name").style.border = "1px solid #FF0000";
				flag = 1;
			} else {
				document.getElementById("validateName").innerHTML = "";
				document.getElementById("name").style.border = "none";
			}
		}
		function func3(x) {
			var flag = 0;
			if (!/^[a-zA-Z ]+$/.test(x) || x == "" || x ==null) {
				document.getElementById("validateSpec").innerHTML = "Name cannot contain special characters or blank";
				document.getElementById("spec").style.border = "1px solid #FF0000";
				
				flag = 1;
			} else {
				document.getElementById("validateSpec").innerHTML = "";
				document.getElementById("spec").style.border = "none";
			}
		}
		function func2(x) {
			var flag = 0;
			if (!/^[0-9]+$/.test(x) || x == "" || x ==null) {
				document.getElementById("validateSSN").innerHTML = "Invalid ssn";
				document.getElementById("ssn").style.border = "1px solid #FF0000";
				flag = 1;
			} else {
				document.getElementById("validateSSN").innerHTML = "";
				document.getElementById("ssn").style.border = "none";
			}
		}

		function checkUserName(x) {

			var flag = 0;

			if (!/^[a-zA-Z0-9]+$/.test(x)|| x == "" || x ==null) {

				document.getElementById("validateUserName").innerHTML = "Username cannot contain special characters or blank";
				document.getElementById("username1").style.border = "1px solid #FF0000";
				flag = 1;
			} else {
				flag = 0;
			}

			if (flag == 0) {
				document.getElementById("validateUserName").innerHTML = "";
				document.getElementById("username1").style.border = "none";
			}
		}

		function checkUser(x) {
			if (x.length < 5) {
				document.getElementById("validateUserName").innerHTML = "Username cannot be blank or less than 5 charcters";
				document.getElementById("username1").style.border = "1px solid #FF0000";
			} else {
				document.getElementById("validateName").innerHTML = "";
				document.getElementById("name1").style.border = "none";
			}
		}

		function checkUserName2() {
			document.getElementById("validateUserName").innerHTML = "";
			document.getElementById("username1").style.border = "none";
		}
		
function checkPassword2(x) {
			
			if (x==null || x=="") {
				document.getElementById("validatePassword").innerHTML = "Password cannot be empty";
				document.getElementById("pwd").style.border = "1px solid #FF0000";
			}
		}

		</script>
		<script>
		function ajaxFunction(cityName) //functionName could be anything
		{

			//1. Create XmlHttpRequest Object
			//This could be written into an external .js file that could be used within other pages as well.
			var xmlHttp;

			try // Firefox, Opera 8.0+, Safari
			{
				xmlHttp = new XMLHttpRequest();
			} catch (e) {
				try // Internet Explorer
				{
					xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
				} catch (e) {
					try {
						xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
					} catch (e) {
						alert("Your browser does not support AJAX!");
						return false;
					}
				}
			}

			//2. Call the server-side script
			//cityName is the function parameter passed when the JS function is called
			xmlHttp.open("GET", "ajax.htm?q=" + cityName, true); //q is the name of the parameter to be used in JSP
			xmlHttp.send(); //leave blank, or pass null. Not used with GET requests

			//3. Check the server-data is ready
			xmlHttp.onreadystatechange = function() {
				if (xmlHttp.readyState == 4) {
					//4. Manipulate the DOM
					var json = JSON.parse(xmlHttp.responseText);

					document.getElementById("validateUserName").innerHTML = json.msg;

				}
			}

		}
		</script>
	</c:if>
	<c:if test="${user.name == null}">
		<p>PLEASE LOGIN TO CONTINUE</p>
		<a href="login.htm">CLICK HERE TO LOGIN</a>
	</c:if>

</body>
</html>