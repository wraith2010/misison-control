<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>

	<div class="container">



		<div class="starter-template">
			<h1>Mission Control</h1>
			<h2>Message: ${message}</h2>
		</div>

		<table>
			<tr>
				<td><a href="/pin00"><h1>${pin00}</h1></a></td>
				<td><a href="/pin01"><h1>${pin01}</h1></a></td>
				<td><a href="/pin02"><h1>${pin02}</h1></a></td>
			</tr>
			<tr>
				<td><a href="/pin10"><h1>${pin10}</h1></a></td>
				<td><a href="/pin11"><h1>${pin11}</h1></a></td>
				<td><a href="/pin12"><h1>${pin12}</h1></a></td>
			</tr>
			<tr>
				<td><a href="/pin20"><h1>${pin20}</h1></a></td>
				<td><a href="/pin21"><h1>${pin21}</h1></a></td>
				<td><a href="/pin22"><h1>${pin22}</h1></a></td>
			</tr>
		</table>

	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
