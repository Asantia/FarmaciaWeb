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
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnessoBean"/>
<%
    if(userCon.getConnesso() && userCon.getAbilitazione().equals("TF")){
%>
<ul class="nav nav-pills" style="">
    <li class="nav-item">
        <a href="Titolare/homeTitolare.jsp" class="nav-link"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a href="messaggiTitolare.jsp" class="nav-link active">Messaggi</a>
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
    <li class="nav-item">
        <a class="nav-link" href="login.jsp">Logout</a>
    </li>
</ul>
<div class="row p-1">
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="messaggiRicevutiTitolare.jsp">Posta Ricevuta </a>
    </div>
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="messaggiInviatiTitolare.jsp">Posta Inviata </a>
    </div>
    <div class="col-md-4">
        <a class="btn btn-primary btn-lg btn-block" href="#">Nuovo Messaggio </a>
    </div>
</div>
<div class="row p-1">
    <form class="w-100 h-100 m-3 p-3" style="" method="post" action="nuovomsg.do">
    Invia a: <select name="emaildest">
    <option value="regionepiemonte@gmail.com">Regione</option>
    <option value="alldipendenti">Tutti i dipendenti</option>
        <%
            Connection conn = null;
            PreparedStatement st = null;
            ResultSet rs=null;
            try {
                Class.forName("org.postgresql.Driver");
                conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "$Ultraheroes1");
                String query="SELECT email from utente where idfarmacia=? AND email<>?";
                st = conn.prepareStatement(query);
                st.setInt(1, userCon.getIdFarmacia());
                st.setString(2, userCon.getEmail());
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
    <h2> <a href="login.jsp">Login</a></h2>
</div>
<%
    }
%>
</body>

</html>