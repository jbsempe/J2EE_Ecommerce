package epsi.front;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import epsi.business.AuthenticationService;
import epsi.business.TokenService;
import epsi.dao.TokenDao;
import epsi.dao.UserDao;
import epsi.exception.AuthenticationException;
import epsi.exception.UserNotFoundException;
import epsi.model.User;

public class LoginServlet extends HttpServlet{
	
	@Override
	public void init() throws ServletException {
		System.out.println("init: login  servlet");
		super.init();
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		System.out.println("POST /login");
		
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		
		Boolean rememberMe = !(req.getParameter("rememberme") == null);
		
		System.out.println("Received: email = " + email + ", password = " + password + ", remember me = " + rememberMe);
		
		AuthenticationService authenticationService = new AuthenticationService(new UserDao());

		try{
			User user = authenticationService.login(email, password);
			req.getSession().setAttribute("user", user);
			System.out.println("Login servlet : user " + user.getEmail() + " is logged in");
			
			if(rememberMe){
				TokenService tokenService = new TokenService(new TokenDao());
				String token =  tokenService.generateToken(user);
				resp.addCookie(new Cookie("loginCookie", token));
			}
		}catch(AuthenticationException ex){
			req.setAttribute("authenticationError", true);
		}
		
		// TODO user HTTP referer for proper dispatch
		RequestDispatcher dispatcher = req.getRequestDispatcher("/app");
		dispatcher.forward(req, resp);

	}
	
}
