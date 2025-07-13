<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pagamento</title>
    <link rel="stylesheet" href="stili/Pagamento.css">
    <link rel="stylesheet" href="stili/footer.css" />
</head>
<body>
	<a href="home.jsp" class="back-button">Torna alla home</a>
    <form action="ConfermaPagamento.jsp" method="post" class="form-pagamento">
        <h2>Dati di pagamento</h2>

        <label for="intestatario">Nome e Cognome Intestatario</label>
        <input type="text" id="intestatario" name="intestatario" required 
        	   oninput="this.value = this.value.replace(/[^a-zA-ZÀ-ÿ\s']/g, '')" />

        <label for="indirizzo">Indirizzo di Spedizione</label>
        <input type="text" id="indirizzo" name="indirizzo" required />

        <label for="numeroCarta">Numero Carta</label>
        <input type="text" id="numeroCarta" name="numeroCarta"
               maxlength="16" inputmode="numeric" pattern="\d{16}"
               required oninput="this.value = this.value.replace(/[^0-9]/g, '')" />

        <label for="codiceSegreto">Codice Segreto (CVV)</label> <br>
        <input type="password" id="codiceSegreto" name="codiceSegreto"
               maxlength="3" inputmode="numeric" pattern="\d{3}"
               required oninput="this.value = this.value.replace(/[^0-9]/g, '')" /> <br>

        <label for="dataScadenza">Data di Scadenza</label>
        <input type="month" id="dataScadenza" name="dataScadenza" required />

        <input type="submit" value="Effettua pagamento">
    </form>

    <jsp:include page="footer.jsp" />

</body>
</html>
