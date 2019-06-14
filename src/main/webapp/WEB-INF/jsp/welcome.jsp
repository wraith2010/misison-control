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
		</div>

		<table>
			<tr>
				<td><button class="button button-blue"
						onclick="window.location.href = '/pin00';">${pin00}</button></td>
				<td><button class="button button-yellow"
						onclick="window.location.href = '/pin01';">${pin01}</button></td>
				<td><button class="button button-blue"
						onclick="window.location.href = '/pin02';">${pin02}</button></td>
			</tr>
			<tr>
				<td><button class="button button-green"
						onclick="window.location.href = '/pin10';">${pin10}</button></td>
				<td><button class="button button-white"
						onclick="window.location.href = '/pin11';">${pin11}</button></td>
				<td><button class="button button-green"
						onclick="window.location.href = '/pin12';">${pin12}</button></td>
			</tr>
			<tr>
				<td><button class="button button-red"
						onclick="window.location.href = '/pin20';">${pin20}</button></td>
				<td><button class="button button-yellow"
						onclick="window.location.href = '/pin21';">${pin21}</button></td>
				<td><button class="button button-red"
						onclick="window.location.href = '/pin22';">${pin22}</button></td>
			</tr>
		</table>

	</div>

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>

</html>
