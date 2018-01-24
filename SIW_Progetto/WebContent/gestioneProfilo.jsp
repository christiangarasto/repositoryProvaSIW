<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head lang="it">

<title>Calabria E20</title>
<meta charset="utf-8">

<link href="css/getioneProfilo.css" rel="stylesheet">
<link href="css/common.css" rel="stylesheet">

<script src="js/utility.js"></script>

<link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
<script src="jquery/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>

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
								<li><a href="redirect?r=homepage">Home</a></li>
								<li><a href="redirect?r=eventi">Eventi </a></li>
<c:if test="${not loggato}">							
								<li><a href="redirect?r=iscriviutente">Diventa uno di noi</a></li>
</c:if>
							</ul>
<c:if test="${loggato}">

					<ul class="nav navbar-nav navbar-right">
						<li class="dropdown"><a href="" class="dropdown-toggle" data-toggle="dropdown">
						<span class="glyphicon glyphicon-user"></span>	${nome} <b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><p class="bg-primary">${username}</p></li>
								<li><a href="redirect?r=gestioneProfilo">Profilo</a></li>
								<li><a href="effettualogout">Logout</a></li>
							</ul>
						</li>
					</ul>
</c:if>
				</div>
			</div>
		</nav>

		<ul class="nav nav-tabs">
			<li class="active"><a href="">Gestione Profilo</a></li>
			<li><a href="gestioneluoghi">Gestione Luoghi</a></li>
		</ul>

		<div class="container">
			<h1>Gestione Profilo</h1>
			<hr>
			<div class="row">
				<div class="col-md-9 personal-info">
					<form class="form-horizontal" method="post"
						action="modificaprofilo">
						<div class="form-group">
							<label class="col-lg-3 control-label">Nome:</label>
							<div class="col-lg-8">
								<input class="form-control" name="input_nome" value="${nome}"
									type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-lg-3 control-label">Partita Iva:</label>
							<div class="col-lg-8">
								<input class="form-control" name="input_piva" value="${piva}"
									type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Email:</label>
							<div class="col-md-8">
								<input class="form-control" name="input_email" value="${email}"
									type="text">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Password:</label>
							<div class="col-md-8">
								<input class="form-control" name="input_password"
									value="${password}" type="password">
							</div>
						</div>
						<div class="form-group">
							<label class="col-md-3 control-label">Conferma password:</label>
							<div class="col-md-8">
								<input class="form-control" name="input_confermapassword"
									value="${password}" type="password">
							</div>
						</div>

						<div class="form-group">
							<label class="col-md-3 control-label"></label>
							<div class="col-md-8">
								<input class="btn btn-success" type="submit"
									value="Salva cambiamenti"> <input
									class="btn btn-default" value="Annulla" type="reset">
							</div>
						</div>

						<c:if test="${profiloModificato}">
							<div class="alert alert-success">Tutte le modifiche sono
								state salvate con successo!</div>
						</c:if>
						<c:if test="${not profiloModificato}">
							<div class="alert alert-warning">
								<strong>Attenzione: </strong> Verificare che tutti i campi siano
								correttamente inseriti.
							</div>
						</c:if>
					</form>
				</div>
			</div>
		</div>
		<hr>
	</header>
</body>
</html>