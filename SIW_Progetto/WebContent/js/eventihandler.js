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
    		var cont = 0;
    		for( e in eventi )
    		{
    			if(cont == 0)
    			{
    				txt += "<div class=\"row\">"
    			}
    			txt += "<div class=\"col-xs-4 col-sm-4 col-md-4 col-lg-4\">";
    			txt += "<div class=\"panel\">";
    			txt += "<div id=\"eventoinbacheca\" class=\"panel-heading\"><big>"+ eventi[e].titolo + "</big></div>";
    			txt += "<div class=\"panel-footer\">" + eventi[e].descrizione + "</div>";
    			txt += "<div class=\"panel-footer\"> <strong>Data :</strong> " + eventi[e].data + " <br><strong>Ora</strong> : " + eventi[e].ora + "</div></div></div>";
    			if(cont == 3)
    			{
    				txt += "</div><br>";
    				cont = 0;
    			}
    			else
    			{
    				cont++;
    			}
    		}
    		$("#show_all_events").html(txt);
    	}
	});
});