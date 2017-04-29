package model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.search.annotations.DateBridge;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Resolution;

@Entity
@Indexed
public class Student {
	
	/*
	 * 
	 * Note that index=Index.YES, analyze=Analyze.YES and store=Store.NO are the default values for these parameters and could be omitted.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ogrenciId;
	
	@Field
	private String firstName;
	
	@Field
	private String lastName;
	
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
	
	
	@DateBridge(resolution = Resolution.DAY)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	

	public Student() {

	}


	public Student(String firstName, String lastName, Date dateOfBirth) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
	}


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


	@Override
	public String toString() {
		return "Ogrenci [ogrenciId=" + ogrenciId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
