<%--
  Created by IntelliJ IDEA.
  User: csantia
  Date: 8/9/2017
  Time: 11:49 AM
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
        <a class="nav-link" href="homeRegione.html"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="messaggiRegione.html">Messaggi</a>
    </li>
    <li class="nav-item">
        <a class="nav-link active" href="#">Registra una nuova farmacia</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="file:///C:/Users/csantia/Desktop/Prog%20ALICE/webProva/login.html">Logout</a>
    </li>
</ul>
<form class="w-50 h-50 m-3 p-3" style="">
    <div class="form-group"> <label>Email address Titolare</label>
        <input class="form-control" placeholder="Enter email" type="email"> </div>
    <div class="form-group"> <label>Password Titolare</label>
        <input class="form-control" placeholder="Password" type="password"> </div>
    <div class="form-group"><label>Nome</label>
        <input class="form-control" placeholder="Nome" type="text"> </div>
    <div class="form-group"><label>Cognome</label>
        <input class="form-control" placeholder="Cognome" type="text"> </div>
    <div class="form-group"><label>Id Farmacia</label>
        <input class="form-control" placeholder="Id Farmacia" type="text"> </div>
    <button type="submit" class="btn btn-primary btn-block">Registra</button>
</form>

</body>

</html>
