package epsi.dao;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import epsi.exception.PlatNotFoundException;
import epsi.model.Plat;
import epsi.model.Traiteur;

/**
 * Home object for domain model class Traiteur.
 * @see epsi.model.Traiteur
 * @author Hibernate Tools
 */
public class TraiteurHome {


	@PersistenceContext
	private EntityManager entityManager;

	public void persist(Traiteur transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(Traiteur persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Traiteur merge(Traiteur detachedInstance) {
		try {
			Traiteur result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public Traiteur findById(Integer id) {
		try {
			Traiteur instance = entityManager.find(Traiteur.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public Traiteur findByName(String name) {
		try {
			Traiteur instance = entityManager.find(Traiteur.class, name);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	/*public Traiteur findById(Integer id_Produit){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		
			Traiteur traiteur = (Traiteur) em.createQuery("Select t FROM Traiteur t WHERE t.idTraiteur=:id")
							.setParameter("id", id_Produit).getSingleResult();
			return traiteur;
		}	*/	
	
	
	@SuppressWarnings("unchecked")
	public List find(){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		List traiteurs = em.createQuery("SELECT t FROM Traiteur t").getResultList();
			
		//Close entity manager
		em.close();
		emf.close();
		return traiteurs;
	}
}