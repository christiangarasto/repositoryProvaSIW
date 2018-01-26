$(window).on("load", function(){

	$("#acquista").on("click", function(){
		
		var intestatario = $("#intestatarioB").val();
		var cc = $("#cartaB").val();
		var scadenza = $("#scadenzaCCB").val();
		var cvv = $("#cvvB").val();
		
		if(intestatario.length == 16){
			
			if(cc.length == 16){
				
				if(scadenza.length == 5){
					
					if(cvv.length == 3){
						$.ajax({
							
							type: "POST",
							url: "acquistabiglietto",
							data: {
								intestatario : intestatario,
								cc : cc,
								scadenza : scadenza,
								cvv : cvv,
								globale : globale							},
							success: function(r){
								var result = JSON.parse(r);								
									
								$("#messaggioAcquisto").html(result.txt);
								$("#cambioBottone").html(result.bottone);
								
							}
							
						});
						
						
						
					}else alert("CVV non valido!");
					
				}else alert("Scadenza non valida!");
				
			}else alert("Numero carta di credito errato!");
			
		}else alert("Codice fiscale non valido!");
		
		
	});	
	
	

});

var globale;

function venditaTicket()
{
	var cod = $("#bottonePagamento").attr("value");
	globale = cod;
}