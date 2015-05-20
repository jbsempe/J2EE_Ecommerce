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

import org.hibernate.Session;

import epsi.dao.PanierContientHome;
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
 * Servlet implementation class SupprimePanierServlet
 */
@WebServlet("/SupprimePanierServlet")
public class SupprimePanierServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	public static final String VUE = "/app/panier";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SupprimePanierServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PanierHome panDAO = new PanierHome();
		User us = (User) request.getSession().getAttribute("user");
        ProduitHome prodDao = new ProduitHome();
		
        String message;
		try {
			
			
			
			Panier panier = panDAO.findByUser(us);
			request.setAttribute("panier", panier);
			 PanierContient pc = new PanierContient(); 
			 PanierContientHome pch = new PanierContientHome();  
		        int foo = Integer.parseInt(request.getParameter("id"));
		        long foo2 = (long) foo;
		        Produit prod = prodDao.findById(foo2);
		        pc = pch.findByPanierProduit(panier, prod);
		        
		        EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
	    		EntityManager em = emf.createEntityManager();
	    		
	    		EntityTransaction transaction = em.getTransaction();
	    		transaction.begin();
	    		
	    		
	    		pc = em.merge(pc); // merge and assign a to the attached entity 
	    	    em.remove(pc); // remove the attached entity
	    	    
	    		transaction.commit();
	    	
	    		message = "Le produit a bien été supprimer du panier.";
	    		
		} catch (MenuNotFoundException | UserNotFoundException e) {
			e.printStackTrace();
		}
		
	        Panier lepanier = (Panier) request.getAttribute("panier");
	       
	        
	        
		        this.getServletContext().getRequestDispatcher( VUE ).forward( request, response );
	           
	        
	}


}
