package epsi.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsi.business.TokenService;
import epsi.dao.TokenDao;

public class LogoutServlet extends HttpServlet {

	private String LOGIN_COOKIE = "loginCookie";

	@Override
	public void init() throws ServletException {
		System.out.println("init: login artist servlet");
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		System.out.println("GET /logout");

		req.getSession().invalidate();

		Cookie removedCookie = null;
		for (Cookie cookie : req.getCookies()) {
			if (LOGIN_COOKIE.equals(cookie.getName())) {
				removedCookie = cookie;
			}
		}

		if (removedCookie != null) {
			// remove login cookie
			removedCookie.setMaxAge(0);
			resp.addCookie(removedCookie);

			// expire token in database
			TokenService tokenService = new TokenService(new TokenDao());
			tokenService.expireToken(removedCookie.getValue());
		}
		
		// TODO user HTTP referer for proper dispatch
		RequestDispatcher dispatcher = req.getRequestDispatcher("/app");
		dispatcher.forward(req, resp);

	}

}
