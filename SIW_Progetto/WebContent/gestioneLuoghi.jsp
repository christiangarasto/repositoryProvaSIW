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
<script src="js/gestioneluoghi.js"></script>

<link href="bootstrap-3.3.7-dist/css/bootstrap.css" rel="stylesheet">
<script src="jquery/jquery-3.2.1.min.js"></script>
<script src="bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="js/creazioneluogo.js"></script>


<style>
#map {
	height: 400px;
	width: 100%;
}
</style>
<script>
	function initMap() {

		var rende = new google.maps.LatLng(39.333765, 16.184529);
		var map = new google.maps.Map(document.getElementById('map'), {
			zoom : 15,
			center : rende
		});
		var marker = new google.maps.Marker({
			position : rende,
			map : map,
			draggable : true
		});
		var searchBox = new google.maps.places.SearchBox(document
				.getElementById('mapsearch'));

		google.maps.event.addListener(searchBox, 'places_changed', function() {
			alert(searchBox.getPlaces());
			var places = searchBox.getPlaces();

			var bounds = new google.maps.LatLngBounds();
			var i, place;
			for (i = 0; place = places[i]; i++) {
				bounds.extend(place.geometry.location);
				marker.setPosition(place.geometry.location);
			}

			google.maps.event.trigger(maCarte, 'resize');
			map.finBounds(bounds);
			map.setZoom(15);
		});
	}
</script>
<script async defer
	src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBYs_o9DSjxlPRQ5v4BYq3gnZ-2UfIADu0&callback=initMap&libraries=places,geometry">
	
</script>

</head>

<body>
	<header>

		<nav class="navbar navbar-inverse navbar-static-top">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle" data-toggle="collapse"
						data-target="#myNavbar">
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
						<li><a href="homepage.jsp">Home</a></li>
						<li><a href="eventi.jsp">Eventi </a></li>

						<c:if test="${not loggato}">
							<li><a href="iscriviutente.jsp">Diventa uno di noi</a></li>
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
									<li><a href="gestioneProfilo.jsp">Profilo</a></li>
									<li><a href="effettualogout">Logout</a></li>
								</ul></li>
						</ul>
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
			<label class="col-lg-4 control-label">Nome luogo:</label>
			<div class="col-lg-8">
				<input class="form-control" id="nomeLuogo" name="input_nomeluogo"
					type="text">
			</div>
			<label class="col-lg-4 control-label">Indirizzo:</label>
			<div class="col-lg-8">
				<input type="text" placeholder="Inserisci l'indirizzo del luogo.."
					id="mapsearch" class="form-control">
			</div>


			<div id="map"></div>

			<br>

			<button type="button" class="btn btn-info btn-md" id="nuovoLuogo">Aggiungi
				nuovo luogo</button>

			<hr>
			<br>
			<div class="container">
				<table class="table table-striped">
					<thead>
						<tr>
							<th></th>
							<th>Nome</th>
							<th>Provincia</th>
							<th>Comune</th>
							<th>Indirizzo</th>
							<th>
								<button type="button" class="btn btn-danger"
									onclick="rimuoviLuoghi()">Rimuovi</button>
							</th>
						</tr>
					</thead>
					<tbody id="elenco">
						<c:forEach items="${luoghi}" var="luogo">

							<tr>
								<td><div class="checkbox">
										<label><input class="luogoDaEliminare" type="checkbox"
											value="${luogo.codice}"></label>
									</div></td>
								<td>${luogo.nome}</td>
								<td>${luogo.provincia}</td>
								<td>${luogo.comune}</td>
								<td>${luogo.indirizzo}</td>
							</tr>

						</c:forEach>
					</tbody>
					<tfoot>
						<tr class="active">
							<td></td>
							<td><a href="#">Ordina per Nome</a></td>
							<td><a href="#">Ordina per Provincia</a></td>
							<td><a href="#">Ordina per Comune</a></td>
							<td><a href="#">Ordina per Indirizzo</a></td>
						</tr>
					</tfoot>
				</table>
			</div>
		</div>

	</header>
</body>
</html>