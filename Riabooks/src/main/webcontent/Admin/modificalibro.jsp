<%@page import="unisa.LibroBean,unisa.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<%	UserBean bean = (UserBean) request.getSession().getAttribute("user");

	if (bean == null)
	{
		response.sendRedirect("../home.jsp");
	} 
	
	else if (!bean.isAdmin())
	{
		response.sendRedirect("../home.jsp");
	}
		
		
		LibroBean libro = (LibroBean) request.getAttribute("modlib");
		if (libro == null){
			String id = request.getParameter("libro_id");	
		}
		else{
	
	%>




<html>
<head>
<meta charset="UTF-8">

<%@include file="../header.jsp" %>
    <title>Modifica libro</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stili/InserisciLibro.css"> 
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stili/header.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stili/footer.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stili/Register.css">

</head>

<body>
			<a href="${pageContext.request.contextPath}/Admin/AdminHome.jsp" class="back-button">Torna alla home</a>
			<h2>Modifica Libro</h2>
    		<main class="form-inserimento"> 
			<div>
		 	<form action="${pageContext.request.contextPath}/modificalibro" method = "post" enctype = "multipart/form-data" id = "modform"> 
		 		
		    <input type= "hidden" name = libro_id value = <%=libro.getId() %>>
		 	<input type= "hidden" name = "modaction" value = "do_mod">
		 	
		 	<img alt="Libro" src="Immretrievecont?id=<%=libro.getId()%>" onerror = "/images/default_libro.png">
		 	<input type="file" accept="image/*" name = "talkImage" value="" maxlength="255">
		 	
		 	<div>Vecchio titolo: <%=libro.getTitolo() %></div> 
		 	<div><label for="titolo">Nuovo titolo: </label> <input type="text" id="titolo" name="titolo"></div>
		 	
		 	<div>Vecchio autore: <%=libro.getAutore() %> </div> 
		 	<div> <label for="autore">Nuovo autore: </label> <input type="text" id="autore" name="autore"></div>
		 	
		 	<div>Vecchia categoria: <%=libro.getCategoria() %> </div>
		 	<div><label for="categoria">Nuova categoria:</label> <input type="text" id="categoria" name="categoria"></div>
		 	
		 	<div>Vecchia descrizione: <%=libro.getDescrizione() %> </div>
		 	<div> <label for="descrizione">Nuova descrizione: </label><textarea id="descrizione" name="descrizione" rows="4"></textarea></div>
		 	
		 	
		 	<div>Vecchio prezzo: <%=libro.getPrezzo() %> </div>
		 	<div><label for="prezzo">Nuovo prezzo:</label> <input type="number" id="prezzo" name="prezzo" step="0.01"></div>
		 	
		 
			
			<input type = "submit" value = "Conferma modifiche" onclick= "sanitizeForm(modform)">
			</form>			 		
		 </div>
		</main>
		
	
<%} %>
	<script type="text/javascript" src="${pageContext.request.contextPath}/scripts/modificalibro.js" defer></script>
</body>
</html>