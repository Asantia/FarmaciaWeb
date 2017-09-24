<%@ page import="utils.CercaPazienteUtil" %>
<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> </head>

<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnessoBean"/>
<jsp:useBean id="pazCon" scope="session" class="beans.PazienteCercatoBean"/>
<%
    if(userCon.getConnesso() && (userCon.getAbilitazione().equals("TF"))){
%>
<ul class="nav nav-pills" style="">

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
        <a href="registraNuovoDipendente.jsp" class="nav-item">Registra un nuovo dipendente</a>
    </li>
    <li class="nav-item">
        <a class="nav-link" href="login.jsp">Logout</a>
    </li>
    </ul>
            <div class="row m-1">
                <table id="sent"  class="table w-100 h-100 mx-4">
                    <thead>
                    <tr><th>Codice Fiscale</th><th>Nome</th><th>Cognome</th><th>Data di Nascita</th></tr>
                    </thead>
                    <tbody>
                    <%CercaPazienteUtil pazientetrovato = new CercaPazienteUtil(); %>
                    <%=pazientetrovato.cerca(pazCon.getCf(), pazCon.getNome(), pazCon.getCognome(), pazCon.getDatanascita())%>
                    </tbody>
                </table>
            </div>

            <div class="col-md-4">
                <a class="btn btn-primary btn-lg btn-block" href="#">Procedi alla vendita</a>
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