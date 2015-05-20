package epsi.dao;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import epsi.model.CommandeContient;
import epsi.model.CommandeContientId;

/**
 * Home object for domain model class CommandeContient.
 * @see epsi.model.CommandeContient
 * @author Hibernate Tools
 */
public class CommandeContientHome {


	@PersistenceContext
	private EntityManager entityManager;

	public void persist(CommandeContient transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(CommandeContient persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public CommandeContient merge(CommandeContient detachedInstance) {
		try {
			CommandeContient result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public CommandeContient findById(CommandeContientId id) {
		try {
			CommandeContient instance = entityManager.find(
					CommandeContient.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
