package com.lt.crs.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author Abdul,Sai kumar,Rohan,Siva,Nikhil
 *
 */

public class Course {
	
	@JsonIgnore
	private int courseId;
	private String courseName;
	private double courseCost;
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
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	/**
	 * @return the courseCost
	 */
	public double getCourseCost() {
		return courseCost;
	}
	/**
	 * @param courseCost the courseCost to set
	 */
	public void setCourseCost(double courseCost) {
		this.courseCost = courseCost;
	}

	
}
