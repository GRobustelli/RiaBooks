/**
 * 
 */


function replaceint(elem){
	
	elem.value = elem.value.replace(/[^a-zA-ZÀ-ÿ\s']/g, '')
}

function checkintestatario(int){
	
	const intestatario = document.getElementById("intestatario").value;
	
	const er = /^[a-zA-ZÀ-ÿ]{1,}\s[a-zA-ZÀ-ÿ]{1,}$/
	
	if (intestatario.length > 50){
		document.getElementById("interr").innerText = "Il campo non può superare i 50 caratteri"
		return false
		
	}else if (er.test(intestatario)){
		document.getElementById("interr").innerText = "";
		console.log("Test superato");
		return true;
	}else {
		console.log("Test non superato");
		document.getElementById("interr").innerText = "Il campo deve essere nella forma: Nome Cognome"
		return false;
	}
	
	
}

function checkindirizzo(){
	
	const indirizzo = document.getElementById("indirizzo").value;
	
	const er = /^[a-zA-ZÀ-ÿ\s']{2,50}\s\d{1,5}[a-zA-Z]?$/;
	
	if (er.test(indirizzo)){
		document.getElementById("inderr").innerText = "";	
		return true;
	}else{
		document.getElementById("inderr").innerText = "Il campo deve essere nella forma: Nome Via [numerocivico]"
	}
	
}

function replacenumcarta(carta){
	
	carta.value = carta.value.replace(/[^0-9]/g, '')
}

function replaceccv(ccv){
	ccv.value = ccv.value.replace(/[^0-9]/g, '')
}


document.getElementById("pagamentoform").addEventListener( 'submit', function(e){
	
	const controllo = checkindirizzo() && checkintestatario();
	
	console.log(controllo);
	if (controllo == false || controllo == null){
		document.getElementById("errsub").innerText = "Controlla che tutti i campi siano corretti";
		e.preventDefault();
	}
});

