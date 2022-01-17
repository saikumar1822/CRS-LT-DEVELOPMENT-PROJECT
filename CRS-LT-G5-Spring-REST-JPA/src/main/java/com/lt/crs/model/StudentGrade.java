package com.lt.crs.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Abdul,Sai kumar,Rohan,Siva,Nikhil
 *
 */
@Entity
@Table
public class StudentGrade {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private int gradeId;
	private int courseId;
	private int professorId;
	private int studentId;
	private double mark;
	private String grade;
	@JsonIgnore(value = true)
	//@JsonProperty("AdminGenaratedReportcard")
	private boolean adminGenaratedReportcard;
	/**
	 * @return the gradeId
	 */
	public int getGradeId() {
		return gradeId;
	}
	/**
	 * @param gradeId the gradeId to set
	 */
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}
	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}
	/**
	 * @param courseId the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	/**
	 * @return the professorId
	 */
	public int getProfessorId() {
		return professorId;
	}
	/**
	 * @param professorId the professorId to set
	 */
	public void setProfessorId(int professorId) {
		this.professorId = professorId;
	}
	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}
	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	/**
	 * @return the mark
	 */
	public double getMark() {
		return mark;
	}
	/**
	 * @param mark the mark to set
	 */
	public void setMark(double mark) {
		this.mark = mark;
	}
	/**
	 * @return the grade
	 */
	public String getGrade() {
		return grade;
	}
	/**
	 * @param grade the grade to set
	 */
	public void setGrade(String grade) {
		this.grade = grade;
	}
	/**
	 * @return the adminGenaratedReportcard
	 */
	public boolean isAdminGenaratedReportcard() {
		return adminGenaratedReportcard;
	}
	/**
	 * @param adminGenaratedReportcard the adminGenaratedReportcard to set
	 */
	public void setAdminGenaratedReportcard(boolean adminGenaratedReportcard) {
		this.adminGenaratedReportcard = adminGenaratedReportcard;
	}
	

}
