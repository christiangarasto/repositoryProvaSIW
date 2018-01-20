$(window).on('load', function() {

					$(".aggiungiLuogoButton").on('click', function() {
						$("#nomeLuogo").val('');
						$("#provincia").val('');
						$("#comune").val('');
						$("#indirizzo").val('');
					});

					$("#salvaLuogo").on('click',function() {
										var nomeLuogo = $("#nomeLuogo").val();
										var provincia = $("#provincia").val();
										var comune = $("#comune").val();
										var indirizzo = $("#indirizzo").val();

										alert(nomeLuogo + ", " + provincia + ", " + comune + ", " + indirizzo);
										
										if (nomeLuogo.length == 0
												|| provincia.length == 0
												|| comune.length == 0
												|| indirizzo.length == 0) {

											alert("Compilare tutti i campi richiesti!");
										} else {
											alert("saluti");
											
											$('#nuovoLuogo').modal('hide');
											$.ajax({
												type : "POST",
												url : "gestioneluoghi",
												data : {
													nomeLuogoInput : nomeLuogo,
													provinciaInput : provincia,
													comuneInput : comune,
													indirizzoInput : indirizzo
												},
												success : function(risposta) {
													risposta = Json.parse(risposta);
													
													var txt = "<tr>";
														txt += "<td>" + risposta.nome + "</td>";
														txt += "<td>" + risposta.provincia + "</td>";
														txt += "<td>" + risposta.comune + "</td>";
														txt += "<td>" + risposta.indirizzo + "</td>";
														txt += "</tr>";
													
													
													$("#elenco").html(txt);
													
													$("#nomeLuogo").val('');
													$("#provincia").val('');
													$("#comune").val('');
													$("#indirizzo").val('');
													
													
												}											
											}); 
										}

									});

				});