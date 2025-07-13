<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%@ page import="java.util.*,unisa.LibroBean,unisa.Cart"%>
<link rel="stylesheet" type="text/css" href="stili/footer.css" />
</head>
<body>
	
	<% 	Cart cart = (Cart) request.getSession().getAttribute("cart"); 
	if (cart == null)
	{%>
		<p> Non esiste un carrello????
	<% 	}
	
	else {
		List<LibroBean> libri = cart.getLibri();
		if (libri.isEmpty()){
	%>
		<p> Non ci sono elementi nel carrello
		
	<% 	 }else { %>
	
	<form action="Ordine.jsp" name = "Carrello" method = GET> 
	<% 
	
		Iterator<?> it = libri.iterator();
		
		while(it.hasNext()){
			LibroBean bean = (LibroBean) it.next(); %>
    		
    		<div>
    		<br>
    			<img alt="Libro" src="images/default_libro.png" style = "margin:10px;margin-top: 0px;"> 
    			<%=bean.getTitolo() %><br>
    			<%=bean.getAutore() %><br>
    			<%=bean.getDescrizione() %><br>
    			<span class = prezzo><%=bean.getPrezzo()%> </span><br>
    			<input type="button" name=<%=bean.getId() %> value="Rimuovi" >
    			<input type= "number" class = pad  id = <%=bean.getId() %> min="1" max = 99 onclick = >
    				
    		</div>
    		
    		
    	<%} %>
		<input type = "text" id= impTot name = impTot  readonly="readonly">
		</form>
			
			<%} }%>
    	
    	
 	<jsp:include page="footer.jsp" />
 	
 	<script>
 	//Scriplet per far mostrare il valore iniziale del type number
  	window.addEventListener('DOMContentLoaded', () => {
  	  const inputs = document.querySelectorAll('.pad');
  	  inputs.forEach(input => {
  	    input.value = input.min;
  	  });
  	})
  	
</script>

<script type="text/javascript">
window.addEventListener('DOMContentLoaded', () => {

	console.log(" Siamo almeno dentro")
	const prezzi = document.querySelectorAll('.prezzo');
	const numero = document.querySelectorAll('.pad');
	var totale = 0;
	var i = 0;
	console.log(parseFloat(prezzi[0].textContent.trim()) );
	console.log(numero[0].value);
	while(prezzi[i] != undefined){
		console.log(" Siamo almeno dentro for ");
		totale = totale + parseFloat(prezzi[i].textContent.trim());
		console.log("Prezzo = ", totale);
		i++
	}
	document.getElementById("impTot").value = totale.toFixed(2);
})
</script>

<script>
	function aggiornaTotale(){
		const prezzi = document.querySelectorAll('.prezzo');
			const numero = document.querySelectorAll('.pad');
			var totale = 0;
			var i = 0;
			
			while(prezzi[i] != undefined){
				totale = totale + (parseFloat(prezzi[i].textContent.trim())* numero[i].value)
				i++
	}
		console.log(totale);
		
		document.getElementById("impTot").value = totale.toFixed(2);
		
	}

	document.querySelectorAll('.pad').forEach(input => {
	  input.addEventListener('input', aggiornaTotale);
	});
</script>
</body>
</html>