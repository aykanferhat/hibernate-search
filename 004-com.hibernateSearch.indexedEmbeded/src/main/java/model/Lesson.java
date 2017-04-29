package model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Lesson {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer lessonId;
	
	private String lessonName;
	
	private String teacherName;
	
	public Lesson() {

	}

	public Lesson(String lessonName, String teacherName) {
		this.lessonName = lessonName;
		this.teacherName = teacherName;
	}

	public Integer getLessonId() {
		return lessonId;
	}

	public void setLessonId(Integer lessonId) {
		this.lessonId = lessonId;
	}

	public String getLessonName() {
		return lessonName;
	}

	public void setLessonName(String lessonName) {
		this.lessonName = lessonName;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	@Override
	public String toString() {
		return "Lesson [lessonId=" + lessonId + ", lessonName=" + lessonName + ", teacherName=" + teacherName + "]";
	}
	
}
