package com.lt.crs.dao;

import java.util.*;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.dto.ViewStudentsDto;
import com.lt.crs.exception.DropFailedException;
import com.lt.crs.exception.DuplicateCourseSelectionException;
import com.lt.crs.exception.NoCourseRegisterException;
import com.lt.crs.exception.NoReportCardException;
import com.lt.crs.exception.StudentNotFoundException;
import com.lt.crs.mappers.ProfessorMapper;
import com.lt.crs.mappers.StudentGradeMapper;
import com.lt.crs.mappers.UnPaidListMapper;
import com.lt.crs.model.Course;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;
import com.lt.crs.model.StudentGrade;
import com.lt.crs.model.Student_Course;
/**
 * 
 * @author Rohan,Sai
 * This Class contain all Student related activity 
 * Add Course,Drop Course,View Course
 *
 */
@Repository
public class StudentDAOImpl implements StudentDAOInterface{
	@Autowired
	JdbcTemplate jdbcTemplate;

	
	@Override
	public String addCourseToTheStudent(Student_Course student) {
		// TODO Auto-generated method stub
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_EXISTS,new Object[] {student.getStudentId()},Integer.class);
		if(count1==0) {
			throw new StudentNotFoundException("Student Does not exists");
		}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.TEACH_COURSE_BY_PROFESSOR_EXISTS,new Object[] {student.getCourseId()},Integer.class);
		
		if(count2==0) {
			throw new com.lt.crs.exception.CourseMapFoundException("Not able to enroll course without teaching professor");
		}
		else {
			int count3=jdbcTemplate.queryForObject(SqlQueryConstants.NUMBER_OF_COURSE_ENROLLED_BY_PARTICULAR_STUDENT,new Object[] {student.getStudentId()},Integer.class);
			if(count3==4) {
				throw new com.lt.crs.exception.MaxCourseRegistered("Sorry You have already registered four courses");
			}
			else {
			int count4=jdbcTemplate.queryForObject(SqlQueryConstants.VERIFY_COURSE_OF_STUDENT_EXISTS,new Object[] {student.getCourseId(),student.getStudentId()},Integer.class);
			
			if(count4==0) {
			List<Professor> pl=new ArrayList<Professor>();
		
			pl=jdbcTemplate.query(SqlQueryConstants.FETCH_PROFESSOR_LIST_FOR_PARTICULAR_COURSE,new Object[] {student.getCourseId()},new ProfessorMapper());	
		
			boolean insertion=false;
		
			for(Professor p:pl) {
				int count=jdbcTemplate.queryForObject(SqlQueryConstants.VALIDATING_NO_OF_STUDENTS_ENROLLED_TO_COURSE ,new Object[] {student.getCourseId(),p.getProfessorId()},Integer.class);
				
				if(count<10) {
					jdbcTemplate.update(SqlQueryConstants.ENROLL_STUDENT_FOR_COURSE,new Object[] {student.getCourseId(),p.getProfessorId(),student.getStudentId()});
					insertion=true;
				}
			

			}
				if(insertion==true) {return "Successfully enrolled course";}
				else {
					throw new com.lt.crs.exception.MaxCourseRegistered("Sorry No vacancy vailable So Please other courses");
				}
			}
			
			else{
			throw new DuplicateCourseSelectionException("This course is already registered by you please select another course");
			}
			}
		}
		
		}
		
		
	}
	//@Transactional
	@Override
	public String dropCourse(int studentId, int courseId) {
		// TODO Auto-generated method stub
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.VERIFY_COURSE_OF_STUDENT_EXISTS,new Object[] {courseId,studentId},Integer.class);
		if(count1==0) {
			throw new NoCourseRegisterException("Sorry not having such data to delete");
		}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.DONT_DROP_CHECK_STUDENT_GRADE_FOR_COURSE_EXISTS,new Object[] {courseId,studentId},Integer.class);
			
			if(count2==0) {
				
				int count3=jdbcTemplate.queryForObject(SqlQueryConstants.DROP_COURSE_BY_STUDENT_PAYMENT_CHECK,new Object[] {courseId,studentId},Integer.class);
				
				if(count3==0) {

					jdbcTemplate.update(SqlQueryConstants.DROP_COURSE_BY_THE_STUDENT,new Object[] {courseId,studentId});
					return "successfully dropped";
				}
				else {
					int che=jdbcTemplate.update("update payment set delete_request=\"yes\" where course_id=? and student_id=?",new Object[] {courseId,studentId});
					System.out.println(che);
			throw new DropFailedException("Sorry you can't drop the course Since payment is done It will be moved to admin to delete");
		
				}
				
}
			else {
			throw new DropFailedException("Sorry you can't drop the course since grade is assigned");
		}
		}
	}

	
	//@Transactional
	@Override
	public List<StudentCourseDetails> viewReportCard(int studentId) {
		// TODO Auto-generated method stub
	
		List<StudentCourseDetails> gradeList=new ArrayList<StudentCourseDetails>();
		
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_EXISTS,new Object[] {studentId},Integer.class);
		if(count1==0) {throw new StudentNotFoundException("Student ID does not exists");}
		else {
			
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_REGISTERED_FOR_COURSE_EXISTS,new Object[] {studentId},Integer.class);
			if(count2==0) {
				throw new NoCourseRegisterException("Sorry No courses enrolled");
			}
			else {
				
				int count3=jdbcTemplate.queryForObject(SqlQueryConstants.CHECK_STUDENT_GRADE_DATA_EXISTS,new Object[] {studentId},Integer.class);
				if(count3==0) {throw new NoReportCardException("Grade assigning is pending");}
				else {
					gradeList=jdbcTemplate.query(SqlQueryConstants.LIST_STUDENT_GRADE_DETAILS,new Object[] {studentId},new StudentGradeMapper());
				}
			}
			
			
		
		}
		
		return gradeList;
	}
	//@Transactional
	@Override
	public List<StudentCourseDetails> viewEnrolledCourses(int studentId) {
		// TODO Auto-generated method stub
		
		
	
	
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_EXISTS,new Object[] {studentId},Integer.class);
		if(count1==0) {throw new StudentNotFoundException("Student ID does not exists");}
		else {
			
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_REGISTERED_FOR_COURSE_EXISTS,new Object[] {studentId},Integer.class);
			if(count2==0) {
				throw new NoCourseRegisterException("Sorry No courses enrolled");
			}
			else {
	
				List<StudentCourseDetails> scd=new ArrayList<StudentCourseDetails>();
				scd=jdbcTemplate.query(SqlQueryConstants.LIST_OF_COURSE_DETAILS_OF_STUDENT,new Object[] {studentId},new com.lt.crs.mappers.StudentCourseDetailMapper());
				return scd;
				
			}
				
		}
		

	}
//	@Transactional
	@Override
	public List<Course> listUnpaidCourses(int studentId) {
		// TODO Auto-generated method stub
		List<Course> li=new ArrayList<Course>();
		
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_EXISTS,new Object[] {studentId},Integer.class);
		if(count1==0) {throw new StudentNotFoundException("Student ID does not exists");}
		else {
			li=jdbcTemplate.query(SqlQueryConstants.LIST_OF_COURSES_WHICH_ARE_UNPAID,new Object[] {studentId,studentId},new UnPaidListMapper());
			if(li.isEmpty()) {throw new NoCourseRegisterException("No Payment needed till now");}
			else {return li;}
			
		}
		
		
		
		
	}


}
