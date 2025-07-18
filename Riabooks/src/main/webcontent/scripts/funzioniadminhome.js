/**
 * 
 */
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


function rimuovidalCatalogo (id){
	
	const actual = "rim_" + id;
	const nuovo = "add_" + id;
	const carr = "c_" + id;
	console.log(actual + " " + nuovo)
	document.getElementById(actual).hidden = true;
	document.getElementById(nuovo).hidden= false;
	document.getElementById(carr).hidden = true;
	
	var ajaxvar = createXMLHttpRequest();
	const url = "LibriControl?valore=false&action=modCatalogo&libro_id=" + id;
	ajaxvar.open("GET", url, true)
			console.log("ho aperto la connessione ");

			console.log("Sto inviando la request")	
			ajaxvar.send();
			
			ajaxvar.onreadystatechange = function () {
				  if (ajaxvar.readyState === 4 && ajaxvar.status === 200) {
				    console.log("Risposta: ce l'abbiamo fatta?");
					const risposta = ajaxvar.responseText;
					if (risposta.trim() === "true"){
						console.log("non pianger");}
					else{
						console.log("pianger");
					}	
	
				}
			}
}


function aggiungialCatalogo (id){
	
	const actual = "add_" + id;
	const nuovo = "rim_" + id;
	const carr = "c_" + id;	
	console.log(actual + " " + nuovo)
	document.getElementById(actual).hidden = true;
	document.getElementById(nuovo).hidden= false;
	document.getElementById(carr).hidden = false;
	var ajaxvar = createXMLHttpRequest();
	
	const url = "LibriControl?valore=true&action=modCatalogo&libro_id=" + id;
	ajaxvar.open("GET", url, true)
			console.log("ho aperto la connessione");

			console.log("Sto inviando la request")	
			ajaxvar.send();
			
			ajaxvar.onreadystatechange = function () {
				  if (ajaxvar.readyState === 4 && ajaxvar.status === 200) {
				    console.log("Risposta: ce l'abbiamo fatta?");
					const risposta = ajaxvar.responseText;
					if (risposta.trim() === "true"){
						console.log("non pianger");}
					else{
						console.log("pianger");
					}	
	
				}
			}
}