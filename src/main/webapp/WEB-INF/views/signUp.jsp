<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="true"%>

<%
	response.setHeader("pragma", "no-cache");
	response.setHeader("Cache-control", "no-cache, no-store, must-revalidate");
	response.setHeader("Expires", "0");
%>

<html>
<head>
<script>
    history.forward();
</script>
<title>Sign Up</title>

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
<style>
.danger {
	color: red;
}

.validation {
	color: red;
}
</style>

</head>
<body>
	<div class="jumbotron">
		<h2 class="text-center">Hospital Management Systems</h2>
	</div>

	<form:form action="signUp.htm" method="post" commandName="patient">
		<div class="row">
			<div class="col-md-2">Person Name :</div>
			<div class="col-md-2">
				<form:input path="name" name="personName" id="name1"
					placeholder="Person Name" onkeyup="func1(this.value)" />
				<form:errors path="name" />
				<br />
				<br />
				<p class="validation" id="validateName"></p>
			</div>
		</div>

		<div class="row">
			<div class="col-md-2">Preferred UserName :</div>
			<div class="col-md-2">
				<form:input path="userName" name="userName" id="username1"
					placeholder="Preferred UserName" onKeyUp="ajaxFunction(this.value)" />
				<form:errors path="userName" />
				<br />
				<br />
				<p class="validation" id="validateUserName"></p>
			</div>
			<div class="col-md-2">
				<c:if test="${error1 == 1}">
					<p class="danger">Username already exists</p>
				</c:if>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">Password :</div>
			<div class="col-md-2">
				<form:password path="pwd" name="password" id="password1"
					placeholder="Password" onFocus="checkPassword()"
					onFocusout="checkPassword2(this.value)" />
				<form:errors path="pwd" />
				<br />
				<br />
				<p class="validation" id="validatePassword"></p>
			</div>
		</div>
		<div class="row">
			<div class="col-md-2">Email :</div>
			<div class="col-md-2">
				<form:input path="email" name="email" id="email1"
					placeholder="Email" onFocus="checkPassword()"
					onFocusout="checkPassword3(this.value)" />
				<form:errors path="email" />
				<br />
				<br />
				<p class="validation" id="validateEmail"></p>
			</div>
		</div>
		<input type="submit" value="Submit" />
	</form:form>
	<script>
		function func1(x) {
			var flag = 0;
			if (!/^[a-zA-Z ]+$/.test(x)) {
				document.getElementById("validateName").innerHTML = "Name cannot contain special characters or blank";
				document.getElementById("name1").style.border = "1px solid #FF0000";
				flag = 1;
			} else {
				document.getElementById("validateName").innerHTML = "";
				document.getElementById("name1").style.border = "none";
			}
		}

		function checkUserName(x) {

			var flag = 0;

			if (!/^[a-zA-Z0-9]+$/.test(x)) {

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

		function checkPassword() {
			document.getElementById("validatePassword").innerHTML = "";
			document.getElementById("password1").style.border = "none";
		}

		function checkPassword2(x) {
			
			if (x==null || x=="") {
				document.getElementById("validatePassword").innerHTML = "Password cannot be empty";
				document.getElementById("password1").style.border = "1px solid #FF0000";
			}
		}
function checkPassword3(x) {
			
	 var atpos = x.indexOf("@");
	    var dotpos = x.lastIndexOf(".");
	    if (atpos<1 || dotpos<atpos+2 || (dotpos+2) >= x.length) {
	    	document.getElementById("validateEmail").innerHTML = "Password cannot be empty";
			document.getElementById("email1").style.border = "1px solid #FF0000";
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
</body>
</html>