package epsi.front;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Hibernate;

import epsi.dao.PanierContientHome;
import epsi.dao.PanierHome;
import epsi.dao.PlatHome;
import epsi.dao.TypeCuisineHome;
import epsi.exception.PlatNotFoundException;
import epsi.model.Panier;
import epsi.model.PanierContient;
import epsi.model.Plat;
import epsi.model.TypeCuisine;
import epsi.model.User;

public class PlatServlet extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		System.out.println("init: loading plat servlet");
		super.init();
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("GET /plats");
		PlatHome platDao = new PlatHome();
		TypeCuisineHome TCH = new TypeCuisineHome();
		PanierHome panDAO = new PanierHome();
		PanierContientHome PCHDAO = new PanierContientHome();
		User us = (User) req.getSession().getAttribute("user");
		
		try{
			Panier panier = panDAO.findByUser(us);
			List<PanierContient> PC = PCHDAO.findAllPanier(panier);
			ArrayList<Long> plats = new ArrayList<Long>();
			
			for(Iterator<PanierContient> i = PC.iterator(); i.hasNext(); ){
				PanierContient panierList = i.next();
				
				Plat plat = platDao.findById(panierList.getProduit().getIdProduit());
				plats.add(plat.getIdProduit());
				System.out.println("Coucou " + plat.getDesignation());
			}
			req.setAttribute("panier", plats);
		} catch(Exception e){
			e.printStackTrace();
		}
		
		System.out.println("go before try doGet");		
		try{
			System.out.println("go in try doGet");
			
			Long id_Produit = Long.valueOf(req.getParameter("id"));	
			Hibernate.initialize(platDao);
			
			Plat plat = platDao.findById(id_Produit);
			Hibernate.initialize(TCH);
			//int TCid = plat.getTypeCuisine().getIdTypeCuisine();
			
			
			TypeCuisine TC = plat.getTypeCuisine();
			int TCid =TC.getIdTypeCuisine();
			System.out.println(TCid);
			
			//TypeCuisine TC = plat.getTypeCuisine();
			
			String typecuisine = TC.getDesignationTypeCuisine();
			System.out.println(TC.getDesignationTypeCuisine());
			req.setAttribute("plat", plat);
			req.setAttribute("typecuis", typecuisine);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/plat.jsp");
			dispatcher.forward(req, resp);
		}
		catch(NumberFormatException nfe){
			System.err.println(nfe);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/static/404.html");
			dispatcher.forward(req, resp);
		}catch(PlatNotFoundException anfe){
			System.err.println(anfe);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/static/404.html");
			dispatcher.forward(req, resp);
		}
	}
	
}
