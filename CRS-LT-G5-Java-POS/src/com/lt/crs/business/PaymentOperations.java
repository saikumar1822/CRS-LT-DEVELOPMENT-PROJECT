package com.lt.crs.business;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.dao.PaymentDAOImpl;
import com.lt.crs.dao.PaymentDAOInterface;
import com.lt.crs.exceptions.CourseNotRegisteredException;
import com.lt.crs.exceptions.InsufficientCardDetailsException;

/**
 * 
 * @author Sai kumar,Diwakar,Rohan This Class handles all the Payment Operation
 * 
 */
public class PaymentOperations implements PaymentInterface {
	Map<String, String> map = new HashMap<String, String>();
	private static Logger logger=Logger.getLogger(PaymentOperations.class);
	PaymentDAOInterface paymentDao;
	Scanner sc = new Scanner(System.in);
	int courseId = 0;

	/**
	 * This method gets the course id from the user
	 * 
	 * @return course id value
	 */
	public int getCourseId() {
		System.out.println("Enter course Id: ");
		courseId = sc.nextInt();
		return courseId;
	}

	/**
	 * to get card details and validates the cards details
	 * 
	 * @return
	 * @throws InsufficientCardDetailsException
	 */
	public Map getCardDetails() throws InsufficientCardDetailsException {
		System.out.println("Enter Credit Card or Debit Card Number :");
		String cardNo = sc.next();
		if (cardNo.length() >= 13 && cardNo.length() <= 16) {
			map.put("cardNo", cardNo);

			System.out.println("Enter the CVV :");
			String cvv = sc.next();
			if (cvv.length() == 3) {
				map.put("cvv", cvv);

				System.out.println("Enter the Expiry Date(MM/yy ):");
				String date = sc.next();

				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
						"MM/yy");
				simpleDateFormat.setLenient(false);
				Date expiry = null;
				try {
					expiry = simpleDateFormat.parse(date);
				} catch (ParseException e) {
					e.getMessage();
				}
				boolean expired = expiry.before(new Date());
				if (expired == true) {
					throw new InsufficientCardDetailsException(
							"Card Already has been Expired");
				} else {
					map.put("expiry", date);
				}

			} else {
				throw new InsufficientCardDetailsException(
						"Enter the valid CVV number");
			}

		} else {
			throw new InsufficientCardDetailsException(
					"Enter the valid card number");
		}

		return map;

	}

	/**
	 * pay method
	 * 
	 * @param userId
	 * @throws InsufficientCardDetailsException
	 */
	public void makePayment(int userId) {
		courseId = getCourseId();
		paymentDao = new PaymentDAOImpl();
		try {
			map = getCardDetails();
		} catch (InsufficientCardDetailsException e1) {
			e1.getMessage();
		}

		if (map.get("expiry") != null && map.get("cvv") != null
				&& map.get("cardNo") != null) {
			try {
				paymentDao.makePayment(userId, courseId);
			} catch (CourseNotRegisteredException e) {
				logger.error(e);
			}

		} else {
			try {
				throw new InsufficientCardDetailsException(
						"Enter the valid card number");
			} catch (InsufficientCardDetailsException e) {
				logger.error(e);
			}
		}

	}
}
