package com.lt.crs.model;

/**
 * @author Abdul,Sai kumar,Rohan,Siva,Nikhil
 *
 */

public class CourseProfessor {
	private int id;
	private int courseId;
	private int professorId;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public int getProfessorId() {
		return professorId;
	}
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	
}
