package test;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import model.Student;
import session.JpaEntityManagerFactory;

public class StudentTest {
	
	public static void main(String[] args) {
		
		EntityManager entityManager = JpaEntityManagerFactory.buildEntityManager();
		
	
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Student.class).get();
		
		
		org.apache.lucene.search.Query query = qb.keyword()
										.wildcard()
										.onField("lastName")
										.matching("de*")
										.createQuery();
		
		javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Student.class);
		
		
		@SuppressWarnings("unchecked")
		List<Student> students = persistenceQuery.getResultList();
		
		System.out.println("-------------------------------------");
		
		students.forEach(System.out::println);
		
	}
	

}
