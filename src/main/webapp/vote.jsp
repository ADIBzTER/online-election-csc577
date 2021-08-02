<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./static/styles/vote.css">
<title>Election | UiTM Jasin</title>
</head>

<body>
	<div class="center">

		<div class="message">
			<h1>Choose One Candidate</h1>
		</div>

		<div class="vote-div">
			<form action="vote" method="POST">
				<div class="grid-div">

					<c:forEach items="${ candidateList }" var="candidate">
						<div class="grid-item">
							<div class="candidate-image">
								<img src="${ candidate.imageLocation }" alt="candidate-image">
							</div>
							<p>Name: ${ candidate.name }</p>
							<p>Faculty: ${ candidate.faculty }</p>
							<p>Achievement: ${ candidate.achievement }</p>
							<p>Manifesto: ${ candidate.manifesto }</p>

							<div class="choose-div">
								<input type="radio" name="candidateId" id="${ candidate.userId }"
									value="${ candidate.userId }"> <label
									for="${ candidate.userId }">Choose Me</label>
							</div>
						</div>
					</c:forEach>
				</div>
			</form>

		</div>

		<button id="vote-button">Vote</button>

	</div>

	<script>
		const form = document.querySelector('form');
		const submitButton = document.querySelector('#vote-button');
		submitButton.onclick = (e) => {
			form.submit();
		};
	</script>
</body>

</html>