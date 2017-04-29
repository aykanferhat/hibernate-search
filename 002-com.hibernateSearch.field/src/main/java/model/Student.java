package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

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
	

	public Student() {

	}


	public Student(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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


	@Override
	public String toString() {
		return "Ogrenci [ogrenciId=" + ogrenciId + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
}
