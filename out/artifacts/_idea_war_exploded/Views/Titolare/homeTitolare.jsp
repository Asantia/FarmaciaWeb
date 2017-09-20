<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> </head>

<body>
<jsp:useBean id="userCon" scope="session" class="beans.UtenteConnessoBean"/>
<%
    if(userCon.getConnesso()==true && userCon.getAbiliazione().equals("tf")){
%>
<ul class="nav nav-pills" style="">
    <li class="nav-item">
        <a href="#" class="nav-link active"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
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
    <li class="nav-item">
        <a class="nav-link" href="/out/artifacts/_idea_war_exploded/login.jsp">Logout</a>
    </li>
</ul>
<div class="row m-1 p-1">
    <div class="col-md-8">
        <img src="http://www.thegoodshoppingguide.com/wp-content/uploads/2013/03/vitamins.jpg" alt="pillole" style="width:930px;height:800px;"> </div>
    <div class="col-md-4">
        <div class="row p-1 text-justify">
            <div class="col-md-12">
                <h1 class="">Top 10 farmaci piu' venduti questo mese</h1>
            </div>
        </div>
        <div class="row h-100 bg-inverse">
            <div class="col-md-12">
                <ol class="my-4">
                    <li>One</li>
                    <li>Two</li>
                    <li>Three</li>
                    <li>four</li>
                    <li>five</li>
                    <li>six</li>
                    <li>seven</li>
                    <li>eight</li>
                    <li>nine</li>
                    <li>ten</li>
                </ol>
            </div>
        </div>
    </div>
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