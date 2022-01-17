package com.lt.crs.business;

import org.apache.log4j.Logger;

import com.lt.crs.dao.LoginUserDAOImpl;
import com.lt.crs.exceptions.UserNotFoundException;

/**
 * 
 * @author Diwakar ,Sai kumar,Rohan,Abdul,Siva,nikil This is related User Login
 *         Operations
 * 
 */
public class LoginUserOperation implements LoginUserInterface {
	com.lt.crs.dao.LoginUserDAOInterface user = null;
	private static Logger logger=Logger.getLogger(LoginUserOperation.class);
	
	/**
	 * 
	 * 
	 * @param userId
	 *            is User Id
	 * @param userPassword
	 *            user login method
	 */

	public void userLogin(String userId, String userPassword) {
		user = new LoginUserDAOImpl();
		try {
			user.userLogin(userId, userPassword);
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e);
		} catch (Exception e) {
			logger.error(e);
		}

	}
	/**
	 * 
	 * @param userID
	 * @param oldpw is Old password
	 * @param newpw is new Password
	 * this is used to update password
	 */
	@Override
	public void updatePassword(String userID, String oldpw, String newpw) {
		// TODO Auto-generated method stub
		user = new LoginUserDAOImpl();
		try {
			user.updatePassword(userID, oldpw, newpw);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			logger.error(e);
		}

	}

}
