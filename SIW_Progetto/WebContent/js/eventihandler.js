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
    		for( e in eventi )
    		{
    			txt += "<h2>" + eventi[e].titolo + "</h2>" ;
    			txt += "<h3>" + eventi[e].descrizione + "</h3>" ;
    		}
    		$("#show_all_events").html(txt);
    	}
	});
});