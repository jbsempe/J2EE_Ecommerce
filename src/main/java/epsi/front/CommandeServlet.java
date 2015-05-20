
package epsi.front;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsi.business.TokenService;
import epsi.dao.CommandeContientHome;
import epsi.dao.CommandeHome;
import epsi.dao.PanierContientHome;
import epsi.dao.PanierHome;
import epsi.dao.TokenDao;
import epsi.dao.UserDao;
import epsi.dao.UserHome;
import epsi.model.Commande;
import epsi.model.CommandeContient;
import epsi.model.CommandeContientId;
import epsi.model.Panier;
import epsi.model.PanierContient;
import epsi.model.User;
import epsi.model.Plat;
import epsi.dao.PlatHome;
import epsi.model.Menu;
import epsi.dao.MenuHome;
import epsi.exception.UserNotFoundException;

public class CommandeServlet extends HttpServlet{
	
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
		
		PanierContientHome PCH = new PanierContientHome();
		PanierHome PH = new PanierHome();
		CommandeHome cmdDAO = new CommandeHome();
		CommandeContientHome CCH = new CommandeContientHome();
		
		User us = (User) req.getSession().getAttribute("user");
		Panier panUser = PH.findByUser(us);
		List<PanierContient> listPCUser = PCH.findAllPanier(panUser);
		Set<CommandeContient> listCCUser = new HashSet<>();
		
		List<Commande> listC = cmdDAO.find();
		int idComm = listC.size() +1;
		
		Commande lacom = new Commande();
		lacom.setLivraison("en scooter sauvage");
		lacom.setUser(us);
		lacom.setIdCommande(idComm);
		
		for(PanierContient lepc : listPCUser)
		{
			CommandeContient CCuser = new CommandeContient();
			CommandeContientId CCuserid = new CommandeContientId();
			CCuserid.setIdCommande(idComm);
			CCuserid.setIdProduit((int) lepc.getProduit().getIdProduit());
			CCuser.setId(CCuserid);
			CCuser.setProduit(lepc.getProduit());
			CCuser.setNombreProduit(lepc.getNombreProduit());
			
			listCCUser.add(CCuser);
		}
		
		lacom.setCommandeContients(listCCUser);
		
		
		
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/catalogue.jsp");
		dispatcher.forward(req, resp);
	}
	
}
