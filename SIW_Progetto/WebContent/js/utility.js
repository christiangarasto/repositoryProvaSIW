function valida() {
	
	console.log("valida");
	
	var controllo = true;
	
	var nome = $("#nome").val();
	var cognome = $("#cognome").val();
	var piva = $("#piva").val();
	var email = $("#email").val();
	var password = $("#password").val();
	var conferma = $("#conferma").val();

	if(nome.length == 0 || cognome.length == 0 || piva.length == 0 || email.length == 0 || password.length == 0 || conferma.length == 0)
	{
		alert("tutti i campi devono essere riempiti!");
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
		}
		
		if(controllo)
		{
			console.log("cerco nel database");
		}
	}
	
	return controllo;
	
}  


//var nome = $("#nome").val();
//var cognome = $("#cognome").val();
//var piva = $("#piva").val();
//var email = $("#email").val();
//var password = $("#password").val();
//var conferma = $("#conferma").val();


//var utente = new Utente


function registraUtente(){
	
	if(valida()){
	
		console.log("registra");

	}else{
		alert("Errore nella validazione");
	}
}
