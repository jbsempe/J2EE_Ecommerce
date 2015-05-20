<%@ page import="java.sql.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="epsi.front.CreationClientServlet" %>
<%@include file="header.jsp" %>
<section class="container">
    <form method="post" action="/app/inscription" class="registration">
        <h1>Inscription</h1>
        <em>Rejoindre La P&#226;te de Canard</em>
        
        <%
		// Récupération du message d'erreur
		String erreur2 = (String) request.getAttribute("erreur2");
		// Affichage du message s'il existe
		if (erreur2 != null) { %>
		<strong>Erreur : </strong>
		<%
		} %>
		<p class="info">${ message }</p>

        <label for="nomClient">Nom <span class="requis">*</span></label>
        <input type="text" id="nomClient" name="nomClient" value="" size="30" maxlength="30" placeholder="Nom" />
        
        <label for="prenomClient">Pr&#233;nom </label>
        <input type="text" id="prenomClient" name="prenomClient" value="" size="30" maxlength="30" placeholder="Pr&#233;nom" />
            
        <label for="adresseClient">Adresse de livraison <span class="requis">*</span></label>
        <input type="text" id="adresseClient" name="adresseClient" value="" size="30" maxlength="60" placeholder="Adresse de livraison" />
        
        <label for="telephoneClient">Num&#233;ro de t&#233;l&#233;phone <span class="requis">*</span></label>
        <input type="text" id="telephoneClient" name="telephoneClient" value="" size="30" maxlength="30" placeholder="+33" />
        
        <label for="emailClient">Adresse email<span class="requis">*</span></label>
        <input type="email" id="emailClient" name="emailClient" value="" size="30" maxlength="60" placeholder="Email" />
        
        <label for="mdp">Mot de passe <span class="requis">*</span></label>
        <input type="password" id="mdp" name="mdpClient" value="" size="30" maxlength="30" placeholder="&#x25cf;&#x25cf;&#x25cf;&#x25cf;&#x25cf;&#x25cf;" />
        
        <label for="mdp2">R&#233;petez mot de passe <span class="requis">*</span></label>
        <input type="password" id="mdp2" name="mdp2Client" value="" size="30" maxlength="30" placeholder="&#x25cf;&#x25cf;&#x25cf;&#x25cf;&#x25cf;&#x25cf;" />
        
        <label for="datenaiss">Votre date de naissance</label>
        <input type="date" id="datenaiss" name="datenaissClient" value="" size="30" maxlength="60" placeholder="jj/mm/yyyy" />
        
        <button type="submit" value="S'inscrire" class="btn-register" name="submit">S'inscrire</button>
        <input type="reset" value="R&#233;initialiser" class="btn-reset" /> 
    </form>
</section>
						
<%@include file="footer.jsp" %>