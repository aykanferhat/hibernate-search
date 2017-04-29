package session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaEntityManagerFactory{
	
	public static EntityManagerFactory factory;
	public static EntityManager entityManager;
	

	public static EntityManager buildEntityManager(){
		
			factory = Persistence.createEntityManagerFactory("PersistenceUnit");
			entityManager = factory.createEntityManager();

			return entityManager;
	}

	public static EntityTransaction transaction(){
		return entityManager.getTransaction();
	}
	
}
