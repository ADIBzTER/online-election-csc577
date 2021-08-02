
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="static/styles/candidate-details.css">
<title>Congratulation | UiTM Jasin</title>
</head>

<body>
	<div class="center">
		<div class="center-top">
			<h1>Congratulation!</h1>
			<img class="candidate-photo" src="${ candidate.imageLocation }"
				alt="candidate-image">
		</div>

		<form action="admin" method="POST">
			<input type="hidden" name="userId" value="${ candidate.userId }">

			<div class="input-class">
				Full Name<input type="text" name="name" autocomplete="off"
					value="${ candidate.name }" disabled />
			</div>

			<div class="input-class">
				Faculty<input type="text" name="faculty" autocomplete="off"
					value="${ candidate.faculty }" disabled />
			</div>

			<h2>Congratulation ${ candidate.name } for winning the election</h2>
		</form>
	</div>

</body>

</html>