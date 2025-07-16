<%@page import="unisa.LibroBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

	<%
		LibroBean libro = (LibroBean) request.getAttribute("modlibro");
		if (libro == null){
			String id = request.getParameter("libro_id");
			RequestDispatcher dispatcher = request.getRequestDispatcher("modificalibro?action=invio&libro_id=" + id);
			dispatcher.forward(request, response);
		}
	
	%>




<html>
<head>
<meta charset="UTF-8">
<title>Modifica libro</title>
</head>

<body>
		
		 <div>
		 	<button>Modifica immagine</button><br>
		 	<img alt="Libro" src="Immretrievecont?id=<%=libro.getId()%>" onerror = "/images/default_libro.png"> 
		 	<div><%=libro.getTitolo() %><button>modifica</button> </div> 
		 	<div><%=libro.getAutore() %> <button>modifica</button> </div>
		 	<div><%=libro.getCategoria() %> <button>modifica</button> </div>
		 	<div><%=libro.getDescrizione() %> <button>modifica</button> </div>
		 	<div><%=libro.getPrezzo() %> <button>modifica</button> </div>
		 	
		 		
		 </div>
	<div>	
	<form action="${pageContext.request.contextPath}/modificalibro" method="get">
<div>
    <label for="titolo">nuovo titolo:</label>
    <input type="text" id="titolo" name="titolo"  hidden="hidden">
</div>
<div>
    <label for="autore">nuovo autore:</label>
    <input type="text" id="autore" name="autore" hidden="hidden">
</div>
<div>
    <label for="prezzo">nuovo prezzo:</label>
    <input type="number" id="prezzo" name="prezzo" step="0.01">
</div>
<div>
    <label for="descrizione">nuova descrizione:</label>
    <textarea id="descrizione" name="descrizione" rows="4"></textarea>
	</div>
<div>
    <label for="categoria">nuova categoria:</label>
    <input type="text" id="categoria" name="categoria">
</div>

	<input type = "hidden" name = "action" value = "do_mod">

  	<input type="submit" value="Procedi alla modifica" hidden="hidden">
</form>
   
<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/modificalibro" method=post>
	<div>	
		<label for="immagine">nuova immagine:</label>
    	<input type="file" accept="image/*" name = "talkImage" value="" maxlength="255" required>	
		<input type="submit" value="Cambia immagine" hidden="hidden">			
	</div>
		<input type = "hidden" name = "action" value = "do_modimm">
    </form>
    
	</div>

</body>
</html>