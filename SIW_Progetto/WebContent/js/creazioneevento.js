$(window).on('load', function()
{
		$("#payment").change(function(){
			
			var tipo = $('#payment option:selected').attr("value");
			if(tipo == "ticket")
			{
				var txt1 = "";
				txt1 = "<label>Numero ticket:</label> <input class='number' type='text' class='form-control' /> ";
				txt1 += "<label>Prezzo singolo ticket:</label> <input class='price' type='text' class='form-control' /> ";
				
				$("#hideticketparameters").html(txt1);
				
			}
			
		});
		
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
		    if(x.style.display === "block")
		    {
		    	x.style.display = "none";
		    }
		    else
		    {
		    	x.style.display = "block";
		    }

		    $.ajax({
		    	type : "GET",
		    	url  : "gestioneluoghi",
		    	success : function(result)
		    	{
		    		result = JSON.parse(result);
		    		var i = 0;
		    		var txt = "";
		    		txt += "<select id=\"location\" name=\"location\" class=\"selectpicker btn btn-default\"><option value = \"\">Luogo</option>";
		    		for( i in result )
		    		{
		    			txt += "<option value = \"" + result[i].codice + "\">" + result[i].nome + "</option>";
		    		}
		    		txt += "<option class = \"divider\" disabled></option>";
		    		txt += "<option id=\"addlocation\">Aggiungi...</option>";
		    		txt += "</select>";
		    		$("#chooselocation").html(txt);
		    	}
		    });
		    
		});
});
