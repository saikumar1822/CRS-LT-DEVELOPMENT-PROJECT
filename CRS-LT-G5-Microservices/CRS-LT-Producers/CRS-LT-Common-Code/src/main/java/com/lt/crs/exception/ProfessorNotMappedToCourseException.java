/**
 * 
 */
package com.lt.crs.exception;

/**
 * @author saikumar
 *
 */
public class ProfessorNotMappedToCourseException extends RuntimeException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2686420116419464356L;

	public ProfessorNotMappedToCourseException(String msg){
		super(msg);
		
	}

}
