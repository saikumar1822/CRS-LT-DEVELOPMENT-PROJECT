package com.lt.crs.dto;

import java.util.List;

import com.lt.crs.model.Course;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;


/**
 * 
 * @author Sai,Rohan
 *
 */
public class ViewStudentsDto {
	private Student student;
	private List<Course> course;
	private List<Professor> professor;
	public Student getStudent() {
		return student;
	}
	public List<Course> getCourse() {
		return course;
	}
	public void setCourse(List<Course> course) {
		this.course = course;
	}
	public List<Professor> getProfessor() {
		return professor;
	}
	public void setProfessor(List<Professor> professor) {
		this.professor = professor;
	}
	public void setStudent(Student student) {
		this.student = student;
	}
		
}
