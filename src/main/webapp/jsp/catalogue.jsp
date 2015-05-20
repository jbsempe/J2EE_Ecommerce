<%@page import="epsi.dao.PlatHome" %>
<%@page import="epsi.dao.ProduitHome" %>
<%@page import="epsi.model.Plat"%>
<%@page import="epsi.dao.MenuHome" %>
<%@page import="epsi.model.Menu"%>
<%@page import="epsi.model.Panier"%>
<%@page import="epsi.model.PanierContient"%>
<%@page import="epsi.model.Produit"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp" %>

<section class="container">
		<div class="sidebar">
			<form action="/app/recherche" method="post">
            <h3>Type de cuisine</h3>
            <div class="radio">
              <label>
                <input type="radio" name="type-cuisine" id="type-cuisine1" value="1" checked >
                Francaise
              </label>
            </div>
            <div class="radio">
              <label>
                <input type="radio" name="type-cuisine" id="type-cuisine2" value="2">
                Mexicain
              </label>
            </div>

            <h3>Personnes</h3>
            <div class="radio">
              <label>
                <input type="radio" name="nb-personnes" id="nb-personnes1" value="1" checked>
                1 personne
              </label>
            </div>
            <div class="radio">
              <label>
                <input type="radio" name="nb-personnes" id="nb-personnes2" value="2">
                2 personnes
              </label>
            </div>
            <div class="radio">
              <label>
                <input type="radio" name="nb-personnes" id="nb-personnes3" value="3">
                3 personnes
              </label>
            </div>

            <h3>Temperature</h3>
            <div class="radio">
              <label>
                <input type="radio" name="temperature" id="chaud" value="1" checked>
                Chaud
              </label>
            </div>
            <div class="radio">
              <label>
                <input type="radio" name="temperature" id="froid" value="0">
                Froid
              </label>
            </div>
            <input type="submit">
            </form>
        </div>
        <div class="catalogue-list">
        <h1>Liste des plats</h1>
        <p class="info">${ message }</p>

<%
	List<Plat> plats = (List<Plat>) request.getAttribute("plats");	
	List<Long> panier = (List<Long>) request.getAttribute("panier");
	
	for(Plat plat: plats){
		%>
		<div class="product">
            <div class="product-image" style="background-image: url('<%= plat.getPosterPath() %>')"></div>
            <form method="post" action="/app/ajoutPanier?id=<%= plat.getIdProduit()%>">
				<input type="hidden" class="nbre" id="nbre" name="nbre" value="1" />
	            <% if(request.getSession().getAttribute("user") != null && !panier.contains(plat.getIdProduit())){%>
	            <div class="add-to-cart">
	                <a href="/app/ajoutPanier?id=<%= plat.getIdProduit()%>"><button>Ajouter au panier</button></a>
	                <div class="quantite">
                        <div class="remove"><a href="#" class="remove-quantite">-</a></div>
                        <div class="number">1</div>
                        <div class="add"><a href="#" class="add-quantite">+</a></div>
                    </div>
	            </div>
	            <% } %>
            </form>
            <div class="product-name"><a href="/app/plats?id=<%= plat.getIdProduit()%>"><%= plat.getDesignation()  %></a></div>
            <div class="product-description"><%= plat.getDescription() %></div>
            <div class="product-price">
                <img src="images/price.png" alt="">
                <div><%= plat.getPrix() %>&#x20AC;</div>
            </div>
        </div>
	<%}%>
</div>
</section>



<%@include file="footer.jsp" %>