package com.lt.crs.dao;

import java.util.List;

import com.lt.crs.model.Payment;



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
	public String makePayment(List<Payment> list,int studentId) ;

}
