package epsi.dao;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import epsi.model.TypeContenant;

/**
 * Home object for domain model class TypeContenant.
 * @see epsi.model.TypeContenant
 * @author Hibernate Tools
 */
public class TypeContenantHome {


	@PersistenceContext
	private EntityManager entityManager;

	public void persist(TypeContenant transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(TypeContenant persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public TypeContenant merge(TypeContenant detachedInstance) {
		try {
			TypeContenant result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public TypeContenant findById(Integer id) {
		try {
			TypeContenant instance = entityManager
					.find(TypeContenant.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
