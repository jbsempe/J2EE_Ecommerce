package epsi.dao;

// Generated 6 mai 2015 11:18:39 by Hibernate Tools 3.4.0.CR1

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import epsi.exception.MenuNotFoundException;
import epsi.model.Menu;

/**
 * Home object for domain model class Menu.
 * @see epsi.model.Menu
 * @author Hibernate Tools
 */
public class MenuHome {


	public MenuHome(){
	}

	@PersistenceContext
	private EntityManager entityManager;

	public void create(Menu menu){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.persist(menu);
		transaction.commit();
		
		//Close entity manager
		em.close();
		emf.close();
	}
	
	public void update(Menu menu){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
		
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		em.merge(menu);
		transaction.commit();
		
		//Close entity manager
		em.close();
		emf.close();
	}
	
	public void delete(Menu menu){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();
				
		//Get transaction
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
				
		em.remove(menu);
		transaction.commit();
				
		//Close entity manager
		em.close();
		emf.close();
	}

	public Menu findById(Long id_Produit) throws MenuNotFoundException{
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		try{
			Menu menu = (Menu) em.createQuery("Select m FROM Menu m WHERE m.idProduit=:id")
							.setParameter("id", id_Produit).getSingleResult();
			return menu;
			
		}catch(NoResultException ex){
			throw new MenuNotFoundException();
		}finally{
			em.close();
			emf.close();
		}		
	}
	@SuppressWarnings("unchecked")
	public List<Menu> find(){
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		List<Menu> menus = em.createQuery("SELECT m FROM Menu m").getResultList();
			
		//Close entity manager
		em.close();
		emf.close();
		return menus;
	}
	public Menu findByDesignation(String designation) throws MenuNotFoundException{
		// Get entity manager
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("musciPU");
		EntityManager em = emf.createEntityManager();

		try{
		Menu menu = (Menu) em.createQuery("Select m FROM Menu m WHERE m.designation=:designation")
							.setParameter("designation", designation).getSingleResult();
		return menu;	

		}catch(NoResultException e){
			throw new MenuNotFoundException();
		}finally{
			em.close();
			emf.close();
		}
	}

}
