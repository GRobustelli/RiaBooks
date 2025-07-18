<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ page  import="java.util.*"%>
<html lang="it">
<head>
    <meta charset="UTF-8"> 
    <%@include file="header.jsp" %>
    <title>Registrazione</title>
   
    <link rel="stylesheet" type="text/css" href="stili/header.css">
    <link rel="stylesheet" type = "text/css" href="stili/Register.css">
    <link rel="stylesheet" type="text/css" href="stili/footer.css" />
</head>
<body>
	<a href="home.jsp" class="back-button">Torna alla home</a>
    <h2>Registrazione</h2>
    <main class="form-wrapper"> 
    
    	<form action="RegisterServlet" method="post" id = "regform">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required onchange = "return checknome()"><br>
        <span class= "errori" id = "errnome"></span><br>

        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" name="cognome" required onchange = "return checkcognome()"><br>
		<span class= "errori" id = "errcognome"></span><br>
        

        <label for="email" >Email:</label>
        <input type="email" id="email" name="email" required onchange = "return checkemail()"><br>
        <span class= "errori" id = "erremail"></span><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required onchange = "return checkconferma()"><br>
		
        
        <label for="conferma_password">Conferma Password:</label>
        <input type="password" id="conferma_password" name="conferma_password" required onchange = "return checkconferma()"><br>
        <span class= "errori" id = "errconfpass"></span><br>
		
		<input type = "hidden" name = "action" value = "do_save">
        
    
        <input type="submit" value = "Registrati">   <br> 
        <span class= "errori" id = "errsub"></span> <br>
    </form>
    </main>
        
<% 
 List<String> errors = (List<String>) request.getAttribute("errors");
if (errors != null){
	for (String error: errors){ %>
		<span class="error"> <%=error %> </span> <br>		
	<%
	}
}
%>
    

    <jsp:include page="footer.jsp" />
    
	<script type="text/javascript" src = "scripts/regexregister.js" defer ></script>
</body>
</html>