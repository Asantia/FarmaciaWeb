<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> </head>

<body>
<ul class="nav nav-pills" style="">
    <li class="nav-item">
        <a href="homeTitolare.jsp" class="nav-link"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a href="#" class="nav-link active">Messaggi</a>
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
        <a class="btn btn-primary btn-lg btn-block" href="messaggiTitolare.jsp">Invia</a>
    </div>
</div>
</body>

</html>