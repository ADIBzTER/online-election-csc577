<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="./static/styles/admin.css">
<title>Election | UiTM Jasin</title>
</head>

<body>
	<button id="end-button">End Election</button>
	<button id="logout-button">Logout</button>
	<div class="center">

		<c:forEach items="${ candidateList }" var="candidate">
			<c:if test="${ !candidate.approved}">
				<c:set var="hasPending" value="true" />
			</c:if>
		</c:forEach>
		<c:if test="${ hasPending }">
			<div class="message">
				<h1>Pending Verification</h1>
			</div>
		</c:if>

		<c:forEach items="${ candidateList }" var="candidate">
			<c:if test="${ !candidate.approved}">
				<div class="row-div">
					<img class="candidate-image" src="${ candidate.imageLocation }"
						alt="candidate-image">
					<div class="candidate-details">
						<h3>Name: ${ candidate.name }</h3>
						<h3>Student ID: ${ candidate.userId }</h3>
						<h3>Faculty: ${ candidate.faculty }</h3>
					</div>
					<button class="details-button"
						onclick="location.href = 'admin?candidateId=${ candidate.userId }';">More
						Details</button>
				</div>
			</c:if>
		</c:forEach>

		<c:forEach items="${ candidateList }" var="candidate">
			<c:if test="${ candidate.approved}">
				<c:set var="hasApproved" value="true" />
			</c:if>
		</c:forEach>
		<c:if test="${ hasApproved }">
			<div class="message">
				<h1>Approved Candidates</h1>
			</div>
		</c:if>

		<c:forEach items="${ candidateList }" var="candidate">
			<c:if test="${ candidate.approved}">
				<div class="row-div">
					<img class="candidate-image" src="${ candidate.imageLocation }"
						alt="candidate-image">
					<div class="candidate-details">
						<h3>Name: ${ candidate.name }</h3>
						<h3>Student ID: ${ candidate.userId }</h3>
						<h3>Faculty: ${ candidate.faculty }</h3>
					</div>
					<button class="details-button"
						onclick="location.href = 'admin?candidateId=${ candidate.userId }&approved=true;'">More
						Details</button>
				</div>
			</c:if>
		</c:forEach>

	</div>
	<script>
	const logoutButton = document.querySelector('#logout-button');
	logoutButton.onclick = (e) => {
		location.href = 'login';
	}

	const endButton = document.querySelector('#end-button');
	endButton.onclick = (e) => {
		const string = 'End The Election?';
		if (confirm(string)) {
			e.preventDefault();
			location.href = 'admin?endElection=true';
		}
	}
	</script>
</body>

</html>