<html>

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
    <link rel="stylesheet" href="https://pingendo.github.io/templates/blank/theme.css" type="text/css"> </head>

<body>
<ul class="nav nav-pills" style="">    <li class="nav-item">
        <a href="homeDipendenti.jsp" class="nav-link"> <i class="fa fa-home fa-home"></i>&nbsp;Home</a>
    </li>
    <li class="nav-item">
        <a href="messaggiDipendenti.jsp" class="nav-link">Messaggi</a>
    </li>
    <li class="nav-item">
        <a href="#" class="nav-link active">Vendi</a>
    </li>
    <li class="nav-item">
        <a href="/out/artifacts/_idea_war_exploded/login.jsp" class="nav-link">Logout</a>
    </li>
</ul>
<div class="col-md-12">
    <form class="form-inline my-4 p-3">
        <div class="form-group">
            <input class="form-control" placeholder="cerca" type="farmaco"> </div>
        <button type="submit" class="btn btn-primary mx-3">Cerca</button>
    </form>
</div>
<div class="row">
    <div class="col-md-12">
        <table class="table w-100 h-100 mx-3">
            <thead>
            <tr>
                <th>#</th>
                <th>Farmaco</th>
                <th>Disponibilita'</th>
                <th>Quantita'</th>
                <th>Aggiungi all'ordine</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <td>1</td>
                <td>Mark</td>
                <td>
                    <input class="form-control" placeholder="0" type="disponibilita"> </td>
                <td>
                    <input class="form-control" placeholder="0" type="quantita"> </td>
                <td>
                    <button type="submit" class="btn btn-primary mx-3 w-100">Aggiungi</button>
                </td>
            </tr>
            <tr>
                <td>2</td>
                <td>Jacob</td>
                <td>
                    <input class="form-control" placeholder="0" type="disponibilita"> </td>
                <td>
                    <input class="form-control" placeholder="0" type="quantita"> </td>
                <td>
                    <button type="submit" class="btn btn-primary mx-3 w-100">Aggiungi</button>
                </td>
            </tr>
            <tr>
                <td>3</td>
                <td>Larry</td>
                <td>
                    <input class="form-control" placeholder="0" type="disponibilita"> </td>
                <td>
                    <input class="form-control" placeholder="0" type="quantita"> </td>
                <td>
                    <button type="submit" class="btn btn-primary mx-3 w-100">Aggiungi</button>
                </td>
            </tr>
            </tbody>
        </table>
    </div>
</div>
<div class="col-md-12">
    <form class="form-inline my-4 p-3">
        <button type="submit" class="btn btn-primary mx-3">Conferma</button>
    </form>
</div>
</body>

</html>