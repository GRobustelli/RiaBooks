  <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*, unisa.LibroBean, unisa.Cart, unisa.ContieneBean" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="header.jsp" %>
    <title>Carrello</title>
    <link rel="stylesheet" type="text/css" href="stili/footer.css" />
    <link rel="stylesheet" type="text/css" href="stili/Carrello.css" />
   
</head>
<body>
	
	

<%
    Cart cart = (Cart) request.getSession().getAttribute("cart");
	Iterator<LibroBean> iteratore = cart.getLibri().iterator();
	while(iteratore.hasNext()){
		LibroBean doDelete = iteratore.next();
		if (!doDelete.isMostra()){
			cart.deleteLibro(doDelete);
		}
	}
	
    if (cart == null || cart.getLibri() == null || cart.isEmpty()) {
%>

	<div>
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
            <img src="Immretrievecont?id=<%=libro.getId()%>" onerror = "/images/default_libro.png" alt="Libro" />
            <div class="dettagli-libro" >
                <p><strong><%= libro.getTitolo() %></strong></p>
                <p>Autore: <%= libro.getAutore() %></p>
                <p><%= libro.getDescrizione() %></p>
                <p class="prezzo" id = "p_<%= libro.getId()%>"><%= libro.getPrezzo() %></p>
               
                <input type="number" name="quantita_<%= libro.getId() %>" class="quantita" min="1" max="99" value="1" />
                
                <button type="button" onclick="rimuoviElemento('<%= libro.getId() %>')"> Rimuovi</button>
            </div>
        </div>

    </div>
 <%} %>
   
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

	<div id = "carrello_svuotato" hidden= "hidden">
	<br>
    <a href="home.jsp" class="back-button-empty">Il carrello è vuoto, torna alla home.</a>
    
	</div>
<script src = "scripts/funzionicarrello.js" defer></script>

<jsp:include page="footer.jsp" />


</body>
</html>
