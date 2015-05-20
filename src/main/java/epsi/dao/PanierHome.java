package epsi.dao;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import epsi.exception.UserNotFoundException;
import epsi.model.Panier;
import epsi.model.User;

/**
 * Home object for domain model class Panier.
 * @see epsi.model.Panier
 * @author Hibernate Tools
 */
public class PanierHome {


	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Panier transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(Panier persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Panier merge(Panier detachedInstance) {
		try {
			Panier result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Panier findById(Integer id) {
		try {
			Panier instance = entityManager.find(Panier.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	
	public Panier findByUser(User user) throws UserNotFoundException{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		try{
			Panier panier = (Panier) em.createQuery("Select p FROM Panier p WHERE p.user=:id")
							.setParameter("id", user).getSingleResult();
			return panier;
			
		}catch(NoResultException ex){
			throw new UserNotFoundException();
		}finally{
			em.close();
			emf.close();
		}		

	}
}
