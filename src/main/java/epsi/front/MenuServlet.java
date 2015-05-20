
package epsi.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsi.business.TokenService;
import epsi.dao.PlatHome;
import epsi.dao.TokenDao;
import epsi.dao.UserDao;
import epsi.model.Plat;
import epsi.model.User;
import epsi.model.Menu;
import epsi.dao.MenuHome;
import epsi.exception.MenuNotFoundException;

public class MenuServlet extends HttpServlet{
	
	private String LOGIN_COOKIE = "loginCookie";
	
	@Override
	public void init() throws ServletException {
		System.out.println("init: loading menu servlet");
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
		
		System.out.println("GET /menus");
		MenuHome menuDao = new MenuHome();
		System.out.println("go before try doGet");		
		try{
			System.out.println("go in try doGet");
			Long id_Produit = Long.valueOf(req.getParameter("id"));		
			Menu menu = menuDao.findById(id_Produit);
			req.setAttribute("menu", menu);

			RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/menu.jsp");
			dispatcher.forward(req, resp);
		}
		catch(NumberFormatException nfe){
			System.err.println(nfe);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/static/404.html");
			dispatcher.forward(req, resp);
		}catch(MenuNotFoundException anfe){
			System.err.println(anfe);
			RequestDispatcher dispatcher = req.getRequestDispatcher("/static/404.html");
			dispatcher.forward(req, resp);
		}

		RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/home.jsp");
		dispatcher.forward(req, resp);
	}
	
}
