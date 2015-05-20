package epsi.front;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsi.dao.PanierHome;
import epsi.dao.ProduitHome;
import epsi.exception.MenuNotFoundException;
import epsi.exception.UserNotFoundException;
import epsi.model.Panier;
import epsi.model.PanierContient;
import epsi.model.PanierContientId;
import epsi.model.Produit;
import epsi.model.User;



/**
 * Servlet implementation class AjoutPanierServlet
 */
@WebServlet("/AjoutPanierServlet")
public class AjoutPanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String CHAMP_NOMBRE = "nbre";
	public static final String ATT_MESSAGE = "message";
       
	public static final String VUE = "/app/catalogue";
	
    public AjoutPanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				String nbre_produit = request.getParameter( CHAMP_NOMBRE );
				PanierHome panDAO = new PanierHome();
				User us = (User) request.getSession().getAttribute("user");
		        ProduitHome prodDao = new ProduitHome();
				
		        String message;
				try {
					Panier panier = panDAO.findByUser(us);
					request.setAttribute("panier", panier);
					 PanierContient pc = new PanierContient();
				        pc.setPanier(panier);
				        
				        int foo = Integer.parseInt(request.getParameter("id"));
				        long foo2 = (long) foo;
				        Produit prod = prodDao.findById(foo2);

				        
				        pc.setProduit(prod);
				        
				        int nb = Integer.parseInt(nbre_produit);
				        pc.setNombreProduit(nb);
				        
				        PanierContientId PCI = new PanierContientId();
				        int bar = (int) prod.getIdProduit();
				        PCI.setIdProduits(bar);
				        
				        int bar2 = (int) panier.getIdPanier();
				        PCI.setIdPanier(bar2);
				        
				        pc.setId(PCI);
				        
				        EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
			    		EntityManager em = emf.createEntityManager();
			    		
			    		EntityTransaction transaction = em.getTransaction();
			    		transaction.begin();
			    		
			    		em.persist(pc);
			    		transaction.commit();
			    		
			    		message = "Le produit a bien été ajouté au panier.";
			    		request.setAttribute( ATT_MESSAGE, message );
			    		
				} catch (MenuNotFoundException | UserNotFoundException e) {
					e.printStackTrace();
				}
				
			        Panier lepanier = (Panier) request.getAttribute("panier");
			       
			        
			        
				        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
			           
			        
			}
	}


