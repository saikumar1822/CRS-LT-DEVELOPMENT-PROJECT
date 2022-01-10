package com.lt.crs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.dao.StudentRegisterationDAOInterface;
import com.lt.crs.model.Student;

/**
 * 
 * @author Rohan
 * call redirected to Student Registration Dao interface
 */
@Service
public class StudentRegistrationOperation implements StudentRegistrationInterface {

@Autowired
StudentRegisterationDAOInterface studRegDAO;

	public String studentRegistration(Student student) {
		return studRegDAO.registerStudent(student);
	}
}
