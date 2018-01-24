$(window).on('load', function(){
	
	$.ajax({
		type: "GET",
		url: "gestioneeventi",
		datatype: "json",
		success: function(eventi){
			var ev = JSON.parse(eventi);
			var cont = 0;

			var txt = "";
			txt += "<div class=\"row\">";
			for(var i = 0; i < ev.length; i++){				
				    txt += "<div class=\"col-sm-4\">";
					txt += "<div class=\"panel\">";
					txt += 		"<div id=\"eventoinbacheca\" class=\"panel-heading\">" +
								"<big><strong>" + ev[i].titolo + "</strong></big></div>";
					txt += 			"<div class=\"panel-body\">"  + ev[i].descrizione + "</div>";
					txt += 		"<div class=\"panel-footer\"> " +
								"<strong>Data :</strong> "+ ev[i].data + " <br>" +
								"<strong>Ora</strong> : " + ev[i].ora + " <br>" +
															ev[i].luogo.comune +
								 							" (" + ev[i].luogo.provincia + ")" +
								"</div>";
					txt += "</div>";
					txt += "</div>";
					if(cont == 3)
					{
						txt += "</div><br>";
						txt += "<div class=\"row\">";
						cont = 0;
					}
					cont++;
			}
			txt += "</div>";				
			$("#mieieventi").html(txt);			
		}
	});
	
	
});