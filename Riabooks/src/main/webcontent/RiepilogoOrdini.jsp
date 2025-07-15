<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Riepilogo ordini</title>
<link rel="stylesheet" type="text/css" href="stili/footer.css" /> 
<link rel="stylesheet" type="text/css" href="stili/RiepilogoOrdini.css" /> 
<link rel="stylesheet" type="text/css" href="stili/home.css" /> 

<%@ page import="java.util.*, unisa.LibroBean, unisa.RiferisceBean,unisa.UserBean,unisa.OrdineBean" %>
</head>
<body>
	<a href="home.jsp" class="back-button">Torna alla home</a>
	
	<% UserBean user = (UserBean) request.getAttribute("user");
	Collection<OrdineBean> collOrd = (Collection<OrdineBean>) request.getAttribute("collOrd");
	ArrayList<Collection<RiferisceBean>> bigList = (ArrayList<Collection<RiferisceBean>>) request.getAttribute("bigList");
	ArrayList<LibroBean> libri = (ArrayList<LibroBean> )request.getAttribute("libri");
	
	Iterator<OrdineBean> itOrd = collOrd.iterator();
	
	while (itOrd.hasNext()){ 
		OrdineBean ord = itOrd.next();
	
		%>
		<br>
		<br>
		<br>
		
		
	<div class="book-details"> 
		<div class="text-overlay">
		        <div><strong>Id Ordine:</strong> <%= ord.getId() %>

		    </div>

		
		    <%
		Iterator<Collection<RiferisceBean>> it2 = bigList.iterator();
		
		while (it2.hasNext()){
			Collection<RiferisceBean> collezione = it2.next();
			
			Iterator<RiferisceBean> it3 = collezione.iterator();
			
			while (it3.hasNext()){
				RiferisceBean riferimento = it3.next();
				if (ord.getId() == riferimento.getOrdine_id()){
					for (LibroBean libro : libri){
						if (libro.getId().equals(riferimento.getLibro_id()))
						{
						%>
						
							<span> Titolo:  <%=libro.getTitolo() %></span>
							<span>, Quantità: <%=riferimento.getQuantita() %></span> <br>
						<%
						break;
						}
					}
				}
				
			}
		}		
	%>
	
		<span> Data: <%= ord.getData() %></span>
		<span> Importo totale: <%=ord.getImporto() %> </span>
	  </div>
	  </div> 
	  <br>
	  
	<%
	}
	%>	
	<jsp:include page="footer.jsp" />

</body>
</html>