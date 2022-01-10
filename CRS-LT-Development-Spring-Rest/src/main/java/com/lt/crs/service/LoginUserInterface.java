package com.lt.crs.service;
/**
 * @author Sai kumar,Dwikar,Rohan, Abudl,siva,nikil
 */

public interface LoginUserInterface {
	/**
	 * 
	 * @param userId is User Id
	 * @param userPassword
	 * user login method 
	 */
public int userLogin(String userId,String userPassword) ;

/**
 * 
 * @param userID
 * @param oldpw is Old password
 * @param newpw is new Password
 * this is used to update password
 */

public String updatePassword(String userID, String oldpw, String newpw);
}
