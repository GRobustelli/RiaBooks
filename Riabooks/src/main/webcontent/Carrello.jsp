  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, unisa.LibroBean, unisa.Cart, unisa.ContieneBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Carrello</title>
    <link rel="stylesheet" type="text/css" href="stili/footer.css" />
    <link rel="stylesheet" type="text/css" href="stili/Carrello.css" />
</head>
<body>
	
	

<%
    Cart cart = (Cart) request.getSession().getAttribute("cart");
	Collection<ContieneBean> contiene = (Collection<ContieneBean>) request.getAttribute("libri");
	
    if (cart == null || cart.getLibri() == null || cart.isEmpty()) {
%>

	<div>
	<br>
    <a href="home.jsp" class="back-button-empty">Il carrello è vuoto, torna alla home.</a>
    
	</div>

<%
    } else {
        List<LibroBean> libri = cart.getLibri();
%>

<form action="Pagamento.jsp" method="get">
    <div id="carrello-container">
    <% for (LibroBean libro : libri) { %>
        <div class="carrello-item" id = <%=libro.getId() %>>
            <img src=<%=libro.getImmagine() %> alt="Libro" />
            <div class="dettagli-libro">
                <p><strong><%= libro.getTitolo() %></strong></p>
                <p>Autore: <%= libro.getAutore() %></p>
                <p><%= libro.getDescrizione() %></p>
                
                <% if (contiene != null){
                	Iterator<ContieneBean> it = contiene.iterator();
                	while (it.hasNext()){
                		ContieneBean beancont = it.next();
                		
                		if (beancont.getLibro_id().equals(libro.getId()) ){     %>
                	
                	     <p class="prezzo" id = p_<%=libro.getId() %>><%= beancont.getPrezzo() %></p>
                <% }}} else{%>
                
                		<p class="prezzo" id = p_<%=libro.getId() %>> <%= libro.getPrezzo() %></p>
                <%} %>
                <input type="number" name="quantita_<%= libro.getId() %>" class="quantita" min="1" max="99" value="1" />
                
                <button type="button" onclick="rimuoviElemento('<%= libro.getId() %>')"> Rimuovi</button>
            </div>
        </div>
    <% } %>
    </div>

   
   <div id="totale-container">
    <div class="riga-bottoni">
        <a href="home.jsp" class="back-button">Torna alla home</a>

        <div class="gruppo-dx">
            <div class="totale-box">
                <label for="impTot">Totale:</label>
                <input type="text" id="impTot" name="impTot" readonly value="0.00" />
          	  </div>
             <input type="submit" value = "Procedi all'ordine">
             
             
            </div>
    </div>
</div>

<%} %>

</form>



<jsp:include page="footer.jsp" />

<script defer>

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

function rimuoviElemento(id) {

	document.getElementById(id).style.display = 'none';
	document.getElementById("p_" + id).textContent= '0';
	aggiornaTotale();
	
	var ajaxvar = new XMLHttpRequest();
	const url = "CartControl?libro_id=" + id + "&action=elimina";
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
</script>

</body>
</html>
