package epsi.dao;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import epsi.model.EtatCommande;
/**
 * Home object for domain model class EtatCommande.
 * @see epsi.model.EtatCommande
 * @author Hibernate Tools
 */
public class EtatCommandeHome {


	@PersistenceContext
	private EntityManager entityManager;

	public void persist(EtatCommande transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(EtatCommande persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public EtatCommande merge(EtatCommande detachedInstance) {
		try {
			EtatCommande result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public EtatCommande findById(Integer id) {
		try {
			EtatCommande instance = entityManager.find(EtatCommande.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
