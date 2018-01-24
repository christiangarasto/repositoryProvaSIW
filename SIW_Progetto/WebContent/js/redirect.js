
function redirectTo(dove){
	var reindirizzaA = dove;
	alert("reindirizzaA: " + reindirizzaA);
	
	$.ajax({
		type: "GET",
		url: "redirect",
		data: {
			reindirizzaA:reindirizzaA
		}
	});
	
	
	
	
}