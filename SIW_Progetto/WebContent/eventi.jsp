<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head lang="it">
<title>Calabria E20</title>
<meta charset="utf-8">

	<link href="css/common.css" rel="stylesheet">
	<link href="css/background.css" rel="stylesheet">
	<link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
	<script src="jquery/jquery-3.2.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

	<script src="js/eventihandler.js"></script>

</head>

<body>

	<header>
		<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container">
				<div class="row">

					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<aside class="pull-left">
							<img src="images/logo.png" height="30">
						</aside>

						<a id="brand" class="navbar-brand">Calabria E20</a>

						<button class="navbar-toggle" data-toggle="collapse"
							data-target=".navHeaderCollapse">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span
								class="icon-bar"></span>
						</button>
					</div>

					<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8">
						<div class="collapse navbar-collapse navHeaderCollapse">
							<ul class="nav navbar-nav">
								<li><a href="homepage.jsp">Home</a></li>

								<li class="active dropdown"><a href=""class="dropdown-toggle" data-toggle="dropdown">Tutti gli eventi <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="eventiOggi.jsp">Eventi oggi</a></li>
										<li><a href="eventiZona.jsp">Eventi vicino a te</a></li>
									</ul></li>
<c:if test="${not loggato}">
								<li><a href="iscriviutente.jsp">Diventa uno di noi</a></li>
</c:if>
							</ul>
						</div>

					</div>
					
<c:if test="${loggato}">

						<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
							<figure>
								<li class="dropdown">
									<aside class="pull-right">
										<a href="#" class="dropdown-toggle" data-toggle="dropdown">
											<img src="images/profilo.png" class="img-circle" height="30"><b
											class="caret"></b>
										</a>
										<ul class="dropdown-menu">
											<li><p class="bg-primary">${username}</p></li>
											<li><a href="gestioneProfilo.jsp">Profilo</a></li>
											<li><a href="effettualogout">Logout</a></li>
										</ul>
									</aside>
								</li>
							</figure>
						</div>

</c:if>
				</div>
			</div>
		</nav>
	</header>
		<div class = "container">
			<div class = "jumbotron">
				<h1 id = "title">Bacheca Calabria E20</h1>
			</div>
		</div>
		<div class = "container">
				<div id = "show_all_events" class = "col-sm-6">
				</div>
		</div>
</body>
</html>