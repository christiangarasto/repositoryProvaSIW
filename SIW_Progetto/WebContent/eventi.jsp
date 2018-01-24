<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head lang="it">
<title>Calabria E20</title>
<meta charset="utf-8">

	<link href="css/common.css?011" rel="stylesheet">
	<link href="css/background.css" rel="stylesheet">
	<link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
	<script src="jquery/jquery-3.2.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

	<script src="js/eventihandler.js"></script>

</head>

<body>
	<header>

		<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container-fluid">
				<div class="navbar-header">
			      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="myNavbar">
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>
			        <span class="icon-bar"></span>                        
			      </button>
					<aside class="pull-left">
						<img src="images/logo.png" height="30">
					</aside>
					<a id="brand" class="navbar-brand">Calabria E20</a>
				</div>
					<div class="collapse navbar-collapse" id = "myNavbar">
							<ul class="nav navbar-nav">
								<li><a href="homepage.jsp">Home</a></li>
								<li class="active"><a href="">Eventi </a></li>
<c:if test="${not loggato}">							
								<li><a href="iscriviutente.jsp">Diventa uno di noi</a></li>
</c:if>
							</ul>
<c:if test="${loggato}">

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="" class="dropdown-toggle" data-toggle="dropdown">
						<span class="glyphicon glyphicon-user"></span>	${nome} <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><p class="bg-primary">${username}</p></li>
								<li><a href="gestioneProfilo.jsp">Profilo</a></li>
								<li><a href="effettualogout">Logout</a></li>
							</ul>
						</li>
					</ul>
</c:if>
				</div>
			</div>
		</nav>
	</header>
	<div id = "tuttieventititle" class="jumbotron">
		<div class="container text-center">
    		<h1>Bacheca Calabria E20</h1>      
    		<p>Tutti i nostri eventi</p>
  		</div>
	</div>				
	
		<div id = "show_all_events" class = "container">
		</div>
	
</body>
</html>