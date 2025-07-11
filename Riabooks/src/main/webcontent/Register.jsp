<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Registrazione</title>
    <link rel="stylesheet" href="stili/Register.css">
    <link rel="stylesheet" type="text/css" href="stili/footer.css" />
</head>
<body>

    <h2>Registrazione</h2>
    <form action="/register" method="post">
        <label for="nome">Nome:</label>
        <input type="text" id="nome" name="nome" required><br><br>

        <label for="cognome">Cognome:</label>
        <input type="text" id="cognome" name="cognome" required><br><br>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" required><br><br>

        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br><br>

        <label for="conferma_password">Conferma Password:</label>
        <input type="password" id="conferma_password" name="conferma_password" required><br><br>

        <button type="submit">Registrati</button>
    </form>

    <jsp:include page="footer.jsp" />

</body>
</html>