package epsi.business;

import java.util.Calendar;
import java.util.Date;

import epsi.dao.TokenDao;
import epsi.exception.TokenExpiredException;
import epsi.exception.TokenNotFoundException;
import epsi.model.Token;
import epsi.model.User;

public class TokenService {
	
	private TokenDao tokenDao;
	
	public TokenService(TokenDao tokenDao){
		this.tokenDao = tokenDao;
	}
	
	public String generateToken(User user){
		
		// TODO should be clever than that
		long unixTime = System.currentTimeMillis() / 1000L;
		String tokenValue = "token-" + unixTime;
		
		// Token last 1 day	
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		cal.add(Calendar.DATE, 1);
		Date expiration_date  = cal.getTime();
		
		tokenDao.create(new Token(tokenValue, user, expiration_date));
		
		return tokenValue;
	}

	public User validateToken(String tokenValue) throws TokenNotFoundException, TokenExpiredException{
		
		Token token = tokenDao.findByTokenValue(tokenValue);
		
		if(token.getExpirationDate().compareTo(new Date()) < 0){
			// Token expired
			throw new TokenExpiredException();
		}
		
		return token.getUser();
	}
	
	public void expireToken(String tokenValue){
		try{
			Token token = tokenDao.findByTokenValue(tokenValue);
			tokenDao.delete(token);
		}catch(TokenNotFoundException ex){
			System.out.println("token already destroyed");
		}
	}
}
