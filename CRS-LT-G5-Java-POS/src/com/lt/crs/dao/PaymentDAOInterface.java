package com.lt.crs.dao;

import java.util.Map;

import com.lt.crs.exceptions.CourseNotRegisteredException;

/**
 * @author sai kumar,Rohan,Diwakar,Abdul,Siva,Nikil
 */
public interface PaymentDAOInterface {
	
	/**
	 * 
	 * @param userId
	 * @param courseId
	 * @throws CourseNotRegisteredException
	 */
	void makePayment(int userId,int courseId) throws CourseNotRegisteredException ;

}
