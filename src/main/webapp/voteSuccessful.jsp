
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
<title>Vote Successful | UiTM Jasin</title>
</head>

<body>
	<div class="center">
		<h1>Thanks For Your Vote!</h1>
		<h2>Your Vote Has Been Recorded</h2>
	</div>

	<script>
		const photoInput = document.querySelector('#photo-input');
		photoInput.onchange = (e) => {
			let candidatePhoto = document.querySelector('.candidate-photo');
			let photoUrl = URL.createObjectURL(photoInput.files[0]);
			candidatePhoto.src = photoUrl;
		};
	</script>
</body>

</html>