<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>



<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />
<link href='https://fonts.googleapis.com/css?family=Lato:300,400,700'
	rel='stylesheet' type='text/css'>

<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
	<div id='stars'></div>
	<div id='stars2'></div>
	<div id='stars3'></div>
	<div id='title'>

		<span> <input type="image" src="img/rocket.gif" name="SMOKE"
			onclick="flipPin(this)" />
		</span> <br /> <span>TO MARS AND BEYOND </span>
	</div>

	<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript" src="/js/main.js"></script>


</body>

</html>
