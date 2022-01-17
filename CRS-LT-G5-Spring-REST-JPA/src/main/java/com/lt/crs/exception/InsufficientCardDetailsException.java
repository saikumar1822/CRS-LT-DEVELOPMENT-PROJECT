package com.lt.crs.exception;

/**
 * @author Abdul,Sai kumar,Rohan,Siva,Nikhil
 *
 */
public class InsufficientCardDetailsException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InsufficientCardDetailsException(String msg){
		super(msg);
	}
}
