package epsi.dao;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import epsi.model.Menu;
import epsi.model.TypeCuisine;

/**
 * Home object for domain model class TypeCuisine.
 * @see epsi.model.TypeCuisine
 * @author Hibernate Tools
 */
public class TypeCuisineHome {
	
	public TypeCuisineHome(){}


	@PersistenceContext
	private EntityManager entityManager;

	public void create(TypeCuisineHome tch){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.persist(tch);
		transaction.commit();
		
		//Close entity manager
		em.close();
		emf.close();
	}
	
	public void update(TypeCuisineHome tch){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.merge(tch);
		transaction.commit();
		
		//Close entity manager
		em.close();
		emf.close();
	}
	
	public void delete(TypeCuisineHome tch){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
				
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
				
		em.remove(tch);
		transaction.commit();
				
		//Close entity manager
		em.close();
		emf.close();
	}

	public void persist(TypeCuisine transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(TypeCuisine persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public TypeCuisine merge(TypeCuisine detachedInstance) {
		try {
			TypeCuisine result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public TypeCuisine findById(Integer id) {
		try {
			TypeCuisine instance = entityManager.find(TypeCuisine.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
