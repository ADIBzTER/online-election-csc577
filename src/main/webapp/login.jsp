<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="static/styles/login.css">
<title>Election | UiTM Jasin</title>
</head>

<body>
	<div class="center">

		<div class="welcome-message">
			<h1>
				Welcome to UiTM Jasin's<br>Election System
			</h1>
		</div>

		<div class="login-form">
			<form action="login" method="POST">

				<div class="form-title">
					<h1>Login</h1>
				</div>

				<div class="form-group">
					<label for="userId">Student Id</label> <input type="text"
						name="userId" id="userId" autocomplete="off" required>
				</div>

				<div class="form-group">
					<label for="password">Password</label> <input type="password"
						name="password" id="password" autocomplete="off" required>
				</div>
				<p class="warning-message">${ errorMessage }</p>

				<div class="user-type">
					<button data-user-type="voter" id="voter-button"
						class="user-type-button">Voter</button>
					<button data-user-type="candidate" id="candidate-button"
						class="user-type-button">Candidate</button>
					<button data-user-type="admin" id="admin-button"
						class="user-type-button">Admin</button>
				</div>

				<input id="login-button" type="submit" value="Login">
			</form>
		</div>

	</div>
	<script src="static/scripts/login.js"></script>
</body>

</html>