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
				success: function(txt){
					
					$("#show_all_events").html(txt);
				
				}
			});
	});
});
		