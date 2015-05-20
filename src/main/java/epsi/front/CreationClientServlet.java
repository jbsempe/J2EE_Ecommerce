package epsi.front;

import java.io.IOException;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsi.dao.UserDao;
import epsi.model.Panier;
import epsi.model.User;

@SuppressWarnings("serial")
public class CreationClientServlet extends HttpServlet {
	
	
	
	    public static final String CHAMP_NOM       = "nomClient";
	    public static final String CHAMP_PRENOM    = "prenomClient";
	    public static final String CHAMP_ADRESSE   = "adresseClient";
	    public static final String CHAMP_TELEPHONE = "telephoneClient";
	    public static final String CHAMP_EMAIL     = "emailClient";
	    public static final String CHAMP_MDP   	   = "mdpClient";
	    public static final String CHAMP_MDP2      = "mdp2Client";
	    public static final String CHAMP_DATENAISS = "datenaissClient";
	 
	    public static final String ATT_CLIENT      = "client";
	    public static final String ATT_MESSAGE     = "message";
	    public static final String ATT_ERREUR      = "erreur";
	 
	    public static final String VUE             = "/app";
	    public static final String ERREUR             = "/jsp/inscription.jsp";
	    
	
	    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException{
	      	
	    	
	        String nom = request.getParameter( CHAMP_NOM );
	        String prenom = request.getParameter( CHAMP_PRENOM );
	        String adresse = request.getParameter( CHAMP_ADRESSE );
	        String telephone = request.getParameter( CHAMP_TELEPHONE );
	        String email = request.getParameter( CHAMP_EMAIL );
	        String mdp = request.getParameter( CHAMP_MDP );
	        String mdp2 = request.getParameter( CHAMP_MDP2 );
	        String datenaiss = request.getParameter( CHAMP_DATENAISS );

	        String message;
	        boolean erreur;
	        
	        User user = null;
	        if ( nom.trim().isEmpty() || adresse.trim().isEmpty() || telephone.trim().isEmpty() || mdp.trim().isEmpty() || email.trim().isEmpty() ) {
	        	message = "Erreur- Vous n'avez pas rempli tous les champs obligatoires.";
	            erreur = true;
	            request.setAttribute( ATT_CLIENT, user );
		        request.setAttribute( ATT_MESSAGE, message );
		        request.setAttribute( ATT_ERREUR, erreur );
	            this.getServletContext().getRequestDispatcher( ERREUR ).forward( request, response );
	            
	        } 
	        else if (mdp.contentEquals(mdp2))
	        {
	            message = "Nous avons bien recu votre demande d'inscription. Veuilez dès à present vous connecter juste au dessus.";
	            erreur = false;
	            
	            user = new User();
	            user.setNom( nom );
	            user.setPrenom( prenom );
	            user.setAdresse( adresse );
	            user.setTelephone( telephone );
	            user.setEmail( email );
	            user.setPassword( mdp );
	            user.setDateNaissance( datenaiss );
	         
	            //Création de l'utilisateur
	    		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
	    		EntityManager em = emf.createEntityManager();
	    		
	    		EntityTransaction transaction = em.getTransaction();
	    		transaction.begin();
	    		
	    		em.persist(user);
	    		transaction.commit();
	    		em.close();
	    		emf.close();
	    		
	    		//Création du panier automatiquement après l'ajout de l'utilisateur
    		 	Panier panier = new Panier();
	            java.util.Date date= new java.util.Date();
	            panier.setDateTime(date);
	            panier.setUser(user);
	            
	            EntityManagerFactory emf2 = Persistence.createEntityManagerFactory("musciPU");
	    		EntityManager em2 = emf2.createEntityManager();
	    		
	    		EntityTransaction transaction2 = em2.getTransaction();
	    		transaction2.begin();
	            em2.persist(panier);
	    		transaction2.commit();
		            
	    		request.setAttribute( ATT_CLIENT, user );
		        request.setAttribute( ATT_MESSAGE, message );
		        request.setAttribute( ATT_ERREUR, erreur );
		        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	           
	        }
	        else
	        {
	        	message = "Erreur - vos mots de passes ne sont pas identiques.";
	        	erreur = true;
	        	request.setAttribute( ATT_CLIENT, user );
		        request.setAttribute( ATT_MESSAGE, message );
		        request.setAttribute( ATT_ERREUR, erreur );
	        	this.getServletContext().getRequestDispatcher( ERREUR ).forward( request, response );
	        	
	        }
	        
	        
	         
	    }
	    public void doGet( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException
		{
			this.getServletContext().getRequestDispatcher( "/creerClient.jsp" ).forward( request, response );
		}
	}

