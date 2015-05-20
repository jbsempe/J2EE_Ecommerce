package epsi.dao;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import epsi.exception.UserNotFoundException;
import epsi.model.Panier;
import epsi.model.PanierContient;
import epsi.model.PanierContientId;
import epsi.model.Produit;
import epsi.model.User;

/**
 * Home object for domain model class PanierContient.
 * @see epsi.model.PanierContient
 * @author Hibernate Tools
 */
public class PanierContientHome {


	@PersistenceContext
	private EntityManager entityManager;

	public void persist(PanierContient transientInstance) {
		try {
			entityManager.persist(transientInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public void remove(PanierContient persistentInstance) {
		try {
			entityManager.remove(persistentInstance);
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public PanierContient merge(PanierContient detachedInstance) {
		try {
			PanierContient result = entityManager.merge(detachedInstance);
			return result;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	public PanierContient findById(PanierContientId id) {
		try {
			PanierContient instance = entityManager.find(PanierContient.class,
					id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}
	
	public List<PanierContient> findAllPanier(Panier panier) throws UserNotFoundException{
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		List<PanierContient> PC = em.createQuery("SELECT p FROM PanierContient p WHERE p.panier=:id").setParameter("id", panier).getResultList();
		//Close entity manager
		em.close();
		emf.close();
		return PC;
	}
	
	public PanierContient findByPanier(Panier panier) throws UserNotFoundException{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		try{
			PanierContient PC = (PanierContient) em.createQuery("Select p FROM PanierContient p WHERE p.panier=:id")
							.setParameter("id", panier).getSingleResult();
			return PC;
			
		}catch(NoResultException ex){
			throw new UserNotFoundException();
		}finally{
			em.close();
			emf.close();
		}		

	}
	public PanierContient findByPanierProduit(Panier panier, Produit produit) throws UserNotFoundException{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		try{
			PanierContient PC = (PanierContient) em.createQuery("Select p FROM PanierContient p WHERE p.panier=:id AND p.produit=:id_prod")
							.setParameter("id", panier)
							.setParameter("id_prod", produit).getSingleResult();
			return PC;
			
		}catch(NoResultException ex){
			throw new UserNotFoundException();
		}finally{
			em.close();
			emf.close();
		}		

	}
}
