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
    		txt += "<div class=\"jumbotron\" id=\"bacheca\">";
    		for( e in eventi )
    		{
    			txt += "<h3>" + eventi[e].titolo + "</h3>" ;
    			txt += "<h4>" + eventi[e].descrizione + "</h4>" ;
    		}
    		txt += "</div>";
    		$("#show_all_events").html(txt);
    	}
	});
});