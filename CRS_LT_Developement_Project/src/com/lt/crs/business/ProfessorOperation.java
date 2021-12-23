package com.lt.crs.business;

import org.apache.log4j.Logger;

import com.lt.crs.dao.ProfessorDAOImpl;
import com.lt.crs.dao.ProfessorDAOInterface;

/**
 * 
 * @author Rohan,Sai kumar,Diwikar This Class is realted Professor Operation
 * 
 */
public class ProfessorOperation implements ProfessorInterface {

	ProfessorDAOInterface prd = null;
	private static Logger logger=Logger.getLogger(ProfessorOperation.class);
	
	/**
	 * is used to view the student by entering the student id
	 */
	@Override
	public void viewStudent(int profId) {
		// TODO Auto-generated method stub
		prd = new ProfessorDAOImpl();
		try {
			prd.viewStudent(profId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

	
	/**
	 * this method is used to add grade for the student by the professor
	 * 
	 * @param profId
	 */
	@Override
	public void addGrade(int profId) {
		// TODO Auto-generated method stub
		prd = new ProfessorDAOImpl();
		try {
			prd.addGrade(profId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

}
