<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head lang="it">

<title>Calabria E20</title>
<meta charset="utf-8">

<link href="css/common.css?100" rel="stylesheet">
<link href="css/background.css" rel="stylesheet">
<link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
<script src="jquery/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

<script src="js/creazioneevento.js"></script>
<!-- <script src="js/eventiutente.js"></script>  -->

</head>

<body>
	<header>

		<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container-fluid">
				<div class="navbar-header">
			      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
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
								<li class="active"><a href="">Home</a></li>
								<li><a href="eventi.jsp">Eventi </a></li>
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
					<div class="col-sm-8">
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
											<textarea rows="3" name="descrizione" id="idDescrizione" class="form-control" placeholder="inserisci qui una breve descrizione..."></textarea>
										</div>
										<div class="form-inline">
												<label for="data">Data svolgimento:</label> <input name="data" type="date"/>
												<label for="orario">Ora:</label> <input name="orario" type="time"/>
										</div><br>
										<div class="form-group">										
											<label for="payment">Tipo di partecipazione:</label>
												<select id="ticket" name = "ticket" class="btn btn-default">
													<option value = "free">Free</option>
													<option value = "pagamento">Pagamento</option>
												</select>
										</div>
										<div id = "hideticketparameters" class = "form-inline">
										<!-- funzione jQuery per contenuto dinamico -->
										</div><br>
										<div class="form-group">
											<label for="fileupload">allega locandina</label> <input type="file" name="fileupload" id="fileupload">
										</div><br>
										<div class="form-group" align="center">
											<input id="pubblicaevento" type="submit" value="Pubblica" class="btn btn-success"/>
										</div>
									</form>
							</div>
							
							<div id="loadbacheca" class="container">
	<c:if test="${events}">
								<h2>I tuoi Eventi in Bacheca</h2><hr>
								<div id = "mieieventi" class = "container">
									<c:forEach var = "evento" items = "${eventi}">
    									<div class="row">
										<div class="container">
										<div class="panel">
										<div id="eventoinbacheca" class="panel-heading">
											<strong>${evento.titolo}</strong>
											<div class="btn-group pull-right">
											<a href="#" class="btn btn-warning btn-sm"> Modifica</a>
											<a href="#" class="btn btn-danger btn-sm"> Cancella</a>
											</div>
										</div>
											<div class="panel-body">${evento.descrizione}</div>
											<div class="panel-footer">
											<strong>Data :</strong>${evento.data}<br>
											<strong>Ora</strong>${evento.ora}<br>
											${evento.luogo.comune} ( ${evento.luogo.provincia} )
											</div>
										</div>
										</div>
										</div><br>									
									</c:forEach>
								</div>
	</c:if>
	<c:if test="${not events}">
								<h2>Nessun evento da visualizzare</h2>
	</c:if>
							</div>
						</div>
					</div>
</c:if>
			</div>
		</div>
	</header>
	<footer> Sito web sviluppato dagli studenti Garasto Christian
		e Ventura Paolo 
	</footer>

</body>
</html>