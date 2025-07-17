<%@page import="unisa.LibroBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<%
		LibroBean libro = (LibroBean) request.getAttribute("modlib");
		if (libro == null){
			String id = request.getParameter("libro_id");	
		}
		else{
	
	%>




<html>
<head>
<meta charset="UTF-8">
<title>Modifica libro</title>
</head>

<body>
		
		 <div>
		 	<form action="${pageContext.request.contextPath}/modificalibro" method = "post" enctype = "multipart/form-data"> 
		 		
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
		 	
		 
			
			<input type = "submit" value = "Conferma modifiche">
			</form>			 		
		 </div>
		
	
<%} %>
	
</body>
</html>