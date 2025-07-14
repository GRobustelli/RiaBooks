<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Riepilogo ordini</title>

<%@ page import="java.util.*, unisa.LibroBean, unisa.RiferisceBean,unisa.UserBean,unisa.OrdineBean" %>
</head>
<body>
	
	<% UserBean user = (UserBean) request.getAttribute("user");
	Collection<OrdineBean> collOrd = (Collection<OrdineBean>) request.getAttribute("collOrd");
	ArrayList<Collection<RiferisceBean>> bigList = (ArrayList<Collection<RiferisceBean>>) request.getAttribute("bigList");
	ArrayList<LibroBean> libri = (ArrayList<LibroBean> )request.getAttribute("libri");
	
	Iterator<OrdineBean> itOrd = collOrd.iterator();
	
	while (itOrd.hasNext()){ 
		OrdineBean ord = itOrd.next();
	
		%>
		
	<div>	
	<p>Id Ordine: <%= ord.getId()%> 	
	<%
		Iterator<Collection<RiferisceBean>> it2 = bigList.iterator();
		
		while (it2.hasNext()){
			Collection<RiferisceBean> collezione = it2.next();
			
			Iterator<RiferisceBean> it3 = collezione.iterator();
			
			while (it3.hasNext()){
				RiferisceBean riferimento = it3.next();
				if (ord.getId() == riferimento.getOrdine_id()){
					for (LibroBean libro : libri){
						if (libro.getId() == riferimento.getLibro_id())
						{
						%>
							<span><%=libro.getTitolo() %></span> <span> <%=riferimento.getQuantita() %></span>
						<%
						break;
						}
					}
				}
				
			}
		}
		
		
		
		
	%>
		<span> Importo totale: <%=ord.getImporto() %> </span>
	  </div>
	<%
	}
	
	
	%>	


</body>
</html>