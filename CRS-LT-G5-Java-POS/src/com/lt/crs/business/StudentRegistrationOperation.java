package com.lt.crs.business;

import org.apache.log4j.Logger;

import com.lt.crs.bean.Student;
import com.lt.crs.dao.StudentRegisterationDAOInterface;
import com.lt.crs.dao.StudentRegisterationDAOImpl;

/**
 * 
 * @author Rohan
 *
 */
public class StudentRegistrationOperation implements StudentRegistrationInterface {
	private static Logger logger=Logger.getLogger(StudentRegistrationOperation.class);
	StudentRegisterationDAOImpl str = null;

	/**
	 * @param student
	 * used to register the student 
	 */
	public void register(Student student) {
		logger.info("Student Registration");
		str = new StudentRegisterationDAOImpl();
		str.callLambadaStudentRegister(student);

	}

}
