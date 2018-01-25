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
								"<button id=\"btnmdf\" value=\""+ ev[i].codice +"\" onclick=\"modifica();luoghimodifica();\" type=\"button\" class=\"btn btn-warning btn-sm\">MODIFICA</button>" +
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
								"<button id=\"btnmdf\" value=\""+ ev[i].codice +"\" onclick=\"modifica();luoghimodifica();\" type=\"button\" class=\"btn btn-warning btn-sm\">MODIFICA</button>" +
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

	alert('evento ' + evento);		
	
	$.ajax({
		type: "GET",
		url: "modificaeventi",
		data: {cod_evento : evento},
		success: function(eventi)
		{
			alert(eventi);
			$("#mieieventi").html(eventi);
		}
	});
}

function luoghimodifica()
{
	alert("luogo");
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
			txt += "<option class = \"divider\" disabled></option>";
			txt += "<option value = \"\">Aggiungi...</option>";
			txt += "</select>";
			$("#locationmodify").html(txt);
			var btn = "<button class=\"btn btn-danger btn-sm\" onclick=\"window.location.reload()\">Annulla</button>" +
//					  "<button class=\"btn btn-success btn-sm\" type=\"submit\" form=\"mod\" value=\"aggiorna\">Aggiorna</button>";
			  "<button class=\"btn btn-success btn-sm\" onclick=\"funzionemodificaevento()\">Aggiorna</button>";
			$("#buttons").html(btn);
		}
	});
}

function funzionemodificaevento()
{
//	var
}
