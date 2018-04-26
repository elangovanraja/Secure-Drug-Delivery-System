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
<title>ADD MEDICINE</title>
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
			<h2 class="text-center">ADD MEDICINE</h2>
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
			<form:form commandName="medicine" action="addMedicine.htm"
				method="post">

				<div class="col-md-3">Medicine Name :</div>
				<div class="col-md-3">
					<form:input type="text" path="medName" class="form-control"
						placeholder="Medicine Name" id="med" onkeyup="func1(this.value)" />
					<font color="red"><form:errors path="medName" /></font>
					<p class="validation" id="validateName"></p>
				</div>
				<div class="col-md-3">Medicine Proposition :</div>
				<div class="col-md-3">
					<form:input type="text" path="medProposition" class="form-control"
						placeholder="Medicine Proposition" id="prop"
						onkeyup="func2(this.value)" />
					<font color="red"><form:errors path="medProposition" /></font>
					<p class="validation" id="validateProp"></p>
				</div>
				<div class="col-md-3">Quantity :</div>
				<div class="col-md-3">
					<form:input type="number" path="medQuantity" class="form-control"
						id="quantity" onkeyup="func3(this.value)" />
					<font color="red"><form:errors path="medQuantity" /></font>
					<p class="validation" id="validateQuantity"></p>
				</div>

				<div class="col-md-5"></div>
				<div class="col-md-2">
					<input type="submit" value="Create" class="form-control" />
				</div>
				<div class="col-md-5"></div>
			</form:form>
		</div>
		<hr />
		<c:if test="${count == 1}">
			<div class="container-fluid">
				<div class="row">
					<div class="col-md-3">
						<strong class="text-center">MEDICINE ID</strong>
					</div>
					<div class="col-md-3">
						<strong class="text-center">MEDICINE NAME</strong>
					</div>
					<div class="col-md-3">
						<strong class="text-center">PROPOSITION</strong>
					</div>
					<div class="col-md-3">
						<strong class="text-center">QUANTITY</strong>
					</div>

				</div>
				<c:forEach items="${doctors}" var="ph1">
					<div class="row">
						<div class="col-md-3">
							<strong class="text-center">${ph1.id}</strong>
						</div>
						<div class="col-md-3">
							<strong class="text-center">${ph1.medName}</strong>
						</div>
						<div class="col-md-3">
							<strong class="text-center">${ph1.medProposition}</strong>
						</div>
						<div class="col-md-3">
							<strong class="text-center">${ph1.medQuantity}</strong>
						</div>
					</div>

				</c:forEach>

			</div>
		</c:if>
		<c:if test="${count == 0}">
			<strong class="text-center">Medicines Unavailable</strong>
		</c:if>
		<script>
			function formSubmit() {
				document.getElementById("logoutForm").submit();
			}
		</script>
		<script>
			function func1(x) {
				var flag = 0;
				if (!/^[a-zA-Z ]+$/.test(x) || x == "" || x ==null) {
					document.getElementById("validateName").innerHTML = "Name cannot contain special characters or blank";
					document.getElementById("med").style.border = "1px solid #FF0000";
					flag = 1;
				} else {
					document.getElementById("validateName").innerHTML = "";
					document.getElementById("med").style.border = "none";
				}
			}
			function func2(x) {
				var flag = 0;
				if (!/^[0-9]+$/.test(x) || x == "" || x ==null) {
					document.getElementById("validateProp").innerHTML = "Invalid proposition";
					document.getElementById("prop").style.border = "1px solid #FF0000";
					flag = 1;
				} else {
					document.getElementById("validateProp").innerHTML = "";
					document.getElementById("prop").style.border = "none";
				}
			}
			function func3(x) {
				var flag = 0;
				if (!/^[0-9]+$/.test(x) || x == "" || x ==null) {
					document.getElementById("validateQuantity").innerHTML = "Invalid quantity";
					document.getElementById("quantity").style.border = "1px solid #FF0000";
					flag = 1;
				} else {
					document.getElementById("validateQuantity").innerHTML = "";
					document.getElementById("quantity").style.border = "none";
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