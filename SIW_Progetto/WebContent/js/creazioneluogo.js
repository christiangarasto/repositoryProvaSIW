$(window).on('load', function() {
	
					$.ajax({
						type : "GET",
						url : "gestioneluoghi",
						success : function() {
							alert("olè");
						}											
					}); 
	

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

										if (nomeLuogo.length == 0
												|| provincia.length == 0
												|| comune.length == 0
												|| indirizzo.length == 0) {

											alert("Compilare tutti i campi richiesti!");
										} else {
											$('#nuovoLuogo').modal('hide');
											/*
											$.ajax({
												type : "POST",
												url : "gestioneluoghi",
												data : {
													nomeLuogoInput : nomeLuogo,
													provinciaInput : provincia,
													comuneInput : comune,
													indirizzoInput : indirizzo
												},
												success : function(asd) {
													    $('#nuovoLuogo').modal('hide');
													    
													    alert(luoghi);
													    
													    //$("#luoghi").html(asd);
												}											
											}); 
											*/
										}

									});

				});