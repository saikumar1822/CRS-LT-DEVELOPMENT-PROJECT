package com.lt.crs.dao;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.exception.UserNotFoundException;
/**
 * 
 * @author Siva
 * All Login related methods are present in this class 
 * input ID,Password 
 *
 */

@Repository 	
public class LoginUserDAOImpl implements LoginUserDAOInterface{
	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * This method is used for login ,based on the role he/she will be
	 * redirected corresponding menu
	 * 
	 * @param userId
	 * @param userPassword
	 * @throws UserNotFoundException
	 * @throws Exception
	 */
	@Transactional
public int userLogin(String userId,String userPassword)  {
	
	try {
	String[] a=userId.split("-");
	if(a[0].trim().toLowerCase().equals("student".trim())) {
		int studID=Integer.parseInt(a[1]);
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.USER_ROLE_STUDENT,new Object[] {studID},Integer.class);
		if(count1==0) {
			throw  new UserNotFoundException("User ID  "+userId+"  is not yet approved by admin");
		}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_LOGIN_QUERY,new Object[] {studID,userPassword},Integer.class);
			
			if(count2==0) {
				throw  new UserNotFoundException("Please provide valid credentials");
			}
			
			return 3;
		}
		
	}
	else if(a[0].toLowerCase().equals("professor".trim())) {
		int profID=Integer.parseInt(a[1]);
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.PROFESSOR_ROLE_STUDENT,new Object[] {profID},Integer.class);
		if(count1==0) {
			throw  new UserNotFoundException("User ID  "+userId+"  is not yet approved by admin");
		}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.PROFESSOR_LOGIN_QUERY,new Object[] {profID,userPassword},Integer.class);
			
			if(count2==0) {
				throw  new UserNotFoundException("Please provide valid credentials");
			}
			
			return 2;
		}

	}
	else if(a[0].toLowerCase().equals("admin".trim())) {
		int adminID=Integer.parseInt(a[1]);
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.USER_ROLE_ADMIN,new Object[] {adminID},Integer.class);
		if(count1==0) {
			throw  new UserNotFoundException("User ID  "+userId+"  is not having admin role");
		}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.ADMIN_LOGIN_QUERY,new Object[] {adminID,userPassword},Integer.class);
			
			if(count2==0) {
				throw  new UserNotFoundException("Please provide valid credentials");
			}
			
			return 1;
		}
		
		
		
	}
	
	
	throw  new UserNotFoundException("User with this credential is not found		"+userId);
	}
	catch(Exception e) {
		throw  new UserNotFoundException("User with this credential is not found		"+userId);
	}
		
	}

/**
 * this method used to update the password
 * 
 * @param userID
 * @param oldpw
 * @param newpw
 * @throws Exception
 */
	@Transactional
@Override
public String updatePassword(String userId, String userPassword, String newpw)  {
	// TODO Auto-generated method stub
	try {
	String[] a=userId.split("-");
	if(a[0].trim().toLowerCase().equals("student".trim())) {
		int studID=Integer.parseInt(a[1]);
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.USER_ROLE_STUDENT,new Object[] {studID},Integer.class);
		if(count1==0) {
			throw  new UserNotFoundException("User ID  "+userId+"  is not yet approved by admin");
		}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_LOGIN_QUERY,new Object[] {studID,userPassword},Integer.class);
			
			if(count2==0) {
				throw  new UserNotFoundException("Please provide valid credentials");
			}
			jdbcTemplate.update(SqlQueryConstants.STUDENT_PASSWORD_UPDATE,new Object[] {newpw,studID});
			return "Password Updated Successfully";
		}
		
	}
	else if(a[0].toLowerCase().equals("professor".trim())) {
		int profID=Integer.parseInt(a[1]);
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.PROFESSOR_ROLE_STUDENT,new Object[] {profID},Integer.class);
		if(count1==0) {
			throw  new UserNotFoundException("User ID  "+userId+"  is not yet approved by admin");
		}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.PROFESSOR_LOGIN_QUERY,new Object[] {profID,userPassword},Integer.class);
			
			if(count2==0) {
				throw  new UserNotFoundException("Please provide valid credentials");
			}
			jdbcTemplate.update(SqlQueryConstants.PROFESSOR_PASSWORD_UPDATE,new Object[] {newpw,profID});
			return "Password Updated Successfully";
		}

	}
	else if(a[0].toLowerCase().equals("admin".trim())) {
		int adminID=Integer.parseInt(a[1]);
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.USER_ROLE_ADMIN,new Object[] {adminID},Integer.class);
		if(count1==0) {
			throw  new UserNotFoundException("User ID  "+userId+"  is not having admin role");
		}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.ADMIN_LOGIN_QUERY,new Object[] {adminID,userPassword},Integer.class);
			
			if(count2==0) {
				throw  new UserNotFoundException("Please provide valid credentials");
			}
			jdbcTemplate.update(SqlQueryConstants.ADMIN_PASSWORD_UPDATE,new Object[] {newpw,adminID});
			return "Password Updated Successfully";
		}
		
		
		
	}
	
	
	throw  new UserNotFoundException("User with this credential is not found		"+userId);
	}
	catch(Exception e){
		throw  new UserNotFoundException("User with this credential is not found		"+userId);
	}
	
			


}


}
