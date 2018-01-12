$(window).on('load', function(){
	alert("vi scuoio!!!");
	$.ajax({
		type : "GET",
		url  : "gestioneeventi",
		success : function(){
			$("#evento").html();
		}
	});	
});



/*
	$("#luoghibutton").on('click', function(){
		
		alert("raccolta delle location di un utente");
		
		$.ajax({
			type : "GET",
			url  : "gestioneluoghi",
			success : function(){
				$("#location").html();
			}
		});
	});
	
	$("#expand_form").on('click', function(){
	    var x = document.getElementById("eventForm");
	    x.style.display = "block";
	});
*/
