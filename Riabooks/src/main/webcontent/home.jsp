<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%

	Collection<?> setLibri = (Collection<?>) request.getAttribute("libri");
	if(setLibri == null) {
		response.sendRedirect("LibriControl");	
		return;
	}
	
%>

<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,unisa.LibroBean,unisa.UserBean"%>
<head>
    <meta charset="UTF-8">
    <%@include file="header.jsp" %>
    <title>Riabooks</title>
    <link rel="stylesheet" type="text/css" href="stili/Stile_navigation.css">
    <link rel="stylesheet" type="text/css" href="stili/footer.css" />
    <link rel="stylesheet" type="text/css" href="stili/home.css" />
    <link rel="stylesheet" type="text/css" href="stili/header.css">
</head>
<body>

  

        <nav id="princ">
            <ul>
               
              	<%
              		UserBean user = (UserBean) session.getAttribute("user");
              		if (user == null){
              	%> 
              	
              	<li><a href="Register.jsp"><button type="button">Registrati</button></a></li>
                <li><a href="Login.jsp"><button type="button">Login</button></a></li>
                <% } else {%>
                
                <li> <div class="user-area">
                <span class="user-name">Utente:
                <%= user.getNome() + " " + user.getCognome() %></span> 
                </div>
                
                
              <form action="OrdiniControl">
                  <input type="hidden" name="action" value="ordini">
              <input type="submit" value = "Riepilogo Ordini"> </form>
              
                 <li><a href="LogoutServlet"><button type="button" class="logout">Logout</button></a></li>
                <%} %>
                
               <li><a href="CartControl?action=prezzi"><button type="button">Carrello</button></a></li>
               
               
            </ul>
        </nav>
    

    
    <div>
    	<%  
    		if(setLibri != null && setLibri.size() != 0)
    		{
    			Iterator<?> it = setLibri.iterator();
    			
    			while(it.hasNext()){
    				LibroBean bean = (LibroBean) it.next();
    	%>
    		<div>
    		<br>
    			<div class="book-item">
    <img alt="Libro" src="Immretrievecont?id=<%=bean.getId()%>" onerror = "/images/default_libro.png">
    <div class="book-details">
    	<div class="text-overlay">
        <div><%=bean.getTitolo() %></div>
        <div><%=bean.getAutore() %></div>
        <div><%=bean.getDescrizione() %></div>
        <div><%=bean.getPrezzo() %> €</div>
        <button class="add-to-cart" value="<%=bean.getId() %>" onclick="addCartAjax(this.value)">
    	Aggiungi al carrello</button>
    </div>
</div>
</div>    			
    			
    		</div>
			    	
    	
    	<% } }%>
    </div>
    
	<script type="text/javascript" src="scripts/funzioni.js" defer></script>
	
    <%@include file="footer.jsp" %>
    
  
</body>
</html>