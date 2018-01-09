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
								<li class="active"><a href="#">Home</a></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">Eventi <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="eventiOggi.jsp">Eventi oggi</a></li>
										<li><a href="eventi.jsp">Tutti gli eventi</a></li>
										<li><a href="eventiZona.jsp">Eventi vicino a te</a></li>
									</ul></li>
								<li><a href="#">Recensioni</a></li>
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown">Social Media <b class="caret"></b></a>
									<ul class="dropdown-menu">
										<li><a href="#">Facebook</a></li>
										<li><a href="#">Instagram</a></li>
										<li><a href="#">Twitter</a></li>
									</ul></li>
								<li><a href="iscriviutente.jsp">Diventa uno di noi</a></li>
								<li><a href="#">Contattaci</a></li>
							</ul>
						</div>

					</div>
					<div class="col-xs-2 col-sm-2 col-md-2 col-lg-2">
						<figure>
							<aside class="pull-right">
								<li class="dropdown"><a href="#" class="dropdown-toggle"
									data-toggle="dropdown"><img src="images/profilo.png"
										height="30"><b class="caret"></b></a> 
									<c:if test="${loggato}">
										<ul class="dropdown-menu">
											<li><p class="bg-success">${username}</p></li>
											<li><a href="gestioneProfilo.jsp">Profilo</a></li>
											<li><a href="effettualogout">Logout</a></li>
										</ul>
									</c:if>
							</aside>
						</figure>
					</div>
				</div>
			</div>
		</nav>


		<div class="container">
			<div class="row">

				<div class="col-sm-3"></div>

				<div class="col-sm-6">
					<div class="jumbotron" id="description">
						<h2>Benvenuti sul sito</h2>
						<h1>Calabria E20</h1>
						<p>
							Il nostro servizio web offre la possibilitÃ  di trovare gli
							eventi a te più vicini scegliendo tra un vasto assortimento di
							eventi raggruppati per genere e zona.<br> Calabria E20 ti
							concede la possibilità  di prenotare presso i locali che
							aderiscono al nostro sito per sponsorizzare il loro evento.
							Offriamo inoltre la possibilità  di acquistare il biglietto per
							il concerto del tuo cantante preferito in occasione della sua
							tappa nella nostra splendida terra.
						</p>
					</div>
				</div>

				<c:if test="${not loggato}">
					<div class="col-sm-3">
						<div class="jumbotron" id="log">
							<h4>
								<strong>Area riservata</strong>
							</h4>
							<h6>Sei un utente registrato? Effettua da qui il login</h6>
							<form method="post" action="effettualogin">
								<div class="form-group">
									<input type="text" placeholder="e-mail" name="email"><br>
									<input type="password" placeholder="password" name="password"><br>
									<input type="submit" value="Login" class="btn btn-success">
								</div>
							</form>
						</div>
					</div>
				</c:if>
			</div>
		</div>

	</header>
	<footer> Sito web sviluppato dagli studenti Garasto Christian
		e Ventura Paolo </footer>
</body>
</html>