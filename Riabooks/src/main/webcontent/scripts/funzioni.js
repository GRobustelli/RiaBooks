/**
 * 
 */

function aggiornaTotale(){
	const prezzi = document.querySelectorAll('.prezzo');
		const numero = document.querySelectorAll('.pad');
		var totale = 0;
		var i = 0;
		
		while(prezzi[i] != undefined){
			totale = totale + (parseFloat(prezzi[i].textContent.trim())+ numero[i].value)
			i++
}
	console.log(totale);
	
	document.getElementById("impTot").value = totale;
	
}


function addCartAjax(){
	
	
}