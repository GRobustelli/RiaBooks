<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Inserisci Libro</title>
    <link rel="stylesheet" href="../stili/InserisciLibro.css">
    <link rel="stylesheet" href="../stili/footer.css">
</head>
<body>

<a href="AdminHome.jsp" class="back-button">Torna alla home</a>

<form enctype="multipart/form-data" action="inserisciLibroServlet" method="post" class="form-inserimento">
    <h2>Inserisci Libro</h2>

    <label for="id">ID:</label>
    <input type="text" id="id" name="id" required>

    <label for="titolo">Titolo:</label>
    <input type="text" id="titolo" name="titolo" required>

    <label for="autore">Autore:</label>
    <input type="text" id="autore" name="autore" required>

    <label for="prezzo">Prezzo:</label>
    <input type="number" id="prezzo" name="prezzo" step="0.01" required>

    <label for="descrizione">Descrizione:</label>
    <textarea id="descrizione" name="descrizione" rows="4"></textarea>

    <label for="categoria">Categoria:</label>
    <input type="text" id="categoria" name="categoria" required>

    <label for="immagine">Immagine:</label>
    <input type="file" accept="image/*">

    <input type="submit" value="Inserisci Libro">
    
</form>

<%  
    List<String> errors = (List<String>) request.getAttribute("errors");
    if (errors != null) {
%>
    <div class="error-message">
        <% for (String error : errors) { %>
            <%= error %><br>
        <% } %>
    </div>
<% 
    }
%>

<%@ include file="/footer.jsp" %>

</body>
</html>
