package test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Student;
import session.JpaEntityManagerFactory;

public class StudentTestTest {
	
	public static void main(String[] args) {
		
		EntityManager entityManager = JpaEntityManagerFactory.buildEntityManager();
		
		EntityTransaction transaction = JpaEntityManagerFactory.transaction();
		
		Student ogrenci = new Student("Ferhat", "Aykan");
		
		
		transaction.begin();
		
		entityManager.persist(ogrenci);
		
		transaction.commit();
		
		
		
	}
	

}
