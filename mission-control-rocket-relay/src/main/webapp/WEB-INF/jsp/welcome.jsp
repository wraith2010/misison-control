<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<c:url value="/css/main.css" var="jstlCss" />

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<link href='https://fonts.googleapis.com/css?family=Lato:300,400,700'
	rel='stylesheet' type='text/css'>
<link href="${jstlCss}" rel="stylesheet" />


</head>
<body>
	<div id='stars'></div>
	<div id='stars2'></div>
	<div id='stars3'></div>
	<div id='title'>

		<span> <img src="img/rocket.gif" /></span> <br /> <span>TO
			MARS AND BEYOND</span>
	</div>

</body>

</html>
