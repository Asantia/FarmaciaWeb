<%--
  Created by IntelliJ IDEA.
  User: csantia
  Date: 9/24/2017
  Time: 3:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>inseriscipaziente</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/aquamarine/theme.css" type="text/css">
</head>
<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnessoBean"/>
<jsp:useBean id="pazCon" scope="session" class="beans.PazienteCercatoBean"/>
<%
    if(userCon.getConnesso() && (userCon.getAbilitazione().equals("TF") || userCon.getAbilitazione().equals("DF"))){
%>
<ul class="nav nav-pills" style="">
    <%
        if(userCon.getConnesso() && (userCon.getAbilitazione().equals("TF"))){
    %>
    <li class="nav-item">
        <a href="Titolare/homeTitolare.jsp" class="nav-link"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a href="messaggiTitolare.jsp" class="nav-link">Messaggi</a>
    </li>
    <li class="nav-item">
        <a href="vendixTitolare.jsp" class="nav-link">Vendi</a>
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
        }else if(userCon.getAbilitazione().equals("DF")){
    %>
    <li class="nav-item">
        <a href="Dipendenti/homeDipendenti.jsp" class="nav-link"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a href="messaggiDipendenti.jsp" class="nav-link">Messaggi</a>
    </li>
    <li class="nav-item">
        <a href="venditafinita.jsp" class="nav-link">Vendi</a>
    </li>
    <%
        }
    %>
    <li class="nav-item">
        <a class="nav-link" href="login.jsp">Logout</a>
    </li>
</ul>
<form class="w-50 h-50 m-3 p-3" style="" action="nuovopaziente.do" method="post">
    <div class="form-group"><label>Citta'</label>
        <input class="form-control" name="citta" placeholder="Citta'" type="text"> </div>
    <div class="form-group"><label>CAP</label>
        <input class="form-control" name="cap" placeholder="cap" type="text"> </div>
    <div class="form-group"><label>Via</label>
        <input class="form-control" name="via" placeholder="via" type="text"> </div>
    <div class="form-group"><label>Numero Civico</label>
        <input class="form-control" name="numero" placeholder="numero civico" type="text"> </div>
    <div class="form-group"><label>Telefono</label>
        <input class="form-control" name="telefono" placeholder="telefono" type="text"> </div>
    <button type="submit" class="btn btn-primary btn-block">Registra</button>
</form>
<%
}
else{
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
