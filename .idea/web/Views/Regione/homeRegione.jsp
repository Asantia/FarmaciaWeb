<%--
  Created by IntelliJ IDEA.
  User: csantia
  Date: 8/9/2017
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="userCon" scope="session" class="Beans.UtenteConnessoBean" />

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> </head>

<body>
<%
    if(userCon.getConnesso()==true && userCon.getAbiliazione().equals("reg")){
%>
<ul class="nav nav-pills">
    <li class="nav-item">
        <a href="#" class="active nav-link"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="messaggiRegione.html">Messaggi</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="registraNuovaFarmacia.html">Registra una nuova farmacia</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="file:///C:/Users/csantia/Desktop/Prog%20ALICE/webProva/login.html">Logout</a>
    </li>
</ul>
<div class="col-md-8">
    <img src="http://www.thegoodshoppingguide.com/wp-content/uploads/2013/03/vitamins.jpg" alt="pillole" style="width:930px;height:800px;">
</div>
}
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
