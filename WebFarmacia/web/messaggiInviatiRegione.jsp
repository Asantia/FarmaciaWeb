<%@page import="utils.EmailUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/aquamarine/theme.css" type="text/css"> </head>

<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnessoBean" />
<%
    if(userCon.getConnesso()==true && userCon.getAbilitazione().equals("RE")){
%>
<ul class="nav nav-pills" style="">
    <li class="nav-item">
        <a class="nav-link" href="Regione/homeRegione.jsp" > <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" href="messaggiRegione.jsp" >Messaggi</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="registraNuovaFarmacia.jsp">Registra una nuova farmacia</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="login.jsp">Logout</a>
    </li>
</ul>
<div class="row p-1">
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="messaggiRicevutiRegione.jsp">Posta Ricevuta </a>
    </div>
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="#">Posta Inviata </a>
    </div>
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="nuovoMessaggioRegione.jsp">Nuovo Messaggio </a>
    </div>
</div>
<div class="row m-1">
    <table id="sent"  class="table w-100 h-100 mx-4">
        <thead>
        <tr><th>Inviato a: </th><th>Oggetto</th><th>Testo</th><th>Data</th></tr>
        </thead>
        <tbody>
        <%EmailUtil msglistsent = new EmailUtil(); %>
        <%=msglistsent.sent(userCon.getEmail())%>
        </tbody>
    </table>

    <div class="col-md-3"></div>
    <div class="col-md-3"></div>
    <div class="col-md-3"></div>
    <div class="col-md-3"></div>
</div>
<%
}
else{
%>
<div class="container">
    <h1>Email o password errata</h1>
    <h2> <a href="../../login.jsp">Login</a></h2>
</div>
<%
    }
%>
</body>

</html>
