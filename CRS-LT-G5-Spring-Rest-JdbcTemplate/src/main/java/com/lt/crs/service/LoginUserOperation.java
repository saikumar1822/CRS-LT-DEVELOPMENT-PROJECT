package com.lt.crs.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.dao.LoginUserDAOInterface;

/**
 * 
 * @author Diwakar ,Sai kumar,Rohan,Abdul,Siva,nikil
 *  This is related User Login
 *         Operations
 * 
 */
@Service
public class LoginUserOperation implements LoginUserInterface {
	@Autowired
	LoginUserDAOInterface loginDao;
	/**
	 * 
	 * 
	 * @param userId
	 *            is User Id
	 * @param userPassword
	 *            user login method
	 */

	public int userLogin(String userId, String userPassword) {
	
		return loginDao.userLogin(userId, userPassword);

	}
	/**
	 * 
	 * @param userID
	 * @param oldpw is Old password
	 * @param newpw is new Password
	 * this is used to update password
	 */
	@Override
	public String updatePassword(String userID, String oldpw, String newpw) {
		// TODO Auto-generated method stub
		return loginDao.updatePassword(userID, oldpw, newpw);
		
	}

}
