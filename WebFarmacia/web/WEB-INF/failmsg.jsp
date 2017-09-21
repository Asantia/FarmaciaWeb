<%--
  Created by IntelliJ IDEA.
  User: csantia
  Date: 9/21/2017
  Time: 2:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>failmsg</title>
</head>
<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnessoBean" />
<h1>Invio fallito.</h1>
<% if(userCon.getAbilitazione().equals("RE")){ %>
    <h1><a href="../messaggiRegione.jsp">Torna ai messaggi</a></h1>
<% } %>
<% if(userCon.getAbilitazione().equals("TF")){ %>
<h1><a href="../messaggiTitolare.jsp">Torna ai messaggi</a></h1>
<% }
    else { %>
<h1><a href="../messaggiDipendenti.jsp">Torna ai messaggi</a></h1>
<% } %>

</body>
</html>
