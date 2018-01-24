$(window).on('load', function()
{
	$.ajax({
		type : "GET",
		url  : "bachecavisitatori",
    	success : function(eventi)
    	{
    		$("#show_all_events").html(eventi);
    	}
	});
	
	
	$("#cercaPerFiltroButton").on('click', function(){
		
		var sceltaFiltro = $("#filtro").val();
		var valoreFiltro = $("#valoreFiltro").val();
					
			$.ajax({
				type: "GET",
				url: "eventifiltrati",
				data: {
					sceltaFiltro:sceltaFiltro,
					valoreFiltro:valoreFiltro
				},
				success: function(r){
					var eventi = JSON.parse(r);
					var txt = "";
					var cont = 0; // era 1?
					txt += "<div class=\"row\">";
					for (var i in eventi) 
					{
					    txt += "<div class=\"col-sm-4\">";
						txt += "<div class=\"panel\">";
						txt += 		"<div id=\"eventoinbacheca\" class=\"panel-heading\">" +
									"<big><strong>" + eventi[i].titolo + "</strong></big></div>";
						txt += 			"<div class=\"panel-body\">"  + eventi[i].descrizione + "</div>";
						txt += 		"<div class=\"panel-footer\"> " +
									"<strong>Data :</strong> "+ eventi[i].data + " <br>" +
									"<strong>Ora</strong> : " + eventi[i].ora + " <br>" +
									 							eventi[i].luogo.comune +
									 							" (" + eventi[i].luogo.provincia + ")" +
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
					
					$("#show_all_events").html(txt);
				
				}
			});
	});
});
		