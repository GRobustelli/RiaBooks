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
	
	const it = "it_" + id;
	document.getElementById(it).style.display = 'none';
	var ajaxvar = createXMLHttpRequest();
	const url = "LibriControl?action=delCatalogo&libro_id=" + id;
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