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