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
	<div class="center">

		<div class="message">
			<h1>Pending Verification</h1>
		</div>

		<c:forEach items="${ candidateList }" var="candidate">
			<c:if test="${ !candidate.approved}">
				<div class="row-div">
					<img class="candidate-image" src="${ candidate.imageLocation }"
						alt="candidate-image">
					<div class="candidate-details">
						<p>Name: ${ candidate.name }</p>
						<p>Student ID: ${ candidate.faculty }</p>
					</div>
					<button
						onclick="location.href = 'admin?candidateId=${ candidate.userId }';">More
						Details</button>
				</div>
			</c:if>
		</c:forEach>

		<div class="message">
			<h1>Approved Candidates</h1>
		</div>

		<c:forEach items="${ candidateList }" var="candidate">
			<c:if test="${ candidate.approved}">
				<div class="row-div">
					<img class="candidate-image" src="${ candidate.imageLocation }"
						alt="candidate-image">
					<div class="candidate-details">
						<p>Name: ${ candidate.name }</p>
						<p>Student ID: ${ candidate.faculty }</p>
					</div>
					<button
						onclick="location.href = 'admin?candidateId=${ candidate.userId }';">More
						Details</button>
				</div>
			</c:if>
		</c:forEach>

	</div>
</body>

</html>