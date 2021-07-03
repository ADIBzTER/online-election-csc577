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
			<div class="grid-div">

				<form action="vote" method="POST">
					<c:forEach items="${ candidateList }" var="candidate">
						<div class="grid-item">
							<img class="candidate-image"
								src="https://images2.minutemediacdn.com/image/upload/c_crop,h_726,w_1292,x_199,y_0/f_auto,q_auto,w_1100/v1578352479/shape/mentalfloss/62455-shout-factory1.jpg"
								alt="candidate-image">
							<p>Name: ${ candidate.name }</p>
							<p>Faculty: ${ candidate.faculty }</p>
							<p>Achievement: ${ candidate.achievement }</p>
							<p>Manifesto: ${ candidate.manifesto }</p>

							<input type="radio" name="userId" id="${ candidate.userId }"
								value="${ candidate.userId }"> <label
								for="${ candidate.userId }">Choose Me</label>
						</div>
					</c:forEach>
				</form>

			</div>
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