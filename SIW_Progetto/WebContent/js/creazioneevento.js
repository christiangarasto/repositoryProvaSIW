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
		
//		$("#luoghibutton").on('click', function()
//		{
			$.ajax({
				type : "GET",
				url  : "gestioneluoghi",
				
				success : function(result)
				{
					result = JSON.parse(result);
					var i = 0;
					var txt = "";
					txt += "<select id=\"location\" class=\"selectpicker btn btn-default\"><option>Luogo</option>";
					for( i in result )
					{
						txt += "<option value = \"" + result[i].nome + "\">" + result[i].nome + "</option>";
					}
					txt += "<option class = \"divider\" disabled></option>";
					txt += "<option id=\"addlocation\">Aggiungi...</option>";
					txt += "</select>";
					document.getElementById("chooselocation").innerHTML = txt;
				}
			});
//		});
});