/**
 * 
 */

function controlloDate(){
	const start = document.getElementById("start").value;
	const end = document.getElementById("end").value;
	
	console.log(start + " " + end);
	
	if (start > end){
		document.getElementById("end").value = start;
	}
}

function doFilter(){
	const email = document.querySelectorAll('.email');
	
	const utente = document.getElementById('utente').value;
	
	const box = document.querySelectorAll(".box");
	const date = document.querySelectorAll(".data");
	
	const start = document.getElementById("start").value;
	const end = document.getElementById("end").value;
	
	console.log(utente + " " + email[0].textContent);
	
	if (utente.trim() != "" && start != "" && end != ""){
		
		
		for (var i = 0; i< email.length; i++){
			console.log("dentro il for");
			
			if (email[i].textContent.includes(utente) && date[i].textContent>= start && date[i].textContent <= end)
			{	
				box[i].hidden = false;
				console.log("dentro true");		
			}
			else{
				box[i].hidden = true;
				console.log("dentro false");
			}
		}
	
		}else if (utente.trim() != "" && start == "" && end == ""){
			for (var i = 0; i< email.length; i++){
						console.log("dentro il for");
						
				if (email[i].textContent.includes(utente)){
					box[i].hidden = false;
					console.log("dentro true");		
				}
				else{
				    box[i].hidden = true;
					console.log("dentro false");}
			}
		}else {

			for (var i = 0; i < date.length; i++){
				console.log(date[i].textContent + "\n" + end + "\n" + start);

				if (date[i].textContent>= start && date[i].textContent <= end)
				{	
					box[i].hidden = false;					
				}
				else
				{
					box[i].hidden = true;
					
				}
			}
			
		}
	
	}
	



function Azzera(){
	const box = document.querySelectorAll(".box");
	
	for (var i = 0; i < box.length; i++){
		box[i].hidden = false;
	}
	document.getElementById("start").value = "";
	document.getElementById("end").value = "";
	document.getElementById("utente").value = "";
}

