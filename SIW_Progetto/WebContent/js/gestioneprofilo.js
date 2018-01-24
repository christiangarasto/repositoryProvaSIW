function rimuoviProfilo(){
	
	if(confirm("Vuoi davvero eliminare il tuo account dalla piattaforma?\nCiò comporterà la perdita di tutti i dati, inclusi i luoghi registrati e gli eventi creati!")){
		$.ajax({
			type: "GET",
			url: "rimuoviprofilo",
			success: function(){
				var r = "homepage";
				$.ajax({
					type: "GET",
					url: "redirect",
					data: {
						r:r
					}					
				});
			}
		});
	}
	
	
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
			alert("Tutti i campi devono essere riempiti!");
			controllo = false;
		} else {
			
			if(!/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/.test(password)){
				alert("La password deve contenere almeno 8 caratteri di cui almeno una lettera ed un numero.");
				controllo = false;
			}else if (password != conferma) {
				alert("Il campo 'password' e 'conferma password' non coincidono");
				controllo = false;
			}
			
			/** ***Controllo pIva****** */
			if( ! /^[0-9]{11}$/.test(piva) ){
				alert("La partita IVA deve contenere 11 cifre.");
				controllo = false;
			}
			
			// ///////////////////////////////////////////////////////////////////////Per
			// l'inserimento di partite ive ci limitiamo a controllare che sia
			// composta da 11 caratteri
			// var s = 0;
			// for( i = 0; i <= 9; i += 2 )
			// s += piva.charCodeAt(i) - '0'.charCodeAt(0);
			//					
			// for(var i = 1; i <= 9; i += 2 ){
			// var c = 2*( piva.charCodeAt(i) - '0'.charCodeAt(0) );
			// if( c > 9 ) c = c - 9;
			// s += c;
			// }
			//					
			// var atteso = ( 10 - s%10 )%10;
			// if( atteso != piva.charCodeAt(10) - '0'.charCodeAt(0) ){
			// alert("La partita IVA non è valida:\nil codice di controllo non
			// corrisponde.\n");
			// controllo = false;
			// }
			// ///////////////////////////////////////////////////////////////////////
			/** ************************** */
			
			/** ******Controllo validità email********* */
			var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			
			if(!re.test(email.toLowerCase())){
				alert("Email non valida");
				controllo = false;
			}
			/** ************************************** */
			
		}
		return controllo;
		
	}

	function registraUtente(event) {
		if (!valida()){
			event.preventDefault();
		}
	}