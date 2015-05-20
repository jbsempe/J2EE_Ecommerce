<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.sql.*" %>
    <%@page import="epsi.model.Panier"%>
    <%@page import="epsi.model.Plat"%>
    <%@page import="epsi.model.PanierContient"%>
    <%@page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="epsi.front.CreationClientServlet" %>

<%@include file="header.jsp" %>
<% Panier lepanier = (Panier) request.getAttribute("panier"); %>

<section class="container">
	<table class="cart-list">
	    <thead>
	        <tr>
	            <th>Produit</th>
	            <th>Description</th>
	            <th>Prix unitaire</th>
	            <th>Quantité</th>
	            <th>Total</th>
	            <th>Actions</th>
	        </tr>
	    </thead>
	    <tbody>

<%

List<PanierContient> plats = (List<PanierContient>) request.getAttribute("listPlats");

for(PanierContient plat: plats){
%>
		<tr>
			<td><div class="product-image" style="background-image: url('<%= plat.getProduit().getPlat().getPosterPath() %>')"></div></td>
			<td class="product-description">
				<div><%= plat.getProduit().getPlat().getDesignation() %></div>
				<span><%= plat.getProduit().getPlat().getDescription() %></span>
			</td>
			
			<td align="center" width="120px"><span class="price"><%= plat.getProduit().getPlat().getPrix() %>&#x20AC;</span></td>
			<td align="center" width="100px">
				<div class="quantite">
					<div class="number"><%= plat.getNombreProduit() %></div>
				</div>
			</td>
			<td align="center"><span class="total-price"></span>&#x20AC;</td>
			<form method="post" action="/app/supprimePanier?id=<%= plat.getProduit().getIdProduit()%>">
			<td align="center"><button class="remove-item-cart"><img src="/static/images/delete.png" width="20px" alt="Delete"></button></td>
			</form>
		</tr>
<% } %>
		</tbody>
		<tfoot>
			<tr>
            	<td colspan="4"></td>
            	<td align="center"><span class="total-cart"></span>&#x20AC;</td>
            	<td></td>
            </tr>
        </tfoot>
	</table>
</section>

<%@include file="footer.jsp" %>
