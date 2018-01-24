function rimuoviLuoghi() {

	var luoghiDaEliminare = [];

	$(".luogoDaEliminare").each(function() {
		if ($(this).is(":checked")) {
			var luogo = $(this).val();
			luoghiDaEliminare.push(luogo);
		}
	});
	alert(luoghiDaEliminare);

	$
			.ajax({
				type : "POST",
				url : "rimuoviluoghi",
				datatype : "json",
				data : JSON.stringify(luoghiDaEliminare),
				success : function(r) {
					alert("successo eliminazione");
					var luoghi = JSON.parse(r);

					var txt = "";

					for (var i = 0; i < luoghi.length; i++) {
						txt += "<tr>";
						txt += "<td><div class=\"checkbox\"><label><input class=\"luogoDaEliminare\" type=\"checkbox\" value=\"";
						txt += luoghi[i].codice;
						txt += "\"></label></div></td><td>";
						txt += luoghi[i].nome;
						txt += "</td><td>";
						txt += luoghi[i].provincia;
						txt += "</td><td>";
						txt += luoghi[i].comune;
						txt += "</td><td>";
						txt += luoghi[i].indirizzo;
						txt += "</td>";
						txt += "</tr>";
					}
					
					$("#elenco").html(txt);

				}
			});

}

function valida() {

	var controllo = true;

	var nome = $("#idNome").val();
	var cognome = $("#idCognome").val();
	var piva = $("#idPIva").val();
	var email = $("#idEmail").val();
	var password = $("#idPassword").val();
	var conferma = $("#idConferma").val();

	if (nome.length == 0 || cognome.length == 0 || piva.length == 0
			|| email.length == 0 || password.length == 0
			|| conferma.length == 0) {
		alert("tutti i campi devono essere riempiti!");
		controllo = false;
	} else {
		if (password != conferma) {
			alert("il campo 'password' e 'conferma password' non coincidono");
			controllo = false;
		}
		if (piva.length != 11 && controllo) {
			alert("partita Iva non valida");
			controllo = false;
		}

		if (controllo) {
			console.log("cerco nel database");
			/*
			 * 
			 * 
			 * 
			 */
		}
	}

	return controllo;

}

function registraUtente(event) {

	if (valida()) {
		console.log("registra");
	} else {
		event.preventDefault();
		alert("Errore nella validazione");
	}
}