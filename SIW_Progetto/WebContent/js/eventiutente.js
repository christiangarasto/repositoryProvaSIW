$(window).on('load', function()
{
	$.ajax({
    	type : "GET",
    	url  : "gestioneeventi",
    	success : function(eventi)
    	{
    		eventi = JSON.parse(eventi);
    		var txt = "";
    		var e = 0;
    		for(e in eventi)
    		{
    			txt += "<div class=\"row\">";
				txt += "<div class=\"col-xs-4 col-sm-4 col-md-4 col-lg-4\">";
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
				txt += "</div><br>";
    		}
       		$("#mieieventi").html(txt);    		
    	}
    });
	
});