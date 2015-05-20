<%@page import="epsi.dao.PlatHome" %>
<%@page import="epsi.dao.ProduitHome" %>
<%@page import="epsi.model.Plat"%>
<%@page import="epsi.dao.MenuHome" %>
<%@page import="epsi.model.Menu"%>
<%@page import="epsi.model.Panier"%>
<%@page import="epsi.model.PanierContient"%>
<%@page import="epsi.model.Produit"%>
<%@page import="java.util.List" %>
<%@page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@include file="header.jsp" %>

<section class="container">
        <div class="product-list">
        <h1>Liste des plats</h1>

<%
List<Plat> plats = new ArrayList<Plat>();	
plats = (List<Plat>) request.getAttribute("plat");	
	
	for(Plat plat: plats){
		%>
		<div class="product">
            <div class="product-image" style="background-image: url('<%= plat.getPosterPath() %>')"></div>
            <div class="product-name"><a href="/app/plats?id=<%= plat.getIdProduit()%>"><%= plat.getDesignation()  %></a></div>
            <div class="product-price">
                <img src="/static/images/price.png" alt="">
                <div><%= plat.getPrix() %>&#x20AC;</div>
            </div>
            <form method="post" action="/app/ajoutPanier?id=<%= plat.getIdProduit()%>">
				<input type="hidden" class="nbre" id="nbre" name="nbre" value="1" />
	            <% if(request.getSession().getAttribute("user") != null){%>
	            <div class="add-to-cart">
	                <a href="/app/ajoutPanier?id=<%= plat.getIdProduit()%>"><button><img src="/static/images/cart.png" alt=""></button></a>
	                <div class="quantite">
                        <div class="remove"><a href="#" class="remove-quantite">-</a></div>
                        <div class="number">1</div>
                        <div class="add"><a href="#" class="add-quantite">+</a></div>
                    </div>
	            </div>
	            <%} %>
            </form>
        </div>
	<%}%>
</div>
</section>




<%@include file="footer.jsp" %>