<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" href="stili/Login.css">
    <link rel="stylesheet" type="text/css" href="stili/footer.css" />
</head>
<body>
    <h2>Login</h2>
    <form action="/login" method="post">
        <label for="userid">Nome Utente:</label>
        <input type="text" id="userid" name="userid" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <button type="submit">Accedi</button>
    </form>
     <jsp:include page="footer.jsp" />
</body>
</html>