<%@page import="epsi.model.Plat"%>
<%@page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@include file="header.jsp" %>


<%
Plat plat = (Plat) request.getAttribute("plat");
List<Long> panier = (List<Long>) request.getAttribute("panier");
String typecuisine = (String) request.getAttribute("typecuis");

%>
<section class="container">
	<div class="product-infos">
	    <div class="product-image" style="background-image: url('<%= plat.getPosterPath() %>')">
	        <div class="product-price">
	            <img src="/static/images/price.png" alt="">
	            <div><%= plat.getPrix() %>&#x20AC;</div>
	        </div>
	    </div>
	    <h2><%= plat.getDesignation() %></h2>
	    <em class="type-cuisine">Cuisine <%= typecuisine %></em>
	    <p><%= plat.getDescription() %></p>
	    <em class="nb-personnes">Pour <%= plat.getNbPersonne() %> personne</em>
	    <em class="plat-chaud">
	    <% if(plat.isEstChaud() == true)
	    	out.println("Plat Chaud");
	   	   else if (plat.isEstChaud() == false)
	   		out.println("Plat Froid"); %>
	    </em>
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
	</div>
</section>


<%@include file="footer.jsp" %>