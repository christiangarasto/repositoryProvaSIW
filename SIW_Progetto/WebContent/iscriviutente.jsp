<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id="u" class="model.Utente" scope="request" />

<!DOCTYPE html>
<html>
<head lang="it">
	<title>Calabria E20</title>
	<meta charset="utf-8">

	<link href="css/iscrizione.css" rel="stylesheet">
	<link href="css/common.css" rel="stylesheet">
	<link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
	
	<script src="jquery/jquery-3.2.1.min.js"></script>
	<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
	
	<link href="js/utility.js" rel="stylesheet">
	<script src="js/utility.js"></script>
	
</head>

<body>
	<header>		
		<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container">

				<aside class="pull-left">
					<img src="images/logo.png" height="30">
				</aside>

				<a id="brand" class="navbar-brand">Calabria E20</a>

				<button class="navbar-toggle" data-toggle="collapse"
					data-target=".navHeaderCollapse">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>

				<div class="collapse navbar-collapse navHeaderCollapse">
					<ul class="nav navbar-nav navbar-right">
						<li><a href="homepage.jsp">Home</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Eventi <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="eventiOggi.html">Eventi oggi</a></li>
								<li><a href="#">Tutti gli eventi</a></li>
								<li><a href="#">Eventi vicino a te</a></li>
							</ul></li>
						<li><a href="#">Recensioni</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown">Social Media <b class="caret"></b></a>
							<ul class="dropdown-menu">
							<li><a href="#">Facebook</a></li>
							<li><a href="#">Instagram</a></li>
							<li><a href="#">Twitter</a></li>
							</ul>
						</li>
						<li class="active"><a href="#">Diventa uno di noi</a></li>
						<li><a href="#">Contattaci</a></li>

						<li class="navbar navbar-nav form-inline navbar-right">
							<div id="search" class="input-group">
								<input type="text" class="form-control">
								<div id="lens" class="input-group-btn">
									<button class="btn btn-default">
										<i class="glyphicon glyphicon-search"></i>
									</button>
								</div>

							</div>
						</li>

					</ul>
				</div>
			</div>
		</nav>
		
		<h1>Diventa membro di Calabria E20</h1>
		<h3>Compila il form qui di seguito per entrare a far parte della community e sponsorizzare i tuoi eventi</h3>
		
	</header>
	<section class="row" class="moduloRegistrazione">
		<div class="col-sm-3">
			<form method="post" action="iscriviutente">
				<div class="form-group"><label for="nome">Nome:</label><input id="nome" type="text" class="form-control"/></div>
				<div class="form-group"><label for="cognome">Cognome:</label><input id="cognome" type="text" class="form-control"/></div>
				<div class="form-group"><label for="piva">Partita Iva:</label><input id="piva" type="text" class="form-control"/></div>
				<div class="form-group"><label for="email">E-mail:</label><input id="email" type="text" class="form-control"/></div>
				<div class="form-group"><label for="password">Password:</label><input id="password" type="password" class="form-control" /></div>
				<div class="form-group"><label for="conferma">Conferma Password:</label><input id="conferma" type="password" class="form-control" /></div>
				<div class="form-group">
					<input name="validaDati" type="button" value="Valida Dati" class="btn btn-warning" onclick="valida()"/>
					<input name="resetDati" type="reset" value="Reset Dati"  class="btn btn-danger" />
					<input name="inviaDati" type="submit" value="Invia Dati"  class="btn btn-success"/>
				</div>
			</form>
		</div>
	</section>
	
</body>
</html>