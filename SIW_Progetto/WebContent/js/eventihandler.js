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
    		
			txt += "<div class=\"row\">"
    		for( e in eventi )
    		{
    			txt += "<div class=\"col-xs-4 col-sm-4 col-md-4 col-lg-4\">";
    			txt += "<div class=\"panel\">";
    			txt += "<div id=\"eventoinbacheca\" class=\"panel-heading\"><big>"+ eventi[e].titolo + "</big></div>";
    			txt += "<div class=\"panel-footer\">" + eventi[e].descrizione + "</div>";
    			txt += "<div class=\"panel-footer\"> " +
							"<strong>Data :</strong> " + eventi[e].data + " <br>" +
							"<strong>Ora</strong> : " + eventi[e].ora + 
					   "</div>";
    			txt += "</div>" +
    				   "</div>";
    			if(cont == 3)
    			{
    				txt += "</div><br>";
    				txt += "<div class=\"row\">"
    				cont = 0;
    			}
    			cont++;
    		}
    		$("#show_all_events").html(txt);
    	}
	});
});