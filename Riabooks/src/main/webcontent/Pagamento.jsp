<%@page import="java.time.LocalDate,unisa.UserBean"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <%@include file="header.jsp" %>
    
    <title>Pagamento</title>
    <%@ page import="java.util.*, unisa.LibroBean, unisa.Cart" %>
    <link rel="stylesheet" href="stili/Pagamento.css">
    <link rel="stylesheet" href="stili/Register.css">
    <link rel="stylesheet" href="stili/header.css">
    
    <link rel="stylesheet" href="stili/footer.css" />
    <% 
    	UserBean user = (UserBean) request.getSession().getAttribute("user");
    	if (user == null){
    		response.sendRedirect("Login.jsp");
   			return; 
    	}
    %>
    
    
    
</head>
<body>
	<a href="home.jsp" class="back-button">Torna alla home</a>
	<h2>Dati di pagamento</h2>
	<main class="form-wrapper">
    <form action="OrdiniControl" method="post" id = "pagamentoform"> 
        

        <label for="intestatario">Nome e Cognome Intestatario</label>
        <input type="text" id="intestatario" name="intestatario" required oninput="replaceint(this)" onchange=" return checkintestatario()"/>
        <span class = "pagerr" id = "interr"></span>

        <label for="indirizzo">Indirizzo di Spedizione</label>
        <input type="text" id="indirizzo" name="indirizzo" required onchange = "return checkindirizzo(this)" />
		<span class = "pagerr" id = "inderr"></span>
        
        <label for="numeroCarta">Numero Carta</label>
        <input type="text" id="numeroCarta" name="numeroCarta"
               maxlength="16" inputmode="numeric" pattern="\d{16}"
               required oninput="replacenumcarta(this)" />

        <label for="codiceSegreto">Codice Segreto (CVV)</label> <br>
        <input type="password" id="codiceSegreto" name="codiceSegreto"
               maxlength="3" inputmode="numeric" pattern="\d{3}"
               required oninput="replaceccv(this)" /> <br>

        <label for="dataScadenza">Data di Scadenza</label>
        <input type="month" id="dataScadenza" name="dataScadenza" required min="2025-01" max = "9999-12"/>

        <input type="submit" value="Effettua pagamento">
        <span id = "errsub"></span>
        <input type = "text" name ="impTot" style= "display:none;" value = <%=request.getParameter("impTot")%>>
        
        
        
        <%	
        	Cart cart  = (Cart) request.getSession().getAttribute("cart");
        		
       		if (!cart.isEmpty()){ 
        	List<LibroBean> lista = cart.getLibri();
        	
        	for (LibroBean bean : lista){ 
        		String  ins = "quantita_" + bean.getId();
        	%>
 				
        	<input type = "hidden" name = "q_<%=bean.getId() %>" value = <%=request.getParameter(ins)%>>
        	
        	<%		
        	}}else{
        		response.sendRedirect("/home.jsp");
        	}
        	%>
        	
        	<input type = "hidden" name = "action" value = "insert">
    	
    </form>
</main>

	<script type="text/javascript" src="scripts/regexpagamento.js" defer></script>
    <jsp:include page="footer.jsp" />

</body>
</html>
