<%--
  Created by IntelliJ IDEA.
  User: csantia
  Date: 8/9/2017
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> </head>

<body>
<ul class="nav nav-pills" style="">
    <li class="nav-item">
        <a class="nav-link" href="homeRegione.html" > <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" href="#" >Messaggi</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="registraNuovaFarmacia.html">Registra una nuova farmacia</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="file:///C:/Users/csantia/Desktop/Prog%20ALICE/webProva/login.html">Logout</a>
    </li>
</ul>
<div class="row p-1">
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="#">Posta Ricevuta </a>
    </div>
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="#">Posta Inviata </a>
    </div>
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="#">Nuovo Messaggio </a>
    </div>
</div>
<div class="row p-1">
    <form class="w-100 h-100 m-3 p-3" style="">
        <div class="form-group"> <label>Destinatario</label>
            <input class="form-control" placeholder="Enter email" type="email"> </div>
        <div class="form-group"> <label>Oggetto</label>
            <input class="form-control" placeholder="oggetto" type="text"> </div>
        <div class="form-group"><label>Messaggio</label>
            <input class="form-control" placeholder="Text" type="text"> </div>
    </form>
</div>
<div class="row p-1">
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="messaggiRegione.html">Invia</a>
    </div>
</div>
</body>

</html>
