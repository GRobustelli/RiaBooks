<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*,unisa.UserBean" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <%@include file="../header.jsp" %>
    <title>Inserisci Libro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stili/InserisciLibro.css"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stili/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stili/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stili/Register.css">
	<% 
	UserBean bean = (UserBean) request.getSession().getAttribute("user");

	if (bean == null){
		response.sendRedirect("../home.jsp");}

	if (!bean.isAdmin()){
		response.sendRedirect("../home.jsp");
	}
	
	
	%>


</head>
<body>

    <a href="${pageContext.request.contextPath}/Admin/AdminHome.jsp" class="back-button">Torna alla home</a>
	<h2>Inserisci Libro</h2>
    <main class="form-inserimento"> 
    <form enctype="multipart/form-data" action="${pageContext.request.contextPath}/LibriControl" method="post" id = "modform">
    <label for="id">ID:</label>
    <input type="text" id="id" name="id" required>

    <label for="titolo">Titolo:</label>
    <input type="text" id="titolo" name="titolo" required>

    <label for="autore">Autore:</label>
    <input type="text" id="autore" name="autore" required>

    <label for="prezzo">Prezzo:</label>
    <input type="number" id="prezzo" name="prezzo" step="0.01" required>

    <label for="descrizione">Descrizione:</label>
    <textarea id="descrizione" name="descrizione" rows="4" required="required"></textarea>

    <label for="categoria">Categoria:</label>
    <input type="text" id="categoria" name="categoria" required>

    <label for="immagine">Immagine:</label>
    <input type="file" accept="image/*" name = "talkImage" value="" maxlength="255">
		
		<input type = "hidden" name = "action" value = "do_upload">
        
        <input type="submit" value="Inserisci Libro" onclick= "sanitizeForm('modform')">
    </form>
    
    <%  
    List<String> errors = (List<String>) request.getAttribute("errors");
    if (errors != null) {
	%>
	    <div class="error-message">
	        <% for (String error : errors) { %>
	          <span><%= error %> </span><br>
	        <% } %>
	    </div>
	<% 
    	}
	%>
    
    
    
    </main>




<%@ include file="/footer.jsp" %>
<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/modificalibro.js" defer></script>
</body>
</html>
