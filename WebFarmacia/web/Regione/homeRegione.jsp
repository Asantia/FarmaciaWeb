<%--
  Created by IntelliJ IDEA.
  User: csantia
  Date: 8/9/2017
  Time: 11:37 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="utils.StatsUtil" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> </head>

<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnessoBean" />
<%
    if(userCon.getConnesso()==true && userCon.getAbilitazione().equals("RE")){
%>
<ul class="nav nav-pills">
    <li class="nav-item">
        <a href="#" class="active nav-link"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="../messaggiRegione.jsp">Messaggi</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="../registraNuovaFarmacia.jsp">Registra una nuova farmacia</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="../login.jsp">Logout</a>
    </li>
</ul>
<<div class="row m-1 p-1">
    <div class="col-md-8">
        <img src="http://www.thegoodshoppingguide.com/wp-content/uploads/2013/03/vitamins.jpg" alt="pillole" style="width:930px;height:800px;"> </div>
    <div class="col-md-4">
        <div class="row p-1 text-justify">
            <div class="col-md-12">
                <h1 class="">Top 10 farmaci piu' venduti</h1>
            </div>
        </div>
        <div class="col-md-12">
            <div class="row h-100 ">
                <table id="bestselling"  class="table h-25 mx-4">
                    <thead>
                    <tr><th>Classifica</th><th>Nome Farmaco</th><th>Dosaggio</th><th>Quantita' Venduta</th></tr>
                    </thead>
                    <tbody>
                    <%StatsUtil bestsellinglist = new StatsUtil(); %>
                    <%=bestsellinglist.bestselling()%>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
}
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