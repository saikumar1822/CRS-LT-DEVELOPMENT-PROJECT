package com.lt.crs.service;
import java.util.List;

/**
 * 
 * @author Sai kumar,Diwakar,Rohan,Abdul,Siva,nikil
 *
 */
import com.lt.crs.model.Payment;

/**
 * @author Abdul
 *
 */
public interface PaymentServiceInterface {
	/**
	 * 
	 * @param userId
	 * @return void
	 */
	public String makePayment(List<Payment> list,int studentId);

}
