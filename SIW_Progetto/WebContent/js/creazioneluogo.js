$(window).on('load', function() {
					$("#nuovoLuogo").on('click', function() {
										var nomeLuogo = $("#nomeLuogo").val();

										var ind = $("#mapsearch").val();
										var nVirgole = 0;

										var indirizzo = "";
										var comune = "";
										var provincia = "";

										var i = 0;

										for (i; i < ind.length; i++) {

											if (ind.charAt(i) == ',') {
												nVirgole++;
											}

											if (nVirgole < 1) {
												indirizzo += ind.charAt(i);
											} else if (nVirgole < 2) {
												comune += ind.charAt(i);

											} else if (nVirgole < 3) {
												provincia += ind.charAt(i);
											}
										}

										comune = comune.substring(2,
												comune.length);
										provincia = provincia.substring(2,
												provincia.length);

										if (nomeLuogo.length == 0
												|| provincia.length == 0
												|| comune.length == 0
												|| indirizzo.length == 0) {

											alert("Compilare tutti i campi richiesti!");
										} else if (provincia.length != 2) {

											alert("Indirizzo inserito non valido!");

										} else {

											var luogoN = {
												nomeLuogoInput : nomeLuogo,
												provinciaInput : provincia,
												comuneInput : comune,
												indirizzoInput : indirizzo
											};

											$.ajax({
														type : "POST",
														url : "gestioneluoghi",
														datatype : "json",
														data : JSON.stringify(luogoN),
														success : function(r) {

															var luogo = JSON.parse(r);

															var txt = "<tr>";
															txt += "<td><div class=\"checkbox\"><label><input class=\"luogoDaEliminare\" type=\"checkbox\" value=\"";
															txt += luogo.codice;
															txt += "\"></label></div></td><td>";
															txt += luogo.nome;
															txt += "</td><td>";
															txt += luogo.provincia;
															txt += "</td><td>";
															txt += luogo.comune;
															txt += "</td><td>";
															txt += luogo.indirizzo;
															txt += "</td>";
															txt += "</tr>";

															$("#elenco")
																	.append(txt);


															$("#nomeLuogo").val('');
															$("#mapsearch").val('');
														}
													});
										}

									});

				});