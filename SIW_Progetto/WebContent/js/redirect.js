function redirectTo(dove){
	var reindirizzaA = dove;
	
	$.ajax({
		type: "GET",
		url: "redirect",
		data: {
			reindirizzaA:reindirizzaA
		}
	});
	
	
	
	
}