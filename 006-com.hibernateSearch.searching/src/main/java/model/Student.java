package model;

import java.util.Date;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.IndexedEmbedded;
import org.hibernate.search.annotations.Resolution;

@Entity
@Indexed
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ogrenciId;
	
	@Field
	private String firstName;
	
	@Field
	private String lastName;
	
	@DateBridge(resolution = Resolution.DAY)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	
	@IndexedEmbedded
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "students_lessons", joinColumns = @JoinColumn(name = "studentId"),
					inverseJoinColumns =  @JoinColumn(name = "lessonId"))
	private Set<Lesson> lessons;
	
	
	public Student() {

	}


	public Student(String firstName, String lastName, Date dateOfBirth, Set<Lesson> lessons) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.lessons = lessons;
	}
	
	/*
	 * 
	 * Note that index=Index.YES, analyze=Analyze.YES and store=Store.NO are the default values for these parameters and could be omitted.
	 */

	/*
	 * @DateBridge
	 * 
	 * 			This annotation is one of the built-in field bridges in Hibernate Search. The Lucene index is mostly string based, 
	 * 			with special support for encoding numbers. Hibernate Search must convert the data types of the indexed fields to their respective 
	 * 			Lucene encoding and vice versa. A range of predefined bridges is provided for this purpose, 
	 * 			including the DateBridge which will convert a java.util.Date into a numeric value (a long) with the specified resolution.
	 * 
	 * 
	 */
	
	/*
	 * @IndexedEmbeded
	 * 
	 * 			This annotation is used to index associated entities (@ManyToMany, @*ToOne, @Embedded and @ElementCollection) as part of the owning entity. 
	 * 			This is needed since a Lucene index document is a flat data structure which does not know anything about object relations. 
	 * 			To ensure that the author names will be searchable you have to make sure that the names are indexed as part of the book itself.
	 * 			On top of @IndexedEmbedded you will also have to mark the fields of the associated entity you want to have included in the index with @Field. 
	 * 			For more details see Embedded and associated objects.
	 */
	

	public Integer getOgrenciId() {
		return ogrenciId;
	}


	public void setOgrenciId(Integer ogrenciId) {
		this.ogrenciId = ogrenciId;
	}


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	

	public Date getDateOfBirth() {
		return dateOfBirth;
	}


	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	

	public Set<Lesson> getLessons() {
		return lessons;
	}


	public void setLessons(Set<Lesson> lessons) {
		this.lessons = lessons;
	}


	@Override
	public String toString() {
		return "Ogrenci [ogrenciId=" + ogrenciId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
