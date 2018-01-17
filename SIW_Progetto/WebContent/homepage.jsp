<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head lang="it">

<title>Calabria E20</title>
<meta charset="utf-8">

<link href="css/common.css?111" rel="stylesheet">
<link href="css/background.css" rel="stylesheet">
<link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
<script src="jquery/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<script src="js/creazioneevento.js"></script>

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


		<div class="container" id="info">
			<div class="row">

<c:if test="${not loggato}">

					<div class="col-sm-3"></div>

					<div class="col-sm-6">
						<div class="jumbotron" id="description">
							<h2>Benvenuti sul sito</h2>
							<h1>Calabria E20</h1>
							<p>
								Il nostro servizio web offre la possibilità di trovare gli
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

					<div class="col-sm-3">
						<div class="jumbotron" id="log">
							<h4>
								<strong>Area riservata</strong>
							</h4>
							<h6>Sei un utente registrato? Effettua da qui il login</h6>
							<form method="post" action="effettualogin">
								<div class="form-group">

									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-user"></i></span> <input id="email"
											type="text" class="form-control" name="email"
											placeholder="Email">
									</div>
									<div class="input-group">
										<span class="input-group-addon"><i
											class="glyphicon glyphicon-lock"></i></span> <input id="password"
											type="password" class="form-control" name="password"
											placeholder="Password">
									</div>
									<br>
					<c:if test="${credenzialiErrate}">
										<div class="container">
											<div class="alert alert-warning">Nome o password errati!</div>
										</div>
					</c:if>
									<input type="submit" value="Login" class="btn btn-success">
								</div>
							</form>
						</div>
					</div>
</c:if>

<c:if test="${loggato}">
					<div class="col-sm-2">
						<div class="jumbotron" id="log">
							<img src="images/profilo.png" class="img-circle" alt="immagine del profilo" style="width: 50%;">
							<h3>${nome}</h3>
						</div>
					</div>
					<div class="col-sm-10">
						<div class="jumbotron" id="description">
						
							<button id="expand_form" type="button" class="btn btn-default btn-xs"><span class="glyphicon glyphicon-plus"></span></button> crea evento<br>
								<div class="jumbotron" id="eventForm">
									<form method="post" action="gestioneeventi">
										<div class="form-group">
											<label for="titolo">Titolo:</label><input name="titolo" id="idTitolo" type="text" class="form-control" />
										</div>
										<div class="form-group" id = "chooselocation">
									<!-- scelta dinamica dei luoghi in base all'utente -->
										</div>						

										<div class="form-group">
											<select id="genere" name = "genere" class="btn btn-default">
												<option value = "">Genere</option>
												<option value = "arte">Arte</option>
												<option value = "cultura">Cultura</option>
												<option value = "gastronomia">Gastronomia</option>
												<option value = "musica">Musica</option>
												<option value = "sport">Sport</option>
											</select>
										</div>
										<div class="form-group">
											<label for="descrizione">Descrizione:</label> <input name="descrizione" id="idDescrizione" type="text" class="form-control" />
										</div>
										<div class="form-group">
											<label for="data">Data svolgimento:</label> <input name="data" type="datetime-local" />
										</div>
										<div class="form-group">
											<label for="fileupload">allega locandina</label> <input type="file" name="fileupload" id="fileupload">
										</div><br>
										<div class="form-group" align="center">
											<input id="pubblicaevento" type="submit" value="Pubblica" class="btn btn-success"/>
										</div>
									</form>
							</div>
							
							<div id="loadbacheca">
	<c:if test="${events}">
									<h2>Eventi in Bacheca</h2>
									<c:forEach var="evento" items="${eventi}">
										<h3>${evento.descrizione}</h3>
									</c:forEach>
	</c:if>
	<c:if test="${not events}">
									<h2>Nessun evento da visualizzare</h2>
	</c:if>
							</div>
						</div>
					</div>
			</div>
		</div>
</c:if>
	</header>

	<footer> Sito web sviluppato dagli studenti Garasto Christian
		e Ventura Paolo </footer>

</body>
</html>