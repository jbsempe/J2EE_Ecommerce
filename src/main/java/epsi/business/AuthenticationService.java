package epsi.business;

import epsi.dao.UserDao;
import epsi.exception.AuthenticationException;
import epsi.exception.UserNotFoundException;
import epsi.model.User;

public class AuthenticationService {
	
	private UserDao userDao;
	
	public AuthenticationService(UserDao userDao){
		
		this.userDao = userDao;
	}
	
	public User login(String username, String password) throws AuthenticationException{
		
		try{
			User user = userDao.findByUserName(username);
			
			if(!user.getPassword().equals(password)){
				System.out.println("Authentication service: password doesn't match for user " + username);
				throw new AuthenticationException();
			}
			
			return user;
			
		}catch(UserNotFoundException ex){
			System.out.println("Authentication service: user " + username + " not found");
			throw new AuthenticationException();
		}
	}

}
