<%@page import="epsi.model.Traiteur"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="utf-8"%>
<%@include file="header.jsp" %>

<section class="container" id="catalogue">
	<h1>Nos Traiteurs</h1>
<%
List<Traiteur> lestraiteurs =(List<Traiteur>) request.getAttribute("traiteurs");

for(Traiteur traiteur: lestraiteurs){
%>

<div class="traiteurs-list">
    <div class="traiteur">
        <div class="traiteur-image" style="background-image: url('<%= traiteur.getPosterPath() %>')"></div>
        <div class="traiteur-name"><a href="/app/plattraiteur?id=<%= traiteur.getIdTraiteur()%>"><%= traiteur.getNomTraiteur() %></a></div>
        <div class="traiteur-address"><%= traiteur.getAdresse() %></div>
        <div class="traiteur-telephone"><%= traiteur.getTelTraiteur() %></div>
    </div>
</div>
<%

}
%>
</section>

<%@include file="footer.jsp" %>