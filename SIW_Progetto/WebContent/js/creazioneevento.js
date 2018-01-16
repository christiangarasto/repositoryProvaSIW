$(window).on('load', function()
{
		
		$.ajax({
			type : "GET",
			url  : "gestioneeventi",
			success : function(){
				$("#evento").html();
			}
		});
		
		$("#expand_form").on('click', function()
		{
		    var x = document.getElementById("eventForm");
		    if(x.style.display = "none")
		    {
		    	x.style.display = "block";
		    }
		    else
		    {
		    	x.style.display = "none";
		    }
		});
		
		$("#luoghibutton").on('click', function()
		{
			$.ajax({
				type : "GET",
				url  : "gestioneluoghi",
				success : function(){
					$("#locations").html();//?????????????????????????????????????????????????????????????????
				}
			});
		});
});