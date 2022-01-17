/**
 * 
 */
package com.lt.crs.service;


import com.lt.crs.dto.LoginDto;
import com.lt.crs.dto.PasswordDto;
import com.lt.crs.dto.StudentDto;
import com.lt.crs.model.Student;
import com.lt.crs.model.User;

/**
 * @author saikumar
 *
 */

public interface UserService {

	/**
	 * @param student
	 * @return
	 */
	Student saveStudent(StudentDto student);

	/**
	 * @param loginDto
	 * @return
	 */
	User userLogin(LoginDto loginDto);

	/**
	 * @param passwordDto
	 * @return
	 */
	User chnagePassword(PasswordDto passwordDto);
	

}
