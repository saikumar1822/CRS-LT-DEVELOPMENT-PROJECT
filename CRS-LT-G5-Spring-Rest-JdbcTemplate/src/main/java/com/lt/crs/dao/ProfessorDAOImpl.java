/**
 * 
 */
package com.lt.crs.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.exception.NoCourseRegisterException;
import com.lt.crs.mappers.ListOfStudentToAddGrade;
import com.lt.crs.mappers.StudentCourseDetailMapper;
import com.lt.crs.model.Student_Course;
import com.lt.crs.constants.SqlQueryConstants;


/**
 * 
 * @author Sai
 * All the Professor related operation done in this class 
 * Methods Add grade , Enroll Student , 
 *
 */
@Repository
public class ProfessorDAOImpl implements ProfessorDAOInterface{

	// Dummy database. Initialize with some dummy values.
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Transactional
	@Override
	public List<Student_Course> listOfStudentsToAddGrades(int pid){
		List<Student_Course> li=new ArrayList<Student_Course>();
		
		li=jdbcTemplate.query(SqlQueryConstants.LIST_OF_STUDENTS_TO_ADD_GRADES,new Object[] {pid},new ListOfStudentToAddGrade());
		if(li.isEmpty()) {
			throw new NoCourseRegisterException("Sorry Zero students enrolled");
		}
		return li;
	}



	@Transactional
	@Override
	public List<StudentCourseDetails> viewEnrolledStudents(int id) {
		
		int count=jdbcTemplate.queryForObject(SqlQueryConstants.PROFESSOR_EXISTS,new Object[] {id},Integer.class);
		
		if(count==0) {throw new com.lt.crs.exception.ProfessorNotFoundException("Professor ID does not exists");}
		else {
		List<StudentCourseDetails> li=new ArrayList<StudentCourseDetails>();
		// TODO Auto-generated method stub
		
		li=jdbcTemplate.query( SqlQueryConstants.LIST_ENROLLED_STUDENTS_FOR_PROFESSOR,new Object[] {id},new StudentCourseDetailMapper());
		
		if(li.isEmpty()) {
			throw new NoCourseRegisterException("Sorry Zero students enrolled");
		}
		return li;}
	}

	private static String findingGrade(double mark) {
		// TODO Auto-generated method stub
		if(mark>=0 && mark<50) {return "Fail";}
		else if(mark>=50 && mark<60){return "E";}
		else if(mark>=60 && mark<70){return "D";}
		else if(mark>=70 && mark<80){return "C";}
		else if(mark>=80 && mark<90){return "B";}
		else if(mark>=90 && mark<=100){return "A";}
		else {throw new NoCourseRegisterException("INVALID MARKS PROVIDED PLEASE PROVIDE MARKS BETWEEN 0 TO 100 ");}

	}

	@Transactional
	@Override
	public String addGrades(Student_Course o, double marks) {
		
		// TODO Auto-generated method stub
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.RECORD_OF_COURSE_PROFESSOR_STUDENT_EXISTS,new Object[] {o.getCourseId(),o.getProfessorId(),o.getStudentId()},Integer.class);
		if(count1==0) {throw new NoCourseRegisterException("Sorry records are not available ");}
		else {
			
			int checker=jdbcTemplate.queryForObject("select count(*) from payment p where p.course_id=? and p.student_id=?",new Object[] {o.getCourseId(),o.getStudentId()},Integer.class);
			if(checker==0) {
				throw new com.lt.crs.exception.PaymentIssueException("Student is not officially enrolled since payment is pending");
			}
			else {
			
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_GRADE_FOR_COURSE_EXISTS,new Object[] {o.getCourseId(),o.getProfessorId(),o.getStudentId()},Integer.class);
		
			if(count2==0) {
				
				String grade=findingGrade(marks);
				jdbcTemplate.update(SqlQueryConstants.INSERT_GRADE_FOR_STUDENT,new Object[] {o.getCourseId(),o.getProfessorId(),o.getStudentId(),marks,grade});
				return "successfully added";
			}
			else {
				String grade=findingGrade(marks);
				jdbcTemplate.update(SqlQueryConstants.UPDATE_GRADE_FOR_STUDENT,new Object[] {marks,grade,o.getCourseId(),o.getProfessorId(),o.getStudentId()});
		
				
				return "successfully updated";
			}
			}
		}
		
	}



	@Transactional
	@Override
	public List<Student_Course> listOfStudentsToUpdateGrades(int pid) {
		// TODO Auto-generated method stub
List<Student_Course> li=new ArrayList<Student_Course>();
		li=jdbcTemplate.query(SqlQueryConstants.LIST_OF_STUDENTS_TO_UPDATE_GRADES,new Object[] {pid},new ListOfStudentToAddGrade());

		if(li.isEmpty()) {
			throw new NoCourseRegisterException("Sorry No students Grade Available for upadtion of marks");
		}
		return li;
	}
}
