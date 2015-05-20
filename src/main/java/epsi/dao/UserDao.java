package epsi.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.hibernate.Hibernate;

import epsi.exception.UserNotFoundException;
import epsi.model.User;

public class UserDao {

	public UserDao(){
	}
	
	public void create(User user){
		
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.persist(user);
		transaction.commit();
		
		//Close entity manager
		em.close();
		emf.close();
	}
	
	public void update(User user){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.merge(user);
		transaction.commit();
		
		//Close entity manager
		em.close();
		emf.close();

	}
	
	public void delete(User user){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		em.remove(em.contains(user) ? user : em.merge(user));
		transaction.commit();
		
		//Close entity manager
		em.close();
		emf.close();

	}
	
	@SuppressWarnings("unchecked")
	public List<User> find(){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		List<User> users = em.createQuery("SELECT u FROM User u").getResultList();
			
		//Close entity manager
		em.close();
		emf.close();
		return users;
	}
	
	public User findByUserName(String username) throws UserNotFoundException{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		try{
			User user = (User) em.createQuery("Select u FROM User u WHERE u.email=:email")
							.setParameter("email", username).getSingleResult();
			return user;
			
		}catch(NoResultException ex){
			throw new UserNotFoundException();
		}finally{
			em.close();
			emf.close();
		}		

	}
	
}
