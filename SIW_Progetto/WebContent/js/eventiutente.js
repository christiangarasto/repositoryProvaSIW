$(window).on('load', function(){
	
	$.ajax({
		type: "GET",
		url: "gestioneeventi",
		datatype: "json",
		success: function(eventi){
			var ev = JSON.parse(eventi);
			var cont = 0;			
			var txt = "";
			for(var i = 0; i < ev.length; i++)
			{				
				txt += "<div class=\"row\">";				
				txt += "<div class=\"panel\">";
				txt += 		"<div id=\"eventoinbacheca\" class=\"panel-heading\">" +
							"<big><strong>" + ev[i].titolo + "</strong></big>" +
								"<div class=\"btn-group pull-right\">" +
								"<button id=\"btnmdf\" value=\""+ ev[i].codice +"\"onclick=\"modifica();luoghimodifica();\"  data-toggle=\"modal\" data-target=\"#myModal\" class=\"btn btn-warning btn-sm\">MODIFICA</button>" +
								"<button id=\"btnrmv\" value=\""+ ev[i].codice +"\" onclick=\"rimuovi()\" type=\"button\" class=\"btn btn-danger btn-sm\">RIMUOVI</button>" +
								"</div>"+
							"</div>";
				txt += 			"<div class=\"panel-body\">"  + ev[i].descrizione + "</div>";
				txt += 		"<div class=\"panel-footer\"> " +
							"<strong>Data :</strong> "+ ev[i].data + " <br>" +
							"<strong>Ora</strong> : " + ev[i].ora + " <br>" +
														ev[i].luogo.comune +
							 							" (" + ev[i].luogo.provincia + ")" +
							"</div>";
					txt += "</div>";
					txt += "</div><br>";
			}
			txt += "</div>";				
			$("#mieieventi").html(txt);			
		}
	});
	
});

function rimuovi(){

	var evento = $("#btnrmv").attr("value");
	
	$.ajax({
		type: "POST",
		url: "eliminaeventi",
		data: {cod_evento : evento},
		success: function(eventi)
		{
			var ev = JSON.parse(eventi);
			var txt = "";
			for(var i = 0; i < ev.length; i++)
			{
				txt += "<div class=\"row\">";				
				txt += "<div id=\"panel_"+ ev[i].codice +"\" class=\"panel\">";
				txt += 		"<div id=\"eventoinbacheca\" class=\"panel-heading\">" +
							"<big><strong>" + ev[i].titolo + "</strong></big>" +
								"<div class=\"btn-group pull-right\">" +
								"<button id=\"btnmdf\" value=\""+ ev[i].codice +"\" onclick=\"modifica();luoghimodifica();\" data-toggle=\"modal\" data-target=\"#myModal\" class=\"btn btn-warning btn-sm\">MODIFICA</button>" +
								"<button id=\"btnrmv\" value=\""+ ev[i].codice +"\" onclick=\"rimuovi()\" type=\"button\" class=\"btn btn-danger btn-sm\">RIMUOVI</button>" +
								"</div>"+
							"</div>";
				txt += 			"<div class=\"panel-body\">"  + ev[i].descrizione + "</div>";
				txt += 		"<div class=\"panel-footer\"> " +
							"<strong>Data :</strong> "+ ev[i].data + " <br>" +
							"<strong>Ora</strong> : " + ev[i].ora + " <br>" +
														ev[i].luogo.comune +
							 							" (" + ev[i].luogo.provincia + ")" +
							"</div>";
					txt += "</div>";
					txt += "</div><br>";
			}
			txt += "</div>";				
			$("#mieieventi").html(txt);			
		}
	});
}

function modifica()
{
	var evento = $("#btnmdf").attr("value");		
	
	$.ajax({
		type: "GET",
		url: "modificaeventi",
		data: {cod_evento : evento},
		success: function(eventi)
		{
			$("#mod").html(eventi);
		}
	});
}

function luoghimodifica()
{
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
			$("#locationmodify").html(txt);
		}
	});
}

function funzionemodificaevento()
{
	alert("evento modificato");
}
