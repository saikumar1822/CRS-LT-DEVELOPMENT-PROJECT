package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.exception.PaymentIssueException;
import com.lt.crs.exception.StudentNotFoundException;
import com.lt.crs.model.Payment;
import com.mysql.jdbc.Statement;

/**
 * 
 * @author sai kumar,Rohan,Diwakar,Abdul,Siva,Nikil
 * Payment related all the methods are present here
 */

@Repository
public class PaymentDAOImpl implements PaymentDAOInterface {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	/**
	 * This method checks weather student has registered for the course and gets the course cost and payment details will added to payment table
	 * @param userId
	 * @param courseId
	 * @throws CourseNotRegisteredException
	 */
	@Transactional
	@Override
	public String makePayment(List<Payment> list, int studentId) {
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_EXISTS,new Object[] {studentId},Integer.class);
		if(count1==0) {
			throw new StudentNotFoundException("Student Does not exists");
		}
		else {
		
		Set<Integer> setOfCourse=new TreeSet<Integer>();
		
		Long count2=(Long)jdbcTemplate.queryForObject("select max(payment_id)+1 from payment",long.class);
		if(count2==null) {
			count2=1L;
		}
		String s1="(";
		String s3="(";
		String s5="";
		for(Payment p:list) {setOfCourse.add(p.getCourseId());
		s1=s1+p.getCourseId()+",";
		s3+="("+studentId+","+p.getCourseId()+")"+",";
		s5+="("+count2+","+studentId+","+p.getCourseId()+","+p.getCourseCost()+")"+",";
		}
		s1=s1.substring(0, s1.length()-1);
		s3=s3.substring(0, s3.length()-1);
		s5=s5.substring(0,s5.length()-1);
		if(list.size()==setOfCourse.size()) {
			//select * from course_professor_student where(course_id)  not in (1,3,2) and student_id=1;
			String s2="select count(*) from course_professor_student where (course_id) in "+s1+") and student_id=?";
			String s4="select count(*) from payment where (student_id,course_id) in "+s3+") and student_id=?";
			
			System.out.println(s4);
			System.out.println(s2);
			int count3=jdbcTemplate.queryForObject(s2,new Object[] {studentId},Integer.class);
			if(list.size()==count3) {
				int count4=jdbcTemplate.queryForObject(s4,new Object[] {studentId},Integer.class);
				if(count4==0) {
					String s6="insert into payment(payment_id,student_id,course_id,amount) values "+s5;
					System.out.println(s6);
					jdbcTemplate.update(s6);
					return "payment Successfully";
				}
				else {
					throw new PaymentIssueException("Already paid course can't be paid again");
				}
				
			}
			else {
				throw new PaymentIssueException("Can't Pay for Unregistered Course");
			}
			
			
			
		}
		else {
			throw new PaymentIssueException("Can't made payment for the same course twice");
		}
		
		// TODO Auto-generated method stub
		
	}
		
	
	
	}
}
