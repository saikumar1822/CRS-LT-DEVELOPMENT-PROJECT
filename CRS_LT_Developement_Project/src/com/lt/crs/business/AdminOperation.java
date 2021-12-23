package com.lt.crs.business;

import com.lt.crs.dao.AdminDAOInterface;

import org.apache.log4j.Logger;

import com.lt.crs.dao.AdminDAOImpl;


/**
 * 
 * @author Diwakar,Sai kumar,Rohan This Class is Admin Opertaion
 * 
 */
public class AdminOperation implements AdminInterface {
	AdminDAOInterface admin = null;
	private static Logger logger=Logger.getLogger(AdminOperation.class);

	/**
	 * this is used to add professor
	 * 
	 * @param
	 */
	@Override
	public void addProfessor() {
		// TODO Auto-generated method stub
		admin = new AdminDAOImpl();
		try {
			admin.addProfessor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}
	
	/**
	 * Remove professor method
	 */
	@Override
	public void removeProfessor() {
		// TODO Auto-generated method stub
		admin = new AdminDAOImpl();
		try {
			admin.removeProfessor();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

	/**
	 * this is used to add student method
	 */
	@Override
	public void addStudent() {
		// TODO Auto-generated method stub
		admin = new AdminDAOImpl();
		try {
			admin.addStudent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

	/**
	 * this method is used to remove student
	 */
	@Override
	public void removeStudent() {
		// TODO Auto-generated method stub
		admin = new AdminDAOImpl();
		try {
			admin.removeStudent();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

	/**
	 * This add course method connects to dao class and adds the new course into
	 * the course table
	 */
	@Override
	public void addCourse() {
		// TODO Auto-generated method stub
		admin = new AdminDAOImpl();
		try {
			admin.addCourse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}
	
	/**
	 * Remove course method connects with the admin Dao Impl class and Removes
	 * the Course from the course table by the admin
	 */
	@Override
	public void removeCourse() {
		// TODO Auto-generated method stub
		admin = new AdminDAOImpl();
		try {
			admin.removeCourse();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}
	/**
	 * This methods is responsible for creating report card for students
	 */
	@Override
	public void reportCardGeneration() {
		// TODO Auto-generated method stub
		admin = new AdminDAOImpl();
		try {
			admin.reportCardGeneration();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

}
