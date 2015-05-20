<%@page import="epsi.model.Menu"%>
<%@page import="epsi.model.Produit"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@include file="header.jsp" %>


<%
Menu menu = (Menu) request.getAttribute("menu");
Produit produit = (Produit) request.getAttribute("produit");

%>
<h2><%= menu.getDescription() %> </h2>

<h3><%= produit.getProduitsForIdProduitPlat() %></h3>


<%@include file="footer.jsp" %>