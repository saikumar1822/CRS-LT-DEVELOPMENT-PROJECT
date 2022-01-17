package com.lt.crs.model;

/**
 * @author Abdul,Sai kumar,Rohan,Siva,Nikhil
 *
 */

public class User {
	private int userID;
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	private String userPassword;
}
