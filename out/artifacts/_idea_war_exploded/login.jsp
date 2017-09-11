<%--
  Created by IntelliJ IDEA.
  User: csantia
  Date: 8/9/2017
  Time: 12:14 PM
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
<div class="py-5">
    <div class="container">
        <div class="row">
            <div class="h-25 bg-inverse">
                <form class="p-2 m-5" action="/.idea/src/Actions/LoginAction.java" method="post">
                    <div class="form-group"> <label>Email address</label>
                        <input class="form-control" placeholder="Enter email" type="email"> </div>
                    <div class="form-group"> <label>Password</label>
                        <input class="form-control" placeholder="Password" type="password"> </div>
                    <button type="submit" class="btn btn-primary" value="Login">Login</button>
                </form>
            </div>
            <div class="col-md-8 text-white">
                <h1 class="text-primary display-4 w-100 m-5 p-5">Farmacie del Piemonte
                    <br>
                </h1>
            </div>
        </div>
    </div>
</div>
<img src="http://www.thegoodshoppingguide.com/wp-content/uploads/2013/03/vitamins.jpg" alt="pillole" style="width:1500px;height:800px;">
</body>

</html>
