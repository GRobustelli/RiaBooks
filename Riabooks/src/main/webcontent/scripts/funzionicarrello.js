/**
 * 
 */


window.addEventListener('DOMContentLoaded', () => {
    aggiornaTotale();

    document.querySelectorAll('.quantita').forEach(input => {
        input.addEventListener('input', aggiornaTotale);
    });
});

function aggiornaTotale() {

    const prezzi = document.querySelectorAll('.prezzo');
    const quantita = document.querySelectorAll('.quantita');
    let totale = 0;

    for (let i = 0; i < prezzi.length; i++) {
        const prezzo = parseFloat(prezzi[i].textContent.trim());
        const qta = parseInt(quantita[i].value);
        totale += prezzo * qta;
    }

    document.getElementById("impTot").value = totale.toFixed(2);
}

function createXMLHttpRequest() {
	var request;
	try {
		// Firefox 1+, Chrome 1+, Opera 8+, Safari 1.2+, Edge 12+, Internet Explorer 7+
		request = new XMLHttpRequest();
	} catch (e) {
		// past versions of Internet Explorer
		try {
			request = new ActiveXObject("Msxml2.XMLHTTP");  
		} catch (e) {
			try {
				request = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				alert("Il browser non supporta AJAX");
				return null;
			}
		}
	}
	return request;
}


function svuotaCarrello(){
	document.getElementById("carrello-container").style.display = "none";
	var ajaxvar = createXMLHttpRequest();
	
	const url = "CartControl?action=svuota";
	
	ajaxvar.open("GET", url, true)
		console.log("ho aperto la connessione");

		console.log("Sto inviando la request")	
		ajaxvar.send();
		
		ajaxvar.onreadystatechange = function () {
			  if (ajaxvar.readyState === 4 && ajaxvar.status === 200) {
			    console.log("Risposta: ce l'abbiamo fatta?");
				const risposta = ajaxvar.responseText;
				
				
				if (risposta.trim() === "true"){
					
					document.getElementById("totale-container").style.display = "none";
					document.getElementById("carrello_svuotato").hidden = false;
				}else{
					console.log("piangi");
				}
					
				}
				}
}

function rimuoviElemento(id) {
	
	document.getElementById(id).style.display = 'none';
	document.getElementById("p_" + id).textContent= '0';
	aggiornaTotale();
	
	var ajaxvar = createXMLHttpRequest();
	
	const url = "CartControl?libro_id=" + id + "&action=elimina";
	ajaxvar.open("GET", url, true)
	console.log("ho aperto la connessione");
	
	console.log("Sto inviando la request")	
	ajaxvar.send();
	
	ajaxvar.onreadystatechange = function () {
		  if (ajaxvar.readyState === 4 && ajaxvar.status === 200) {
		    
			
			const risposta = ajaxvar.responseText;
			
			console.log("\n" + risposta);
				
			if (risposta.trim() === "true"){
				console.log("Io qui ci arrivo");
				document.getElementById("totale-container").style.display = "none";
				document.getElementById("carrello_svuotato").hidden = false;
			}
			
			
			}
			
		
		
		
		setTimeout(function () { if (ajaxvar.readyState < 4) { ajaxvar.abort(); } }, 15000);};
	}