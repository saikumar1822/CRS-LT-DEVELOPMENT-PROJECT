package com.lt.crs.bean;



/**
 * @author Deepika, Bheem,Shiva, Shiva Amancha, Dinesh, Rajnish
 *
 */

public class Student_Course {


	private int studentId;
	private int courseId;
	private int professorId;

	

	@Override
	public String toString() {
		return "Student_Course [studentId=" + studentId + ", courseId=" + courseId + ", professorId=" + professorId
				+ "]";
	}

	public int getStudentId() {
		return studentId;
	}
	public void setStudentId(int studentId) {
		this.studentId = studentId;
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
