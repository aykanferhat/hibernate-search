package test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;

import model.Lesson;
import model.Student;
import session.JpaEntityManagerFactory;

public class StudentTestTest {
	
	public static void main(String[] args) {
		
		EntityManager entityManager = JpaEntityManagerFactory.buildEntityManager();
		EntityTransaction transaction = JpaEntityManagerFactory.transaction();

		Lesson lesson1 = new Lesson("Matematik", "Ahmet");
		Lesson lesson2 = new Lesson("Fizik", "Burak");
		Lesson lesson3 = new Lesson("Kimya", "Hamza");
		
		Set<Lesson> lessons = new HashSet<>();
		lessons.add(lesson3);
		lessons.add(lesson2);
		lessons.add(lesson1);
		
		Student ogrenci = new Student("Ferhat", "Aykan", new Date(), lessons);
		Student ogrenci1 = new Student("Ahmet", "Deniz", new Date(), lessons);
		
		transaction.begin();
		entityManager.persist(lesson1);
		entityManager.persist(lesson2);
		entityManager.persist(lesson3);
		entityManager.persist(ogrenci);
		entityManager.persist(ogrenci1);
		transaction.commit();
		
		
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		
		try {
			fullTextEntityManager.createIndexer().startAndWait();
			
		} catch (InterruptedException e) {

			System.err.println("hATa : " + e);
		}
		
		
		
	}
	
	
	
	

}
