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
<script src="js/acquistoBiglietto.js"></script>


</head>

<body>
	<header>

		<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="myNavbar">
						<span class="icon-bar"></span> <span class="icon-bar"></span> <span
							class="icon-bar"></span>
					</button>
					<aside class="pull-left">
						<img src="images/logo.png" height="30">
					</aside>
					<a id="brand" class="navbar-brand">Calabria E20</a>
				</div>
				<div class="collapse navbar-collapse" id="myNavbar">
					<ul class="nav navbar-nav">
						<li><a href="redirect?r=homepage">Home</a></li>
						<li class="active"><a href="">Eventi </a></li>
						<c:if test="${not loggato}">
							<li><a href="redirect?r=iscriviutente">Diventa uno di
									noi</a></li>
						</c:if>
					</ul>
					<c:if test="${loggato}">

						<ul class="nav navbar-nav navbar-right">
							<li class="dropdown"><a href="" class="dropdown-toggle"
								data-toggle="dropdown"> <span
									class="glyphicon glyphicon-user"></span> ${nome} <b
									class="caret"></b></a>
								<ul class="dropdown-menu">
									<li><p class="bg-primary">${username}</p></li>
									<li><a href="redirect?r=gestioneProfilo">Profilo</a></li>
									<li><a href="effettualogout">Logout</a></li>
								</ul></li>
						</ul>
					</c:if>
				</div>
			</div>
		</nav>
	</header>
	<div id="tuttieventititle" class="jumbotron">
		<div class="container text-center">
			<h1>Bacheca Calabria E20</h1>
			<p>Tutti i nostri eventi</p>
		</div>
		<br>
		<div class="form-group col-md-2">
			<input name="valoreFiltro" id="valoreFiltro" type="text"
				class="form-control" />
		</div>


		<button class="btn btn-success" id="cercaPerFiltroButton">Cerca</button>
		<br>
		<div class="form-group">
			<select id="filtro" name="filtro" class="btn btn-default">
				<option value="">Filtra per...</option>
				<option value="luogo">Eventi per luogo</option>
				<option value="genere">Eventi per genere</option>
				<option value="data" disabled>Eventi in data</option>
				<option value="oggi" disabled>Eventi oggi</option>
				<option value="ora" disabled>Eventi per le ore</option>
				<option value="comune">Eventi per comune</option>
				<option value="provincia">Eventi per provincia</option>
				<option value="gratuiti">Eventi gratuiti</option>
				<option value="pagamento">Eventi a pagamento</option>

			</select>
		</div>
	</div>

	<div id="show_all_events" class="container"></div>

	<div class="modal fade" id="acquisto" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Effettua il
						pagamento</h5>
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
				</div>
				<div class="modal-body" id="messaggioAcquisto">
				
					<form id="acquistaBiglietto" method="post"
						action="acquistabiglietto">
						<div class="form-group">
							<label>Codice fiscale: </label> <input type="text"
								id="intestatarioB" name="intestatarioB">
						</div>
						<div class="form-group">
							<label>Carta di credito: </label> <input type="text" id="cartaB"
								name="cartaB">
						</div>
						<div class="form-group">
							<label>Data di scadenza: </label> <input type="text"
								id="scadenzaCCB" name="scadenzaCCB" placeholder="MM/AA">
						</div>
						<div class="form-group">
							<label>CVV: </label> <input type="text" id="cvvB" name="cvvB">
						</div>

					</form>

				</div>
				<div class="modal-footer" id="cambioBottone">
					<button type="button" class="btn btn-secondary"
						data-dismiss="modal">Annulla</button>

					<button id="acquista" class="btn btn-primary">Acquista</button>
				</div>
			</div>
		</div>
	</div>
</body>
</html>