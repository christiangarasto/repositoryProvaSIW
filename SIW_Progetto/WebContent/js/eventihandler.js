$(window).on('load', function()
{
	$.ajax({
		type : "GET",
		url  : "bachecavisitatori",
    	success : function(eventi)
    	{
    		eventi = JSON.parse(eventi);
    		var e = 0;
    		var txt = "";
    		var cont = 1;
    		
			txt += "<div class=\"row\">";
    		for( e in eventi )
    		{
    			txt += "<div class=\"col-xs-5 col-sm-5 col-md-5 col-lg-5\">";
    			txt += "<div class=\"panel\">";
    			txt += "<div id=\"eventoinbacheca\" class=\"panel-heading\">" +
    								   "<big><strong>" + eventi[e].titolo + "</strong></big></div>";
    			txt += "<div class=\"panel-body\">"  + eventi[e].descrizione + "</div>";
    			txt += "<div class=\"panel-footer\"> " +
							"<strong>Data :</strong> " + eventi[e].data + " <br>" +
							 "<strong>Ora</strong> : " + eventi[e].ora + " <br>" +
							 							 eventi[e].luogo.comune + " (" + eventi[e].luogo.provincia + ")" +
					   "</div>";
    			txt += "</div>" +
    				   "</div>";
    			if(cont == 3)
    			{
    				txt += "</div><br>";
    				txt += "<div class=\"row\">";
    				cont = 0;
    			}
    			cont++;
    		}
    		$("#show_all_events").html(txt);
    	}
	});
	
});