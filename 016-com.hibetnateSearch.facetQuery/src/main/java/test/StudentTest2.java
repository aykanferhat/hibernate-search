package test;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.FullTextQuery;
import org.hibernate.search.jpa.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.engine.spi.FacetManager;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetSortOrder;
import org.hibernate.search.query.facet.FacetingRequest;

import model.Student;
import session.JpaEntityManagerFactory;

public class StudentTest2 {
	
	public static void main(String[] args) {
		
		EntityManager entityManager = JpaEntityManagerFactory.buildEntityManager();
		
	
		FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager(entityManager);
		
		QueryBuilder qb = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Student.class).get();
		
		
	
		org.apache.lucene.search.Query query = qb
												.all()
												.createQuery();
												
		FacetingRequest facetingRequest = qb
											.facet()
											.name("emailCategory")
											.onField("email")
											.discrete()
											.orderedBy(FacetSortOrder.COUNT_DESC)
											.includeZeroCounts(false)
											.createFacetingRequest();
		
		FullTextQuery fullTextQuery = fullTextEntityManager.createFullTextQuery(query, Student.class);
		
		
		FacetManager facetManager = fullTextQuery.getFacetManager();
		facetManager.enableFaceting(facetingRequest);
		
		
		List<Facet> facets = facetManager.getFacets("emailCategory");
		
		for(Facet f : facets){
			
			System.out.println(f.getValue() +  " (" + f.getCount() + ")");
			
			@SuppressWarnings("unchecked")
			List<Student> students = fullTextEntityManager.createFullTextQuery(f.getFacetQuery(), Student.class).getResultList();
			
			for(Student s : students){
				
				System.out.println("\t" + s.getFirstName() + " " + s.getEmail());
			}
			
		}
		
		
	}

}
