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
        <div class="carrello-item">
            <img src=<%=libro.getImmagine() %> alt="Libro" />
            <div class="dettagli-libro" id = <%=libro.getId() %>>
                <p><strong><%= libro.getTitolo() %></strong></p>
                <p>Autore: <%= libro.getAutore() %></p>
                <p><%= libro.getDescrizione() %></p>
                
                <% if (contiene != null){
                	Iterator<ContieneBean> it = contiene.iterator();
                	while (it.hasNext()){
                		ContieneBean beancont = it.next();
                		
                		if (beancont.getLibro_id().equals(libro.getId()) ){     %>
                	
                	     <p class="prezzo"><%= beancont.getPrezzo() %></p>
                <% }}} else{%>
                
                		<p class="prezzo"><%= libro.getPrezzo() %></p>
                <%} %>
                <input type="number" name="quantita_<%= libro.getId() %>" class="quantita" min="1" max="99" value="1" />
                
                <button type="button" onclick="rimuoviElemento('<%= libro.getId() %>'"> Rimuovi</button>
            </div>
        </div>
    <% } %>
    </div>

   
    <div id="totale-container">
        <div class="riga-bottoni">
            <div class="gruppo-sx">
                <a href="home.jsp" class="back-button">Torna alla home</a>
                <button type="button" class="svuota-button" onclick="svuotaCarrello()">Svuota il carrello</button>
            </div>

            <div class="gruppo-dx">
                <div class="totale-box">
                    <label for="impTot">Totale:</label>
                    <input type="text" id="impTot" name="impTot" readonly value="0.00" />
                </div>
                <input type="submit" value="Procedi all'ordine">
            </div>
        </div>
    </div>

<%} %>

</form>



<jsp:include page="footer.jsp" />

<script src="scripts/funzioni.js" defer>

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
        const prezzo = parseFloat(prezzi[i].textContent.trim()) || 0;
        const qta = parseInt(quantita[i].value) || 1;
        totale += prezzo * qta;
    }

    document.getElementById("impTot").value = totale.toFixed(2);
}


</script>

</body>
</html>
