$(window).on('load', function(){
	
	$.ajax({
		type: "GET",
		url: "gestioneeventi",
		datatype: "json",
		success: function(eventi){alert("successo");
			var ev = JSON.parse(eventi);
			
	alert("ev: " + ev[0].titolo);
			var i = 0;
	
			var eventiUtente = "";
			//for(i; i < eventi.length; i++){				
			for(i in eventi){	
				
				eventiUtente += "<p>" + ev[i].titolo + "</p>";
				alert("eventiUtente:: " + eventiUtente);
				
				
//				eventiUtente += "<div class=\"row\">";
//					eventiUtente += "<div class=\"container\">";
//						eventiUtente += "<div class=\"panel\">";
//							eventiUtente += "<div id=\"eventoinbacheca\" class=\"panel-heading\"><strong>";
//											eventiUtente += eventi[i].titolo;
//											eventiUtente += "</strong><div class=\"btn-group pull-right\">";
//											eventiUtente += "<a href=\"servletDaCreare!\" class=\"btn btn-warning btn-sm\"> Modifica</a>";
//											eventiUtente += "<a href=\"servletDaCreare!\" class=\"btn btn-danger btn-sm\"> Cancella</a>";
//											eventiUtente += "</div></div>";
//				eventiUtente += "<div class=\"panel-body\">";
//				eventiUtente += eventi[i].descrizione;
//				eventiUtente += "</div><div class=\"panel-footer\">";
//				eventiUtente += "<strong>Data :</strong>";
//				eventiUtente += eventi[i].data;
//				eventiUtente += "<br><strong>Ora : </strong>";
//				eventiUtente += eventi[i].ora;
//				eventiUtente += "<br>";
//				eventiUtente += eventi[i].luogo.comune;
//				eventiUtente += ("( " + eventi[i].luogo.provincia + " )");
//				eventiUtente += "</div></div></div></div><br>\n";							
			}
			
			alert("bashtard::" + eventiUtente);
			$("#mieieventi").html(eventiUtente);			
		}
	});
	
	
});