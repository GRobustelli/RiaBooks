<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,unisa.UserBean,unisa.OrdineBean,unisa.RiferisceBean,unisa.LibroBean"%>
<% 
	UserBean bean = (UserBean) request.getSession().getAttribute("user");

if (bean == null || !bean.isAdmin()){
	response.sendRedirect("../home.jsp");
}else{
	
	Collection<OrdineBean> ordcoll = (Collection<OrdineBean>) request.getAttribute("adcollord");
	Collection<RiferisceBean> rifcoll = (Collection<RiferisceBean>) request.getAttribute("rifadmin");
	Collection<LibroBean> libcoll = (Collection<LibroBean>) request.getAttribute("libriad");
	boolean controllo = false;
	if (ordcoll == null || rifcoll == null || libcoll == null){
		//fai qualcosa
	}
	else{
		Iterator<OrdineBean> ordit = ordcoll.iterator();
		Iterator<RiferisceBean> rifit = rifcoll.iterator();
		RiferisceBean rif = rifit.next();
		
%>




<html>
<head>
<meta charset="UTF-8">
<title>Riepilogo ordini</title>
</head>
<body>
	<div>
		   <nav id="princ">
           <ul>   
           		<li>Data inizio: <input type = "date" id = "start" onchange="controlloDate()"> </li>
           		<li>Data fine: <input type = "date" id = "end" onchange="controlloDate()"> </li>
           		<li>Utente:  <input type = text id = "utente"></li>
           		<li><button onclick = "doFilter()">Filtra</button></li>
           		<li><input type = "reset" value  = "Azzera" onclick = "Azzera()"></li>
           </ul>
        </nav>
			
		
	</div>
	

	<% 
		while (ordit.hasNext()){
		
			OrdineBean ord = ordit.next();
			
			%>
		
			<div class= "box" id = <%=ord.getId() %>>
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
	<%  }}} %>	

	<script type="text/javascript" src="scripts/funzioniadordini.js" defer></script>
</body>

</html>