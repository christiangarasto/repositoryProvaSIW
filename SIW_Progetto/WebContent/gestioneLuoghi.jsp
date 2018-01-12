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
<script src="js/creazioneluogo.js"></script>

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
		
		<ul class="nav nav-tabs">
			<li><a href="gestioneProfilo.jsp">Gestione Profilo</a></li>
			<li class="active"><a href="#">Gestione Luoghi</a></li>
		</ul>

		<div class="container">
			<h1>Gestione Luoghi</h1>
			<hr>

			<button type="button" class="btn btn-info btn-md" data-toggle="modal"
				data-target="#nuovoLuogo" data-backdrop="static">Aggiungi
				nuovo luogo</button>
			<div class="modal fade" id="nuovoLuogo" role="dialog">
				<div class="modal-dialog">

					<!-- Modal content-->
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close aggiungiLuogoButton"
								data-dismiss="modal">×</button>
							<h4 class="modal-title">Aggiungi nuovo luogo</h4>
						</div>
						<div class="modal-body">
							<p>Aggiungi le informazioni per aggiungere un nuovo luogo.</p>
						</div>
						<div class="modal-footer">
							<div class="col-md-9 personal-info">
								<form class="form-horizontal" method="post"
									action="aggiunginuovoluogo">
									<br>
									<div class="form-group">
										<label class="col-lg-4 control-label">Nome luogo:</label>
										<div class="col-lg-8">
											<input class="form-control" id="nomeLuogo"
												name="input_nomeluogo" type="text">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">Provincia:</label>
										<div class="col-md-8">
											<input class="form-control" id="provincia"
												name="input_provincia" type="text">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">Comune:</label>
										<div class="col-md-8">
											<input class="form-control" id="comune" name="input_comune"
												type="text">
										</div>
									</div>
									<div class="form-group">
										<label class="col-md-4 control-label">Indirizzo:</label>
										<div class="col-md-8">
											<input class="form-control" id="indirizzo"
												name="input_indirizzo" type="text">
										</div>
									</div>
								</form>
							</div>

							<hr>


						</div>
						<div class="modal-footer">
							
<!-- 							<c:if test="${luogoCreato}">
								<div class="alert alert-success">Luogo salvato con successo!</div>
							</c:if>
							<c:if test="${luogoEsistente}">
								<div class="alert alert-warning">Luogo già presente!</div>
							</c:if>
 -->
 
 							<div id="salvataggioLuogoRisposta"></div>
							
							<form>
								<button type="button" class="btn btn-success" id="salvaLuogo">Salva</button>
								<button type="button" class="btn btn-info aggiungiLuogoButton"
									data-dismiss="modal">Annulla</button>
							</form>
						</div>
					</div>

				</div>
			</div>


			<!-- 
						<div class="modal-footer">
							<form action="aggiunginuovoluogo" method="post">
								<button type="submit" class="btn btn-success">Salva</button>
								<button type="button" class="btn btn-info" data-dismiss="modal">Annulla</button>
							</form>
						</div>
 -->



			<div class="row">
				<div class="col-md-9 personal-info">
					<c:forEach items="${luoghi}" var="luogo">
						<div class="form-group">
							<label class="col-lg-3 control-label">Nome: ${luogo.nome}</label><br>
							<label class="col-lg-3 control-label">Titolare:
								${luogo.titolare}</label><br> <label class="col-lg-3 control-label">Provincia:
								${luogo.provincia}</label><br> <label
								class="col-lg-3 control-label">Comune: ${luogo.comune}</label><br>
							<label class="col-lg-3 control-label">Indirizzo:
								${luogo.indirizzo}</label><br>
						</div>
					</c:forEach>
				</div>
			</div>
		</div>
		<hr>
</body>
</html>