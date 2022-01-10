package com.lt.crs.model;


/**
 * @author Abdul,Sai kumar,Rohan,Siva,Nikhil
 *
 */
public class Payment {
	private int paymentId;
	public int getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(int paymentId) {
		this.paymentId = paymentId;
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
	
	
	public double getCourseCost() {
		return courseCost;
	}
	public void setCourseCost(double courseCost) {
		this.courseCost = courseCost;
	}

	private int studentId;
	private int courseId;
	private String deleteRequest;
	public String getDeleteRequest() {
		return deleteRequest;
	}
	public void setDeleteRequest(String deleteRequest) {
		this.deleteRequest = deleteRequest;
	}

	private double courseCost;

}
