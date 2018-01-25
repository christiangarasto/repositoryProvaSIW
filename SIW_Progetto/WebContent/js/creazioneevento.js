$(window).on('load', function()
{	
		$("#ticket").change(function()
		{
			var tipo = $('#ticket option:selected').attr("value");
			var txt = "";
			if(tipo == "pagamento")
			{
				txt += "<div class=\"form-inline\">";
				txt += "<label>Numero ticket:</label> <input name = \"numero\" type=\"text\" class=\"form-control\" /> ";
				txt += "</div><br>";
				txt += "<div class=\"form-inline\">";
				txt += "<label>Prezzo singolo ticket:</label> <input name = \"prezzo\" type='text' class='form-control' /> ";
				txt += "</div>";
				
				$("#hideticketparameters").html(txt);
			}
			else
			{
				$("#hideticketparameters").empty();
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
		    	url  : "dammiluoghi",
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
		    		txt += "</select>";
		    		$("#chooselocation").html(txt);
		    	}
		    });
		});
});

