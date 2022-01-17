package com.lt.crs.dao;

/**
 * 
 * @author Siva
 *
 */

public interface LoginUserDAOInterface {
	
public int userLogin(String userId,String userPassword);
public String updatePassword(String userID, String oldpw, String newpw);

}