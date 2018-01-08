function valida() {
	
	console.log("valida");
	
	var controllo = true;
	
	var nome = $("#idNome").val();
	var cognome = $("#idCognome").val();
	var piva = $("#idPIva").val();
	var email = $("#idEmail").val();
	var password = $("#idPassword").val();
	var conferma = $("#idConferma").val();

	if(nome.length == 0 || cognome.length == 0 || piva.length == 0 || email.length == 0 || password.length == 0 || conferma.length == 0)
	{
		alert("tutti i campi devono essere riempiti!");
		controllo = false;
	}
	else
	{
		if(password != conferma)
		{
			alert("il campo 'password' e 'conferma password' non coincidono");
			controllo = false;
		}
		if(piva.length != 11 && controllo)
		{
			alert("partita Iva non valida");
			controllo = false;
		}
		
		if(controllo)
		{
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

/*
$("#inviaDati").on(function(e){
	if(valida()){
		console.log("registra");
	}else{
		alert("Errore nella validazione");
		e.preventDefault();
	}
	
});
*/

function registraUtente(event){
	alert("saluti");
	
	if(valida()){
		console.log("registra");
	}else{
		event.preventDefault();
		alert("Errore nella validazione");
	}
}
