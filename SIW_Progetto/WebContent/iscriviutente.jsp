<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

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

<script src="js/utility.js"></script>
<script src="js/gestioneprofilo.js"></script>

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
								<li><a href="redirect?r=homepage">Home</a></li>
								<li><a href="redirect?r=eventi">Eventi </a></li>
								<li class="active"><a href="">Diventa uno di noi</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</nav>
		
		<h1>Diventa membro di Calabria E20</h1>
		<h3>Compila il form qui di seguito per entrare a far parte della
			community e sponsorizzare i tuoi eventi</h3>

	</header>
	<section class="row" class="moduloRegistrazione">
		<div class="col-sm-3">
			<form method="post" action="iscriviutente">
				<div class="form-group">
					<label for="nome">Nome:</label><input name="nome" id="idNome"
						type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label for="cognome">Cognome:</label><input name="cognome"
						id="idCognome" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label for="piva">Partita Iva:</label><input name="piva"
						id="idPIva" type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label for="email">E-mail:</label><input name="email" id="idEmail"
						type="text" class="form-control" />
				</div>
				<div class="form-group">
					<label for="password">Password:</label><input name="password"
						id="idPassword" type="password" class="form-control" />
				</div>
				<div class="form-group">
					<label for="conferma">Conferma Password:</label><input
						name="conferma" id="idConferma" type="password"
						class="form-control" />
				</div>
				<div class="form-group">
					<input name="validaDati" type="button" value="Valida Dati" class="btn btn-warning" onclick="valida()" /> 
					<input name="resetDati" type="reset" value="Reset Dati" class="btn btn-danger" /> 
					<input id="inviaDati" type="submit" value="Invia Dati" class="btn btn-success" onclick="registraUtente(event);" />
				</div>
			</form>
		</div>
	</section>

</body>
</html>