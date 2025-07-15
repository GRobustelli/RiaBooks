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


function addCartAjax(valore){
	
	var ajaxvar = new XMLHttpRequest();
	console.log(valore);
	const url = "CartControl?libro_id=" + valore + "&action=insert";
	ajaxvar.open("GET", url, true)
	console.log("ho aperto la connessione");

	console.log("Sto inviando la request")	
	ajaxvar.send();
	ajaxvar.onreadystatechange = function () {
		  if (ajaxvar.readyState === 4 && ajaxvar.status === 200) {
		    console.log("Risposta: ce l'abbiamo fatta?");
		}
	}
}


function rimuoviElemento(id) {
   Document.getElementById(id).style.display = hidden;
	
}