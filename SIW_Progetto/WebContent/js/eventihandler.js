$(window).on('load', function()
{alert("prova");
	$.ajax({
		type : "GET",
		url  : "bachecavisitatori",
    	success : function(eventi)
    	{
    		alert("eventihandler success");
    		$("#show_all_events").html(eventi);
    	}
	});
	
});