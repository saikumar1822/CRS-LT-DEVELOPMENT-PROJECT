package com.lt.crs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.dao.PaymentDAOInterface;
import com.lt.crs.model.Payment;



/**
 * 
 * @author Sai kumar,Diwakar,Rohan This Class handles all the Payment Operation
 * 
 */

@Service
public class PaymentService implements PaymentServiceInterface {

	@Autowired
	PaymentDAOInterface paymentDao;

	public String makePayment(List<Payment> list,int studentId) {
		return paymentDao.makePayment(list, studentId);
	}
	
}
