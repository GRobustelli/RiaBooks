<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,unisa.UserBean,unisa.OrdineBean,unisa.RiferisceBean,unisa.LibroBean"%>
<% 
	UserBean bean = (UserBean) request.getSession().getAttribute("user");

if (bean == null){
	response.sendRedirect("../home.jsp");
	} else if (!bean.isAdmin()){
	response.sendRedirect("../home.jsp");
	
}
else{
	
	Collection<OrdineBean> ordcoll = (Collection<OrdineBean>) request.getAttribute("adcollord");
	Collection<RiferisceBean> rifcoll = (Collection<RiferisceBean>) request.getAttribute("rifadmin");
	Collection<LibroBean> libcoll = (Collection<LibroBean>) request.getAttribute("libriad");
	boolean controllo = false;
	if (ordcoll == null || rifcoll == null || libcoll == null){
		response.sendRedirect("../home.jsp");
	}
	else{
		Iterator<OrdineBean> ordit = ordcoll.iterator();
		Iterator<RiferisceBean> rifit = rifcoll.iterator();
		
		if (rifit.hasNext()){
		RiferisceBean rif = rifit.next();
		
%>




<html>
<head>
<meta charset="UTF-8">
<%@include file="../header.jsp" %>
<title>Riepilogo ordini</title>

<link rel="stylesheet" type="text/css" href="stili/footer.css" /> 
<link rel="stylesheet" type="text/css" href="stili/RiepilogoOrdini.css" /> 
<link rel="stylesheet" type="text/css" href="stili/header.css" /> 
<link rel="stylesheet" type="text/css" href="stili/adminriepilogoordini.css" /> 
</head>
<body>
	<a href="${pageContext.request.contextPath}/Admin/AdminHome.jsp" class="back-button">Torna alla home</a>
	<div>	
		   <nav id="princ">
           <ul>   
           
           		<li class="timing">Data inizio: <input type = "date" id = "start" onchange="controlloDate()"> </li>
           		<li class="timing">Data fine: <input type = "date" id = "end" onchange="controlloDate()"> </li>
           		<li class="timing">Utente:  <input type = text id = "utente"></li>
           		<li><button onclick = "doFilter()"class= "filtro">Filtra </button></li>
           		<li><input type = "reset" value  = "Azzera" onclick = "Azzera()" class="azzera"></li>
           </ul>
        </nav>
			
		
	</div>
	

	<% 
		while (ordit.hasNext()){
		
			OrdineBean ord = ordit.next();
			
			%>
		<div class= "box" id = <%=ord.getId() %>>
		<div class="book-details">
    	<div class="text-overlay">
			
			<div><strong>Id Ordine:</strong> <%= ord.getId() %> email: <span class = "email" id = e_<%=ord.getId() %>><%=ord.getEmail() %></span></div>
		
			<% 
			
			while (ord.getId() == rif.getOrdine_id() && !controllo){
				for (LibroBean libro : libcoll){
					if (libro.getId().equals(rif.getLibro_id())){
						
						%>
							
    			
							<span> Titolo:  <%=libro.getTitolo() %></span> <br>
							<span> Quantità: <%=rif.getQuantita() %></span> <br>
							<span>Prezzo: <%=rif.getPrezzo() %></span> <br>
							<span>**********************************************</span> <br>
						
			
						
						<% 
						break;}
				}
				if (rifit.hasNext())
				{
				rif = rifit.next();
				}
				else{
					controllo = true;
				}
				
			}
			%> 
			
    				
    
			<span> Data: <span class = data id =p_<%=ord.getId() %>><%= ord.getData() %></span></span>
			<span> Importo totale: <%=ord.getImporto()%> </span>
			</div>
			</div>
			</div>
			<br>	
	<%  }}else{ %>
			
			<a href="${pageContext.request.contextPath}/Admin/AdminHome.jsp" class="back-button">Non ci sono ordini, torna alla home</a>
		
	<% }}} %>	

	<script type="text/javascript" src="scripts/funzioniadordini.js" defer></script>
	<%@ include file="/footer.jsp" %>
</body>

</html>