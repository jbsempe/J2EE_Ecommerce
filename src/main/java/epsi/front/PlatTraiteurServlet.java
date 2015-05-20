package epsi.front;

import java.util.List;
import java.util.Set;
import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;

import epsi.dao.PlatHome;
import epsi.dao.TraiteurHome;
import epsi.exception.PlatNotFoundException;
import epsi.model.Plat;
import epsi.model.Produit;
import epsi.model.Traiteur;

/**
 * Servlet implementation class TraiteurServlet
 */

public class PlatTraiteurServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("init: loading plat servlet");
		super.init();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			TraiteurHome traiteurDAO = new TraiteurHome();
			PlatHome platDAO = new PlatHome();
			int id_traiteur =  Integer.parseInt(req.getParameter("id")); 
			List<Traiteur> traits = traiteurDAO.find();
			Traiteur traiteur = null;
			
			for(Traiteur letrait : traits)
			{
				if(id_traiteur == letrait.getIdTraiteur())
				{
					traiteur = letrait;
				}
			}

			List<Plat> listplats = new ArrayList<Plat>();
			Set<Produit> lesprod = traiteur.getProduits();
			
			for(Produit leprod: lesprod)
			{	
				Plat leplat = platDAO.findById(leprod.getIdProduit());
				listplats.add(leplat);
			}
			
			req.setAttribute("plat", listplats);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/plattraiteur.jsp");
			dispatcher.forward(req, resp);
		} catch (PlatNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.doGet(request,response);
	}

}