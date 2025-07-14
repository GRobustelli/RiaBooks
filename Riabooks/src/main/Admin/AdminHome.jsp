<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	Collection<?> setLibri = (Collection<?>) request.getAttribute("libri");
	if(setLibri == null) {
		response.sendRedirect( request.getContextPath()+ "/LibriControl");	
		return;
	}
	
%>

<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,unisa.LibroBean,unisa.UserBean"%>
<head>
    <meta charset="UTF-8">
    <title>Riabooks</title>
    <link rel="stylesheet" type="text/css" href="stili/Stile_navigation.css">
    <link rel="stylesheet" type="text/css" href="stili/footer.css" />
    <link rel="stylesheet" type="text/css" href="stili/home.css" />
</head>
<body>

  <div id="logo">Riabooks</div>
   <header id="main-header">
        <div class="logo">
            <h1>Riabooks</h1>
        </div>

        <nav id="princ">
            <ul>
               
              	<%
              		UserBean user = (UserBean) session.getAttribute("user");
              		if (user == null){
              	%> 
              	
              	<li><a href="Register.jsp"><button type="button">Registrati</button></a></li>
                <li><a href="Login.jsp"><button type="button">Login</button></a></li>
                <% } else {%>
                
                <li> <%=user.getNome() + " " + user.getCognome() %>	
                <%} %>
                <li><a href="Carrello.jsp"><button type="button">Carrello</button></a></li>
            </ul>
        </nav>
    </header>



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
    			<img alt="Libro" src="images/default_libro.png" style = "margin:10px;margin-top: 0px;"> 
    			<%=bean.getTitolo() %><br>
    			<%=bean.getAutore() %><br>
    			<%=bean.getDescrizione() %><br>
    			<%=bean.getPrezzo()  %> <br>
    			<button value=<%=bean.getId() %>  onclick = addCartAjax(this.value)>aggiungi al carrello</button>
    			
    		</div>
			    	
    	
    	<% } }%>
    </div>
    
	<script type="text/javascript" src="scripts/funzioni.js" defer></script>
    <%@include file="../footer.jsp" %> 
  
</body>
</html>