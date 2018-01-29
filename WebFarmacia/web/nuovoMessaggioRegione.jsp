<%--
  Created by IntelliJ IDEA.
  User: csantia
  Date: 8/9/2017
  Time: 11:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.PreparedStatement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Connection" %>
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
        <a class="btn btn-primary btn-lg btn-block" href="messaggiInviatiRegione.jsp">Posta Inviata </a>
    </div>
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="#">Nuovo Messaggio </a>
    </div>
</div>
<div class="row p-1">
    <form class="w-100 h-100 m-3 p-3" style="" method="post" action="nuovomsg.do">
        Invia a: <select name="emaildest">
        <option value="alltf">Tutte le farmacie</option>
            <%
            Connection conn = null;
            PreparedStatement st = null;
            ResultSet rs=null;
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
                String query="SELECT email from utente where abilitazione='TF'";
                st = conn.prepareStatement(query);
                rs = st.executeQuery();
                String output = "";
                while (rs.next()){
            %>
        <option value="<%=rs.getString(1)%>"> <%=rs.getString(1)%></option>
            <%
                }
            }
            catch (Exception e) {
                System.out.println("Errore di connessione al DB: "+ e.getMessage() );
            }
        %>
        </select>
        <div class="form-group"> <label>Oggetto</label>
            <input class="form-control" name="oggetto" placeholder="oggetto" type="text"> </div>
        <div class="form-group"><label>Messaggio</label>
            <input class="form-control" name="testo" placeholder="Text" type="text"> </div>
        <button type="submit" class="btn btn-primary btn-block">Invia</button>
    </form>
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