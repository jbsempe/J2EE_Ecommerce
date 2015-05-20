
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
import epsi.dao.UserDao;
import epsi.model.Panier;
import epsi.model.PanierContient;
import epsi.model.User;
import epsi.model.Plat;
import epsi.dao.PlatHome;
import epsi.model.Menu;
import epsi.dao.MenuHome;
import epsi.exception.UserNotFoundException;

public class CatalogueServlet extends HttpServlet{
	
	private String LOGIN_COOKIE = "loginCookie";
	
	@Override
	public void init() throws ServletException {
		System.out.println("init: loading home servlet");
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("GET /");
		
		PanierHome panDAO = new PanierHome();
		PanierContientHome PCHDAO = new PanierContientHome();
		PlatHome platDAO = new PlatHome();
		User us = (User) req.getSession().getAttribute("user");
		
		try{
			Panier panier = panDAO.findByUser(us);
			List<PanierContient> PC = PCHDAO.findAllPanier(panier);
			ArrayList<Long> plats = new ArrayList<Long>();
			
			for(Iterator<PanierContient> i = PC.iterator(); i.hasNext(); ){
				PanierContient panierList = i.next();
				
				Plat plat = platDAO.findById(panierList.getProduit().getIdProduit());
				plats.add(plat.getIdProduit());
				System.out.println("Coucou " + plat.getDesignation());
			}
			req.setAttribute("panier", plats);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		
		PlatHome platDao = new PlatHome();
		req.setAttribute("plats", platDao.find());
		
		MenuHome menuDao = new MenuHome();
		req.setAttribute("menus", menuDao.find());
		
		// Trying to reload context from cookie when the session is new	
		if(req.getSession().isNew()){
			String tokenValue = null;
			
			for(Cookie cookie : req.getCookies()){
				if(LOGIN_COOKIE.equals(cookie.getName())){
					tokenValue = cookie.getValue();
				}
			}
						
			if(tokenValue != null){
				try{
					TokenService tokenService = new TokenService(new TokenDao());
					User user = tokenService.validateToken(tokenValue);
					req.getSession().setAttribute("user", user);
				}catch(Exception e){
					System.out.println("Token " + tokenValue + " is invalid or expired (" + e.getMessage() + ")");
				}
			}
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/catalogue.jsp");
		dispatcher.forward(req, resp);
	}
	
}
