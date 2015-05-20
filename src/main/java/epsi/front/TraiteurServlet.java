package epsi.front;

import java.util.List;
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
import epsi.model.Traiteur;

/**
 * Servlet implementation class TraiteurServlet
 */

public class TraiteurServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		System.out.println("init: loading plat servlet");
		super.init();
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		TraiteurHome traiteurDAO = new TraiteurHome();
		List<Traiteur> traiteurs = new ArrayList<Traiteur>();
		traiteurs = traiteurDAO.find();
		
		req.setAttribute("traiteurs", traiteurs);
		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/traiteur.jsp");
		dispatcher.forward(req, resp);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		this.doGet(request,response);
	}

}
