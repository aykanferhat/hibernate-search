package test;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;

import model.Student;
import session.JpaEntityManagerFactory;

public class StudentTest2 {
	
	public static void main(String[] args) {
		
		EntityManager entityManager = JpaEntityManagerFactory.buildEntityManager();
		
	
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Student.class).get();
		
		/*
		org.apache.lucene.search.Query query = qb
												.bool()
												.must( QueryA )
												.must( QueryB )
												.createQuery();
		*/	
		/*
		org.apache.lucene.search.Query query = qb
												.bool()
												.must( qb.keyword().onField("criteria").matching("bilen").createQuery())
												.must( qb.range().onField("dateOfBirth").above(createDate(22, 1, 1999)).excludeLimit().createQuery())
												.createQuery();
		*/
		/*
		org.apache.lucene.search.Query query = qb
												.bool()
												.should( qb.keyword().onField("criteria").matching("bilen").createQuery())
												.should( qb.range().onField("dateOfBirth").above(createDate(22, 1, 1999)).excludeLimit().createQuery())
												.createQuery();
		*/
		org.apache.lucene.search.Query query = qb
												.all()
												.except(qb.keyword().onField("criteria").matching("bilen").createQuery())
												.createQuery();
		
		javax.persistence.Query persistenceQuery = fullTextEntityManager.createFullTextQuery(query, Student.class);
		
		
		@SuppressWarnings("unchecked")
		List<Student> students = persistenceQuery.getResultList();
		
		System.out.println("-------------------------------------");
		
		students.forEach(System.out::println);
		
	}
	
	
	public static Date createDate(int day, int month, int year){
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		return calendar.getTime();
	}
	

}
