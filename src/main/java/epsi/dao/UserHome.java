package epsi.dao;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import epsi.model.User;
/**
 * Home object for domain model class User.
 * @see epsi.model.User
 * @author Hibernate Tools
 */
public class UserHome {


	@PersistenceContext
	private EntityManager entityManager;

	public void persist(User transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(User persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		try {
			User result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public User findById(Integer id) {
		try {
			User instance = entityManager.find(User.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
}
