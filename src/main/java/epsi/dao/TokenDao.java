package epsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import epsi.exception.TokenNotFoundException;
import epsi.exception.UserNotFoundException;
import epsi.model.Token;
import epsi.model.User;

public class TokenDao {

	public TokenDao(){
	}
	
	public void create(Token token){
		
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.persist(token);
		transaction.commit();
		
		//Close entity manager
		em.close();
		emf.close();
	}
	
	public void update(Token token){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.merge(token);
		transaction.commit();
		
		//Close entity manager
		em.close();
		emf.close();

	}
	
	public void delete(Token token){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.remove(em.contains(token) ? token : em.merge(token));
		transaction.commit();
		
		//Close entity manager
		em.close();
		emf.close();

	}
	
	@SuppressWarnings("unchecked")
	public List<Token> find(){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		List<Token> tokens = em.createQuery("SELECT t FROM Token t").getResultList();
			
		//Close entity manager
		em.close();
		emf.close();
		return tokens;
	}
	
	public Token findByTokenValue(String value) throws TokenNotFoundException{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		try{
			Token token = (Token) em.createQuery("Select t FROM Token t WHERE t.value=:value")
							.setParameter("value", value).getSingleResult();
			return token;
			
		}catch(NoResultException ex){
			throw new TokenNotFoundException();
		}finally{
			em.close();
			emf.close();
		}		

	}
	
}
