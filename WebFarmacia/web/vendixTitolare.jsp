<%@page import="utils.SellUtil" %>
<%@ page import="utils.BuyMedicinalOb" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css">
    <script src="library/jquery-3.2.1.min.js"></script>
    <script src="js/NewStuff.js"></script>

    <script type="text/javascript">
        function prova(btn){
            var x=[];
            var y=[];
            for(var i=1; i<document.getElementById("lm").rows.length; i++) {
                x.unshift(document.getElementById("lm").rows[i].cells.item(6).value);
                y.unshift(document.getElementById("lm").rows[i].cells.item(0).textContent);
            }
            alert( x.toString() + "---"+ y.toString());
        }
    </script>
</head>
<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnessoBean"/>
<jsp:useBean id="carrello" scope="session" class="beans.CarrelloBean"/>
<%
    if(userCon.getConnesso() && userCon.getAbilitazione().equals("TF")){
%>
<ul class="nav nav-pills" style="">
    <li class="nav-item">
        <a href="Titolare/homeTitolare.jsp" class="nav-link"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a href="messaggiTitolare.jsp" class="nav-link">Messaggi</a>
    </li>
    <li class="nav-item">
        <a href="#" class="nav-link active">Vendi</a>
    </li>
    <li class="nav-item">
        <a href="compra.jsp" class="nav-link">Compra</a>
    </li>
    <li class="nav-item">
        <a href="magazzino.jsp" class="nav-link">Magazzino</a>
    </li>
    <li class="nav-item">
        <a href="registraNuovoDipendente.jsp" class="nav-link">Registra un nuovo dipendente</a>
    </li>
    <%
        }
        else if(userCon.getConnesso() && (userCon.getAbilitazione().equals("DF") || userCon.getAbilitazione().equals("OB"))){
    %>
    <ul class="nav nav-pills" style="">
        <li class="nav-item">
            <a href="Dipendenti/homeDipendenti.jsp" class="nav-link"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
        </li>
        <li class="nav-item">
            <a href="messaggiDipendenti.jsp" class="nav-link">Messaggi</a>
        </li>
        <li class="nav-item">
            <a href="vendixDipendenti.jsp" class="nav-link active">Vendi</a>
        </li>
    <%
        }
    %>
    <li class="nav-item">
        <a class="nav-link" href="login.jsp">Logout</a>
    </li>
</ul>

        <table id="lm">
            <thead>
            <tr><th>Id Prodotto</th><th>Nome</th><th>Dosaggio</th><th>Prezzo</th><th>Ricetta</th><th>Disponibilita'</th><th>Quantita'</th></tr>
            </thead>
            <tbody>
            <%//SellUtil lista = new SellUtil(); %>
            <%//=lista.listout(userCon.getIdFarmacia(), userCon.getAbilitazione())%>
            <% BuyMedicinalOb vendita = new BuyMedicinalOb();%>
            <%=vendita.VendiMedicinali(userCon.getIdFarmacia(), userCon.getAbilitazione())%>
            <%vendita.close();%>
            </tbody>
        </table>
            <input type="button" id="vendi" class="vendi" value="vendi">

<%
    if(!userCon.getConnesso() || (!userCon.getAbilitazione().equals("TF") && !userCon.getAbilitazione().equals("DF") && !userCon.getAbilitazione().equals("OB"))){
%>
<div class="container">
    <h1>Email o password errata</h1>
    <h2> <a href="login.jsp">Login</a></h2>
</div>
<%
    }
%>
</body>

</html>