package test;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import model.Lesson;
import model.Student;
import session.JpaEntityManagerFactory;

public class StudentTest {
	
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
		
		transaction.begin();
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Student.class).get();
		
		
		org.apache.lucene.search.Query query = qb.keyword()
															.onFields("firstName", "lastName", "lessons.lessonName", "lessons.teacherName")
															.matching("Ferhat")
															.createQuery();
												
		
		javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Student.class);
		
		
		@SuppressWarnings("unchecked")
		List<Student> students = persistenceQuery.getResultList();
		
		System.out.println("-------------------------------------");
		
		students.forEach(System.out::println);
		
	}
	
	
	
	

}
