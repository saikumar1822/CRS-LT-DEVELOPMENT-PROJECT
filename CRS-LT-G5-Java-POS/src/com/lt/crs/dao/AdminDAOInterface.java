package com.lt.crs.dao;
/**
 * this is the Admin dao interface where Admin realted methods will be there
 * @author Abdul,Rohan,Diwakar,Siva,Nikhil
 *
 */

public interface AdminDAOInterface {
/**
 * 
 * @throws Exception
 */
	public void addProfessor() throws Exception;
	/**
	 * 
	 * @throws Exception
	 */

	public void removeProfessor() throws Exception;
	/**
	 * 
	 * @throws Exception
	 */

	public void addStudent() throws Exception;
	/**
	 * 
	 * @throws Exception
	 */

	public void removeStudent() throws Exception;
	/**
	 * 
	 * @throws Exception
	 */

	public void addCourse() throws Exception;
	/**
	 * 
	 * @throws Exception
	 */

	public void removeCourse() throws Exception;
	/**
	 * 
	 * @throws Exception
	 */

	public void reportCardGeneration() throws Exception;
	
	
}
