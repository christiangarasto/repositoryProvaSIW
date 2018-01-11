<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head lang="it">

<title>Calabria E20</title>
<meta charset="utf-8">

<link href="css/getioneProfilo.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">
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
								<li><a href="homepage.jsp">Home</a></li>
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
										height="30"><b class="caret"></b></a> <c:if
										test="${loggato}">
										<ul class="dropdown-menu">
											<li><p class="bg-success">${username}</p></li>
											<li class="active"><a href="gestioneProfilo.jsp">Profilo</a></li>
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
			<h1>Gestione Profilo</h1>
			<hr>
			<div class="row">
				<div class="col-md-9 personal-info">
					<form class="form-horizontal" method="post" action="modificaprofilo">
						<div class="form-group">
							<label class="col-lg-3 control-label">Nome:</label>
							<div class="col-lg-8">
								<input class="form-control" name="input_nome" value="${nome}" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Partita Iva:</label>
							<div class="col-lg-8">
								<input class="form-control" name="input_piva" value="${piva}" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Email:</label>
							<div class="col-md-8">
								<input class="form-control" name="input_email" value="${email}" type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Password:</label>
							<div class="col-md-8">
								<input class="form-control" name="input_password" value="${password}" type="password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Conferma password:</label>
							<div class="col-md-8">
								<input class="form-control" name="input_confermapassword" value="${password}" type="password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-8">
								<input class="btn btn-success" type="submit" value="Salva cambiamenti">
								<input class="btn btn-default" value="Annulla" type="reset">
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
		<hr>

		<!--  		
		<div class="container">
			<div class="row">

				<div class="col-xs-4 col-sm-4 col-md-4 col-lg-4">
					<section class="row" class="moduloRegistrazione">
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
								<label for="email">E-mail:</label><input name="email"
									id="idEmail" type="text" class="form-control" />
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
								<input name="validaDati" type="button" value="Valida Dati"
									class="btn btn-warning" onclick="valida()" />
							</div>
						</form>
					</section>
				</div>


				<div class="col-xs-8 col-sm-8 col-md-8 col-lg-8"></div>

			</div>
		</div>
		<div class="form-group">
			<input name="validaDati" type="button" value="Salva modifiche"
				class="btn btn-success" onclick="valida()" />
		</div>
		-->
</body>
</html>