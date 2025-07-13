<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<%@ page  import="java.util.*"%>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="stili/Login.css">
    <link rel="stylesheet" type="text/css" href="stili/footer.css" />
</head>
<body>
	<a href="home.jsp" class="back-button">Torna alla home</a>
   	
   	 <h2>Login</h2>
    <form action="loginServlet" method="post">
        <label for="userid">Nome Utente:</label>
        <input type="text" id="userid" name="userid" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Accedi</button>
    </form>
    
    
<% 
 List<String> errors = (List<String>) request.getAttribute("errors");
if (errors != null){
	for (String error: errors){ %>
		<%=error %> <br>		
	<%
	}
}
%>
    
     <jsp:include page="footer.jsp" />
</body>
</html>