<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
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
                <li><a href="Register.jsp"><button type="button">Registrati</button></a></li>
                <li><a href="Login.jsp"><button type="button">Login</button></a></li>
                <li><a href="Carrello.jsp"><button type="button">Carrello</button></a></li>
            </ul>
        </nav>
    </header>

    <nav id="filtro">
        <ul>
            <li>Avventura</li>
            <li>Azione</li>
            <li>Fantasy</li>
        </ul>
    </nav>

    <div></div>

    <%@include file="footer.jsp" %>
</body>
</html>
