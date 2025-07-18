/**
 * 
 */

function checknome(){
	const nome = document.getElementById("nome").value;
	
	console.log(nome);
	
	const checknome = /^([a-zA-Z ]){4,50}$/;
	
	if (checknome.test(nome))
		{
			
			document.getElementById("errnome").innerText = "";
			console.log("Test superato");
			return true;
		}
		else {
			console.log ("Test non superato");
			
			if (nome.length > 50 || nome.length < 4){
			console.log ("più di 50");
			document.getElementById("errnome").innerText = "Il campo nome deve contenere da 4 a 50 caratteri"
			}else{
			
			document.getElementById("errnome").innerText = "Il campo nome non può contenere caratteri speciali [<,>,@,!...] o numeri";
			}
			
			return false;
		}
	
}


function checkcognome() {
	
	const cognome = document.getElementById("cognome").value;

	console.log(cognome);

	const checkcognome = /^([a-zA-Z ]){3,50}$/;

	if (checkcognome.test(cognome))
		{
			
			document.getElementById("errcognome").innerText = "";
			console.log("Test superato");
			return true;
		}
		else {
			console.log ("Test non superato");
			
			if (cognome.length > 50 || cognome.length < 4){
			console.log ("più di 50");
			document.getElementById("errcognome").innerText = "Il campo cognome deve contenere da 4 a 50 caratteri";
		}else{
			
			document.getElementById("errcognome").innerText = "Il campo cognome non può contenere caratteri speciali [<,>,@,!...] o numeri";
		}
			
			return false;
		}
	
}

function checkemail(){
	
	const emailcheck = /^[A-Za-z_]{3,}@[A-Za-z]{3,}[.]{1}[A-Za-z.]{2,6}$/;
	
	const email = document.getElementById("email").value
	
	if (emailcheck.test(email)){
		
		document.getElementById("erremail").innerText = "";
		console.log("Test superato");
		return true;
	}
	else{
		document.getElementById("erremail").innerText = "formato email non corretto ( almeno 3 caratteri per i primi due campi ex. example@mail.it)"
		return false;
	}
	

}


function checkconferma(){
	
	const pass = document.getElementById("password").value;
	const conf_pass = document.getElementById("conferma_password").value;
	
	
	if (conf_pass != ""){
	if (pass == conf_pass){
		
		document.getElementById("errconfpass").innerText = "";
		console.log("Test superato");
		return true;
	}else{
		
		document.getElementById("errconfpass").innerText = "La password non corrisponde";
		console.log("Test non superato");
		return false;
	}
}	
}		

/*
function checksubmit(){
	
	const controllo = checknome() && checkcognome() && checkemail() && checkconferma();
	
	if (controllo == false || controllo == null){
		
		document.getElementById("errsub").innerText = "Controlla che tutti i campi siano corretti";
		return false;
	}else{
		return true;
	}
}	
*/


document.getElementById("regform").addEventListener( 'submit', function(e){
	
	const controllo = checknome() && checkcognome() && checkemail() && checkconferma();
	
	console.log(controllo);
	if (controllo == false || controllo == null){
		document.getElementById("errsub").innerText = "Controlla che tutti i campi siano corretti";
		e.preventDefault();
	}
});
