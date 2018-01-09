<%@page import="utils.SellUtil" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> </head>

<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnessoBean" />
<%
    if(userCon.getConnesso() && (userCon.getAbilitazione().equals("DF") || (userCon.getAbilitazione().equals("OB")))){
%>
<ul class="nav nav-pills" style="">    <li class="nav-item">
    <a href="Dipendenti/homeDipendenti.jsp" class="nav-link"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
</li>
    <li class="nav-item">
        <a href="messaggiDipendenti.jsp" class="nav-link">Messaggi</a>
    </li>
    <li class="nav-item">
        <a href="#" class="nav-link active">Vendi</a>
    </li>
    <li class="nav-item">
        <a href="login.jsp" class="nav-link">Logout</a>
    </li>
</ul>
<div class="row">
    <div class="col-md-12">
        <table id="listout" class="table w-100 h-100 mx-5">
            <thead>
            <tr><th>Nome</th><th>Dosaggio</th><th>Prezzo</th><th>Ricetta</th><th>Quantita'</th></tr>
            </thead>
            <tbody>
            <%SellUtil lista = new SellUtil(); %>
            <%=lista.listout(userCon.getIdFarmacia(), userCon.getAbilitazione())%>
            </tbody>
        </table>
    </div>
</div>
<div class="col-md-4">
    <a class="btn btn-primary btn-lg btn-block" href="cercaPazienteTitolare.jsp">Procedi alla vendita</a>
</div>
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