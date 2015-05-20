package epsi.dao;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import epsi.exception.UserNotFoundException;
import epsi.model.Commande;
import epsi.model.Panier;
import epsi.model.PanierContient;
import epsi.model.Plat;

/**
 * Home object for domain model class Commande.
 * @see epsi.model.Commande
 * @author Hibernate Tools
 */
public class CommandeHome {


	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Commande transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(Commande persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Commande merge(Commande detachedInstance) {
		try {
			Commande result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Commande findById(Integer id) {
		try {
			Commande instance = entityManager.find(Commande.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Commande> find(){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		List<Commande> commandes = em.createQuery("SELECT c FROM Commande c").getResultList();
			
		//Close entity manager
		em.close();
		emf.close();
		return commandes;
	}
}
