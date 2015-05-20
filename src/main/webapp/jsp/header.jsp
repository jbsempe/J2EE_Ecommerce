<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@page import="epsi.model.User"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href='http://fonts.googleapis.com/css?family=Montserrat|Sacramento' rel='stylesheet' type='text/css'>
	<script src="/static/js/jquery.js" type="text/javascript"></script>
	<script src="/static/js/bootstrap.js" type="text/javascript"></script>
	<script src="/static/js/main.js" type="text/javascript"></script>
	<link href="/static/css/bootstrap.css" rel="stylesheet"/>
	<link href="/static/css/style.css" rel="stylesheet"/>
	
	<title>La Pate de cannard</title>
</head>
<body>
	<header>
        <nav>
            <div class="logo"><a href="/app"><img src="/static/images/lpdc_logo2.png" alt=""></a></div>
            <ul>
            	<a href="/app/catalogue"><li>Catalogue</li></a>
            	<a href="/app/traiteur"><li>Traiteurs</li></a>
                <a href="/app/panier"><li><img src="/static/images/cart_menu.png" alt=""></li></a>
            </ul>
        </nav>
    </header>
    <%
		if(request.getSession().getAttribute("user") == null){												
	%>
	<div class="user-logged-in"><p>Pas encore inscrit? 
		<a href="/jsp/inscription.jsp">S'inscrire</a></p>
	</div>
    <div class="login-form">
        <form action="/app/login" method="post">
            <div class="login-form-group">
                <label for="email">E-mail</label>
                <input name="email" type="email" id="email" placeholder="Enter email">
            </div>

            <div class="login-form-group">
                <label for="password">Password</label>
                <input name="password" type="password" id="password" placeholder="Password">
            </div>
            <div class="login-form-group"><button type="submit" class="btn-login">Sign in</button></div>
        </form>
    </div>
    <%
		}
		else{
			User user  = (User) request.getSession().getAttribute("user");
	%>
		<div class="user-logged-in">
			<p> Connecté en tant que <%=user.getNom() + " " + user.getPrenom()%>
			<a href="/app/logout">(se déconnecter)</a></p>
		</div>
	<%
		}
	%> 
	<section id="header">
        <div class="jumbotron"></div>
        <div class="hero">
            <h2>Bienvenue sur La Pâte de Canard</h2>
            <p>La Pâte de Canard est devenu en 23 ans un leader incontournable de la vente en ligne produits issus des meilleurs traiteurs. Retrouvez un catalogue riche en produits frais et de qualité.</p>
            <a href="#" class="btn btn-hero">Parcourir</a>
        </div>
    </section>
	