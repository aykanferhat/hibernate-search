package test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import model.Student;
import session.JpaEntityManagerFactory;

public class StudentTestTest {
	
	public static void main(String[] args) {
		
		EntityManager entityManager = JpaEntityManagerFactory.buildEntityManager();
		
		EntityTransaction transaction = JpaEntityManagerFactory.transaction();
		
		Student ogrenci = new Student("Ferhat", "Aykan", new Date());
		
		Student ogrenci1 = new Student("Ahmet", "Deniz", new Date());
		
		transaction.begin();
		
		entityManager.persist(ogrenci);
		entityManager.persist(ogrenci1);
		transaction.commit();
		
		
		
	}
	

}
