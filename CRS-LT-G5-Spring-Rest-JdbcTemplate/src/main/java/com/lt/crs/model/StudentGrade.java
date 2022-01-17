package com.lt.crs.model;


/**
 * @author Abdul,Sai kumar,Rohan,Siva,Nikhil
 *
 */

public class StudentGrade {
	private int id;
	private int courseId;
	private int professorId;
	private int studentId;
	private double mark;
	private String grade;

	public int getCourseId() {
		return courseId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public int getStudentId() {
		return studentId;
	}

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public double getMark() {
		return mark;
	}

	public void setMark(double mark) {
		this.mark = mark;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

}
