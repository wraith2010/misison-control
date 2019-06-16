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
	<div id='stars'></div>
	<div id='stars2'></div>
	<div id='stars3'></div>
	<div id='title' align="center">

		<span> <img src="img/rocket.gif"
			style="width: 200px; height: 200px;" /></span> <br /> <span>TO MARS
			AND BEYOND</span>

		<section style="width: 100%;">
			<div style="width: 22%; display: inline-block;">
				<table>
					<tr>
						<td><button class="button button-blue-${pin00_out}"
								name="pin00_out" type="button" onclick="flipPin(this)">${pin00_out}</button></td>
						<td><button class="button button-yellow-${pin01_out}"
								name="pin01_out" type="button" onclick="flipPin(this)">${pin01_out}</button></td>
						<td><button class="button button-blue-${pin02_out}"
								name="pin02_out" type="button" onclick="flipPin(this)">${pin02_out}</button></td>
					</tr>
					<tr>
						<td><button class="button button-green-${pin10_out}"
								name="pin10_out" type="button" onclick="flipPin(this)">${pin10_out}</button></td>
						<td><button class="button button-white-${pin11_out}"
								name="pin11_out" type="button" onclick="flipPin(this)">${pin11_out}</button></td>
						<td><button class="button button-green-${pin12_out}"
								name="pin12_out" type="button" onclick="flipPin(this)">${pin12_out}</button></td>
					</tr>
					<tr>
						<td><button class="button button-red-${pin20_out}"
								name="pin20_out" type="button" onclick="flipPin(this)">${pin20_out}</button></td>
						<td><button class="button button-yellow-${pin21_out}"
								name="pin21_out" type="button" onclick="flipPin(this)">${pin21_out}</button></td>
						<td><button class="button button-red-${pin22_out}"
								name="pin22_out" type="button" onclick="flipPin(this)">${pin22_out}</button></td>
					</tr>
				</table>
			</div>
			<div style="width: 22%; display: inline-block;">
				<table>
					<tr>
						<td><button
								class="square-button button-white-${button01_out}"
								name="button01_out" type="button" onclick="flipPin(this)">${button01_out}</button></td>
						<td><button
								class="square-button button-white-${button02_out}"
								name="button02_out" type="button" onclick="flipPin(this)">${button02_out}</button></td>
						<td><button
								class="square-button button-white-${button03_out}"
								name="button03_out" type="button" onclick="flipPin(this)">${button03_out}</button></td>
					</tr>
					<tr>
						<td><button
								class="square-button button-white-${button04_out}"
								name="button04_out" type="button" onclick="flipPin(this)">${button04_out}</button></td>
						<td><button
								class="square-button button-white-${button05_out}"
								name="button05_out" type="button" onclick="flipPin(this)">${button05_out}</button></td>
						<td><button
								class="square-button button-white-${button06_out}"
								name="button06_out" type="button" onclick="flipPin(this)">${button06_out}</button></td>
					</tr>
				</table>
			</div>
			<div style="width: 22%; display: inline-block;">
				<table>
					<tr>
						<td><button class="square-button button-white-${button07_out}"
								name="button07_out" type="button" onclick="flipPin(this)">${button07_out}</button></td>
						<td><button
								class="square-button button-white-${button08_out}"
								name="button08_out" type="button" onclick="flipPin(this)">${button08_out}</button></td>
						<td><button class="square-button button-white-${button09_out}"
								name="button09_out" type="button" onclick="flipPin(this)">${button09_out}</button></td>
					</tr>
					<tr>
						<td><button class="button button-green-${switch01_out}"
								name="switch01_out" type="button" onclick="flipPin(this)">${switch01_out}</button></td>
						<td><button class="button button-white-${switch02_out}"
								name="switch02_out" type="button" onclick="flipPin(this)">${switch02_out}</button></td>
						<td><button class="button button-green-${switch03_out}"
								name="switch03_out" type="button" onclick="flipPin(this)">${switch03_out}</button></td>
					</tr>
				</table>
			</div>
			<div style="width: 22%; display: inline-block;">
				<button class="mega-button button-red-${mega_button01_out}"
					name="mega_button01_out" type="button" onclick="flipPin(this)">${mega_button01_out}</button>
			</div>
		</section>
	</div>

	<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/main.js"></script>

</body>

</html>
