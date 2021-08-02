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
<title>Candidate Form | UiTM Jasin</title>
</head>

<body>
	<div class="center">
		<div class="center-top">
			<h1>Candidate Verification</h1>
			<img class="candidate-photo" src="images/person-icon.png"
				alt="candidate-image">
		</div>

		<form action="candidate" method="POST" enctype="multipart/form-data">
			Photo: <input id="photo-input" type="file" name="photo"
				accept=".png,.jpg,.jpeg" required />

			<div class="input-class">
				Full Name<input type="text" name="name" autocomplete="off"
					value="${ name }" readonly />
			</div>

			<div class="input-class">
				Student ID<input type="text" name="userId" autocomplete="off"
					value="${ userId }" readonly />
			</div>

			<div class="input-class">
				Faculty<input type="text" name="faculty" autocomplete="off"
					value="${ faculty }" readonly />
			</div>

			<div class="input-class">
				<legend>Achievement</legend>
				<textarea rows="5" cols="20" name="achievement" autocomplete="off"
					required></textarea>
			</div>

			<div class="input-class">
				<legend>Manifesto</legend>
				<textarea rows="5" cols="20" name="manifesto" autocomplete="off"
					required></textarea>
			</div>

			<input id='verify-button' type="submit" value="VERIFY" />
			<button id='cancel-button'>CANCEL</button>
		</form>
	</div>

	<script>
		const photoInput = document.querySelector('#photo-input');
		photoInput.onchange = (e) => {
			let candidatePhoto = document.querySelector('.candidate-photo');
			let photoUrl = URL.createObjectURL(photoInput.files[0]);
			candidatePhoto.src = photoUrl;
		};

		const cancelButton = document.querySelector('#cancel-button');
		cancelButton.onclick = (e) => {
			e.preventDefault();
			location.href = 'login';
		}
	</script>
</body>

</html>