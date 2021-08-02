
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
<title>Candidate Form | UiTM Jasin</title>
</head>

<body>
	<div class="center">
		<div class="center-top">
			<h1>Candidate Validation</h1>
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
				Student ID<input type="text" name="userId" autocomplete="off"
					value="${ candidate.userId }" disabled />
			</div>

			<div class="input-class">
				Faculty<input type="text" name="faculty" autocomplete="off"
					value="${ candidate.faculty }" disabled />
			</div>

			<div class="input-class">
				<legend>Achievement</legend>
				<textarea rows="5" cols="20" name="achievement" autocomplete="off"
					disabled>${ candidate.achievement }</textarea>
			</div>

			<div class="input-class">
				<legend>Manifesto</legend>
				<textarea rows="5" cols="20" name="manifesto" autocomplete="off"
					disabled>${ candidate.manifesto }</textarea>
			</div>

			<c:if test="${ param.approved == null }">
				<button id="approve-button">Approve And Display To Voters</button>
				<button id="decline-button">Decline</button>
			</c:if>
			<button id="back-button">Back</button>

		</form>
	</div>

	<script>
	const approveButton = document.querySelector('#approve-button');
	approveButton.onclick = (e) => {
		const string = 'Approve Candidate?';
		if (!confirm(string)) {
			e.preventDefault();
		}
	}

	const declineButton = document.querySelector('#decline-button');
	declineButton.onclick = (e) => {
		e.preventDefault();
		const string = 'Decline Candidate?';
		if (confirm(string)) {
			location.href = 'admin?decline=true&userId=${ candidate.userId }';
		}
	}

	const backButton = document.querySelector('#back-button');
	backButton.onclick = (e) => {
		e.preventDefault();
		location.href = 'admin';
	}
</script>
</body>

</html>