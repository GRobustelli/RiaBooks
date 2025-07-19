<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	UserBean user = (UserBean) request.getSession().getAttribute("user");
	Collection<?> setLibri = (Collection<?>) request.getAttribute("libri");
	if(setLibri == null) {
		response.sendRedirect( request.getContextPath() + "/LibriControl");	
		return;
	}
	

	
	if (user == null){
		response.sendRedirect("/home.jsp");
		return;
	}
	
	if (!user.isAdmin()){
		response.sendRedirect("/NonAdmin.jsp");
		return;
	}
	
%>

<html>

<%@ page contentType="text/html; charset=UTF-8" import="java.util.*,unisa.LibroBean,unisa.UserBean"%>
<head>
    <meta charset="UTF-8">
    <%@include file="../header.jsp" %>
    <title>Riabooks Admin</title>
    <link rel="stylesheet" type="text/css" href="stili/Stile_navigation.css">
    <link rel="stylesheet" type="text/css" href="stili/footer.css" />
    <link rel="stylesheet" type="text/css" href="stili/header.css" />
    <link rel="stylesheet" type="text/css" href="stili/RimuovidalCatalogo.css" />
    <link rel="stylesheet" type="text/css" href="stili/home.css" />
    
    
</head>
<body>

        
        
        <nav id="princ">
            <ul>
               
              	<%
              		if (user != null){
              			%>

                        <li> <div class="user-area">

                       <span class="user-name"> Admin:
                       <%= user.getNome() + " " + user.getCognome() %>
                       </span> 
                       </div>
                       

                       <%} %>
                       <a href="OrdiniControl?action=ordini"><button type="button">Riepilogo Ordini</button></a>
                       <li><a href="Admin/InserisciLibro.jsp"><button type="button">Inserisci Libro</button></a></li>
                       <li><a href="Carrello.jsp"><button type="button">Carrello</button></a></li>
                       <li><a href="LogoutServlet"><button type="button">Logout</button></a></li>
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
                       <div class="book-item" id = "it_<%=bean.getId() %>" >
           <img alt="Libro" src="Immretrievecont?id=<%=bean.getId()%>" onerror = "this.onerror=null; this.src= /images/default_libro.png">
           <div class="book-details">
               <div class="text-overlay">
               <div><%=bean.getTitolo() %></div>
               <div><%=bean.getAutore() %></div>
               <div><%=bean.getDescrizione() %></div>
               <div><%=bean.getPrezzo() %> €</div>
               <button class="add-to-cart" value="<%=bean.getId() %>" onclick="addCartAjax(this.value)" id = c_<%=bean.getId() %>>Aggiungi al carrello</button>
               <a href="modificalibro?action=invio&libro_id=<%=bean.getId()%>"> <button class="modify-item" >Modifica prodotto</button></a>
               
               <% if (bean.isMostra()){ %>
               <button class="remove-to-catalogue" id = "rim_<%=bean.getId() %>" onclick="rimuovidalCatalogo('<%= bean.getId() %>')">Rimuovi dal catalogo</button>
               <button hidden = "hidden" class="add-to-catalogue" id = "add_<%=bean.getId() %>" onclick="aggiungialCatalogo('<%= bean.getId() %>')">Aggiungi al catalogo</button>
               <%} else{ %>
               <button hidden="hidden" class="remove-to-catalogue" id = "rim_<%=bean.getId() %>" onclick="rimuovidalCatalogo('<%= bean.getId() %>')">Rimuovi dal catalogo</button>
                <button class="add-to-catalogue" id = "add_<%=bean.getId() %>" onclick="aggiungialCatalogo('<%= bean.getId() %>')">Aggiungi al catalogo</button>
               <%} %>
           </div>
       </div>
       </div>

                   </div>


               <% } }%>
           </div>
			<script type="text/javascript" src="scripts/funzioniadminhome.js" defer></script>
           <script type="text/javascript" src="scripts/funzioni.js" defer></script>
           <%@include file="/footer.jsp" %> 

       </body>
       </html>