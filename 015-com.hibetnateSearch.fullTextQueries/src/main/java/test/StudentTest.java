package test;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;


import model.Address;
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
		
		Set<String> phoneNumbers1 = new HashSet<>();
		phoneNumbers1.add("05555555555");
		phoneNumbers1.add("04444444444");
		phoneNumbers1.add("03333333333");
		
		Set<String> phoneNumbers2 = new HashSet<>();
		phoneNumbers2.add("02222222222");
		phoneNumbers2.add("01111111111");
		
		Set<String> phoneNumbers3 = new HashSet<>();
		phoneNumbers3.add("06666666666");
		
		Address address1 = new Address("Meydan Mahallesi", "Kütahya");
		Address address2 = new Address("Çanakkale Mahallesi", "Adana");
		Address address3 = new Address("Vakýf sokak Deneme Mahallesi", "Ýstanbul");
		
		String criteria1 = "Java bilen, iyi derecede sql yazabilen, ingilizcesi iyi olan, 5 yýllýk deneyim sahibi olan, muhendislik fakültesi mezunu olan.";
		String criteria2 = "C# bilen, iyi derecede mssql yazabilen, ingilizcesi orta olan, 5 yýllýk deneyim sahibi olan, bilgisayar fakültesi mezunu olan.";
		String criteria3 = "Calýþmayý seven takým arkadaþý arýyor.";
		
		Student ogrenci = new Student("Ferhat", "Aykan", createDate(29, 6, 1994), lessons, phoneNumbers1, "aykanferhat@gmail.com", address1, criteria1);
		Student ogrenci1 = new Student("Ahmet", "Deniz", createDate(10, 3, 1998), lessons, phoneNumbers2, "kitaplar", address2, criteria2);
		Student ogrenci2 = new Student("Baki", "Aykan", createDate(22, 1, 2001), lessons, phoneNumbers3, "crazy_betul485@hotmail.com", address3, criteria3);
		
		transaction.begin();
		entityManager.persist(address2);
		entityManager.persist(address1);
		entityManager.persist(address3);
		entityManager.persist(lesson1);
		entityManager.persist(lesson2);
		entityManager.persist(lesson3);
		entityManager.persist(ogrenci);
		entityManager.persist(ogrenci1);
		entityManager.persist(ogrenci2);;
		transaction.commit();
	
	}
	
	
	
	
	public static Date createDate(int day, int month, int year){
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH, day);
		calendar.set(Calendar.MONTH, month);
		calendar.set(Calendar.YEAR, year);
		
		return calendar.getTime();
	}
	
	

}
