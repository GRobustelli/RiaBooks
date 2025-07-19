/**
 * 
 */

function aggiornaTotale() {
    const prezzi = document.querySelectorAll('.prezzo');
    const quantita = document.querySelectorAll('.quantita');
    let totale = 0;

    for (let i = 0; i < prezzi.length; i++) {
        const prezzo = parseFloat(prezzi[i].textContent.trim()) || 0;
        const qta = parseInt(quantita[i].value) || 1;
        totale += prezzo * qta;
    }

    document.getElementById("impTot").value = totale.toFixed(2);
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






