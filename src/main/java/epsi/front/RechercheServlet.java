
package epsi.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsi.business.TokenService;
import epsi.dao.PanierContientHome;
import epsi.dao.PanierHome;
import epsi.dao.TokenDao;
import epsi.dao.TypeCuisineHome;
import epsi.dao.UserDao;
import epsi.model.Panier;
import epsi.model.PanierContient;
import epsi.model.TypeCuisine;
import epsi.model.User;
import epsi.model.Plat;
import epsi.dao.PlatHome;
import epsi.model.Menu;
import epsi.dao.MenuHome;
import epsi.exception.UserNotFoundException;

public class RechercheServlet extends HttpServlet{
	
	private String LOGIN_COOKIE = "loginCookie";
	public static final String CHAMP_TYPE = "type-cuisine";
	public static final String CHAMP_PERSONNES = "nb-personnes";
	public static final String CHAMP_TEMPERATURE = "temperature";
	
	@Override
	public void init() throws ServletException {
		System.out.println("init: loading home servlet");
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String type_cuisine = req.getParameter( CHAMP_TYPE );
		String nb_personnes = req.getParameter( CHAMP_PERSONNES );
		String temperature = req.getParameter( CHAMP_TEMPERATURE );
		
		TypeCuisineHome typeDAO = new TypeCuisineHome();
		int int_type_cuisine = Integer.parseInt(type_cuisine);
		TypeCuisine cuisine = new TypeCuisine();
		cuisine.setIdTypeCuisine(int_type_cuisine);
		boolean estChaud = Boolean.valueOf(temperature);
		
		PlatHome platDAO = new PlatHome();
		List<Plat> plats = platDAO.findByParams(cuisine, Integer.parseInt(nb_personnes), estChaud);
		
		req.setAttribute("plats", plats);
		
		PanierHome panDAO = new PanierHome();
		PanierContientHome PCHDAO = new PanierContientHome();
		User us = (User) req.getSession().getAttribute("user");
		
		try{
			Panier panier = panDAO.findByUser(us);
			List<PanierContient> PC = PCHDAO.findAllPanier(panier);
			ArrayList<Long> listPanier = new ArrayList<Long>();
			
			for(Iterator<PanierContient> i = PC.iterator(); i.hasNext(); ){
				PanierContient panierList = i.next();
				
				Plat plat = platDAO.findById(panierList.getProduit().getIdProduit());
				listPanier.add(plat.getIdProduit());
				System.out.println("Coucou " + plat.getDesignation());
			}
			req.setAttribute("panier", listPanier);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/catalogue.jsp");
		dispatcher.forward(req, resp);
		
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		
	}
	
}
