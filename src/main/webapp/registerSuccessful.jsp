
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="static/styles/candidate-register.css">
<title>Register Successful | UiTM Jasin</title>
</head>

<body>
	<div class="center">
		<h1>Thanks For The Registration</h1>
		<h2>Your Registration has been submitted</h2>
	</div>

	<script>
	setTimeout(() => {
		location.href = 'login';
	}, 5000);
	</script>
</body>

</html>