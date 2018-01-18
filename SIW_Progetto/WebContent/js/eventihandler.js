$(window).on('load', function()
{
	$.ajax({
		type : "GET",
		url  : "bachecavisitatori",
    	success : function(result)
    	{
    		result = JSON.parse(result);
    		var i = 0;
    		var txt = "";
    		txt += "<div>";
    		for( i in result )
    		{
    			txt += "<h2>/" + result[i].titolo + "</h2>" ;
    			txt += "<h3>/" + result[i].descrizione + "</h3>" ;
    		}
    		txt += "</div>";
    		$("#show_all_events").html(txt);
    	}
	});
});