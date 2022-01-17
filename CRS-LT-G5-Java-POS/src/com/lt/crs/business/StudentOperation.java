package com.lt.crs.business;

import org.apache.log4j.Logger;

import com.lt.crs.dao.StudentDAOImpl;
import com.lt.crs.dao.StudentDAOInterface;


/**
 * 
 * @author Sai kumar,Rohan,Diwikar,Abdul
 * This Class is related all the Student Operation
 *
 */
public class StudentOperation implements StudentInterface {

	StudentDAOInterface stud = null;
	private static Logger logger=Logger.getLogger(StudentOperation.class);
	
	/**
	 * is used to add course for the student
	 * @param studentId
	 */
	@Override
	public void addCourse(int studentId) {
		// TODO Auto-generated method stub
		stud = new StudentDAOImpl();
		try {
			stud.addCourse(studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

	/**
	 * drop course method 
	 * @param student id
	 */
	@Override
	public void dropCourse(int studentId) {
		// TODO Auto-generated method stub
		stud = new StudentDAOImpl();
		try {
			stud.dropCourse(studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}
	}
	
	
	/**
	 * this method displays the students who are all enrolled for the course
	 * @param studentId
	 */
	@Override
	public void viewEnrolledCourse(int studentId) {
		// TODO Auto-generated method stub
		stud = new StudentDAOImpl();
		try {
			stud.viewEnrolledCourses(studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

	/**
	 * using this method student can view the report card
	 * @param studentId
	 */
	@Override
	public void viewReportCard(int studentId) {
		// TODO Auto-generated method stub
		stud = new StudentDAOImpl();
		try {
			stud.viewReportCard(studentId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

}
