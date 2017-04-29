package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer ogrenciId;
	
	private String firstName;
	
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
