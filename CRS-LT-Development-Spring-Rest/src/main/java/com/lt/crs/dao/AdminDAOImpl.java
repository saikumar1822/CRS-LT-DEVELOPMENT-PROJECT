package com.lt.crs.dao;

import java.util.ArrayList;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.exception.CourseMapFoundException;
import com.lt.crs.exception.CourseNotFoundException;
import com.lt.crs.exception.NoDeleteRequestFoundException;
import com.lt.crs.exception.NoReportCardException;
import com.lt.crs.exception.ProfessorNotFoundException;
import com.lt.crs.mappers.StudentMapper;
import com.lt.crs.exception.StudentNotFoundException;
import com.lt.crs.exception.UserRoleFoundException;
import com.lt.crs.mappers.AllCoursessMapper;
import com.lt.crs.mappers.AllProfessorsMapper;
import com.lt.crs.mappers.ListCourseWithLessThanThreeStudentsMapper;
import com.lt.crs.mappers.PaymentDeleteMapper;
import com.lt.crs.mappers.PaymentDeleteRefundWithDeleteMapper;
import com.lt.crs.mappers.StudentGradeMapper;
import com.lt.crs.model.Course;
import com.lt.crs.model.Payment;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;


/**
 * 
 * @author Diwakar,Abdul
 * All Admin related operation methods present in this class
 * 
 *
 */

@Repository
public class AdminDAOImpl implements AdminDAOInterface{

	@Autowired
	JdbcTemplate jdbcTemplate;
	SimpleJdbcInsert simpleJdbcInsert;
	
	//@Transactional
	public List<Professor> list() {
		List<Professor> li=new ArrayList<Professor>();
		li=jdbcTemplate.query(SqlQueryConstants.LIST_ALL_PROFESSORS,new AllProfessorsMapper());
		if (li.size()==0) {
			throw new ProfessorNotFoundException("No Professors Available");
		}
		return li;
	}
	
	//@Transactional
	public List<Course> listOfCourses() {
		List<Course> li=new ArrayList<Course>();
		li=jdbcTemplate.query(SqlQueryConstants.LIST_ALL_COURSES,new AllCoursessMapper());
		if (li.size()==0) {
			throw new CourseNotFoundException("No Courses Available");
		}
		return li;
	}
	
	//@Transactional
	@Override
	public String approveStudent(int sid) {
		// TODO Auto-generated method stub
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_EXISTS,new Object[] {sid},Integer.class);
		if(count1==0) {
			throw new StudentNotFoundException("Student Does not exists");
		}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.CHECK_USER_ROLE_EXISTS_OR_NOT,new Object[] {sid,3},Integer.class);
			if(count2==0) {
			
				jdbcTemplate.update(SqlQueryConstants.ADMIN_APPROVE_STUDENT,new Object[] {sid,3});
				return "Successfull Approved";
				
			}
			else {
				throw new UserRoleFoundException("Role ID Found So can't insert");
			
			}
		
		}

	}
	
	//@Transactional
	@Override
	public List<Student> listOfUnApprovedStudents() {
		// TODO Auto-generated method stub
		List<Student> li=new ArrayList<Student>();
		li=jdbcTemplate.query(SqlQueryConstants.LIST_ALL_STUDENTS_FOR_APPROVAL,new Object[] {3},new StudentMapper());
		if (li.size()==0) {
			throw new StudentNotFoundException("No Students Available For Approval");
		}
		return li;
	}


	
	
	//@Transactional
	public String createProfessor(Professor prof1) {
		try {
		//	KeyHolder keyHolder = new GeneratedKeyHolder();
			//  jdbcTemplate.update(SqlQueryConstants.PROFESSOR_REGISTERATION,new Object[] {prof1.getProfessorName(),prof1.getProfessorPassword(),prof1.getProfessorDepartment()},keyHolder);
			
			simpleJdbcInsert=new SimpleJdbcInsert(jdbcTemplate);
			  simpleJdbcInsert.withTableName("professor").usingGeneratedKeyColumns("professor_id");

			  MapSqlParameterSource params = new MapSqlParameterSource()
				        .addValue("professor_name",prof1.getProfessorName())
				        .addValue("professor_password", prof1.getProfessorPassword())
				        .addValue("professor_department",prof1.getProfessorDepartment());
			 Number id = simpleJdbcInsert.executeAndReturnKey(params);

			jdbcTemplate.update(SqlQueryConstants.ADMIN_APPROVE_PROFESSOR,new Object[] {id.intValue(),2});	    
				    
				   
			  
			return "Sccessfully added your user id is   professor-"+id;
			}
			catch(Exception e) {
				throw new com.lt.crs.exception.IntegrityViolationException(e.getMessage());
			}
		
		
	}
	
	

	@Transactional
	public String deleteProfessor(int id) {

		
int count1=jdbcTemplate.queryForObject(SqlQueryConstants.PROFESSOR_EXISTS,new Object[] {id},Integer.class);
		
		if(count1==0) {throw new com.lt.crs.exception.ProfessorNotFoundException("Professor ID does not exists");}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.VERIFY_COURSE_OF_PROFESSOR_EXISTS,new Object[] {id},Integer.class);
			if(count2==0) {
				int upd1=jdbcTemplate.update(SqlQueryConstants.DELETE_PROFESSOR,new Object[] {id});
				int upd2=jdbcTemplate.update(SqlQueryConstants.DELETE_ROLE_OF_PROFESSOR,new Object[] {id,2});
				if(upd1==0 || upd2==0) {
					
					throw new com.lt.crs.exception.DropFailedException("Sorry some transaction error occurred");
				}
				else {
					return "DELETED PROFESSOR SUCCESSFULLY";
				}
				
			
			}
			else {
				throw new com.lt.crs.exception.ProfessorNotFoundException("Cannot remove professor whose course has been registered by the student");
			}
		}
		
		
	}




	//@Transactional
	public String deleteStudent(int id) {

		
int count1=jdbcTemplate.queryForObject(SqlQueryConstants.STUDENT_EXISTS,new Object[] {id},Integer.class);
		
		if(count1==0) {throw new com.lt.crs.exception.ProfessorNotFoundException("STUDENT ID does not exists");}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.VERIFY_COURSE_REGISTERED_BY_STUDNT_EXISTS,new Object[] {id},Integer.class);
			if(count2==0) {
		
				
				int count3=jdbcTemplate.queryForObject(SqlQueryConstants.CHECK_USER_ROLE_EXISTS_OR_NOT,new Object[] {id,3},Integer.class);
				if(count3==0) {
					jdbcTemplate.update(SqlQueryConstants.DELETE_STUDENT,new Object[] {id});
					return "DELETED STUDENT SUCCESSFULLY";
				}
				else {
					int upd1=jdbcTemplate.update(SqlQueryConstants.DELETE_STUDENT,new Object[] {id});
					int upd2=jdbcTemplate.update(SqlQueryConstants.DELETE_ROLE_OF_STUDENT,new Object[] {id,3});
					if(upd1==0 || upd2==0) {
						
						throw new com.lt.crs.exception.DropFailedException("Sorry some transaction error occurred");
					}
					else {
						return "DELETED STUDENT SUCCESSFULLY";
					}
				}
			
			
			}
			else {
				throw new com.lt.crs.exception.ProfessorNotFoundException("Cannot remove Student who has registered for the courses");
			}
		}
		
		
	}



	
	//@Transactional
	@Override
	public List<StudentCourseDetails> viewAllReportCard() {
		// TODO Auto-generated method stub
	
		List<StudentCourseDetails> gradeList=new ArrayList<StudentCourseDetails>();
		

		gradeList=jdbcTemplate.query(SqlQueryConstants.LIST_OF_ALL_STUDENT_GRADE_DETAILS,new StudentGradeMapper());

		if(gradeList.isEmpty()) {throw new NoReportCardException("No Report Cards Found");}	
		
	
		return gradeList;
	}
	
	
	

	@Transactional
	public String addCourse(Course c) {
		try {

			//  jdbcTemplate.update(SqlQueryConstants.COURSE_REGISTERATION,new Object[] {});
			
			int count1=jdbcTemplate.queryForObject(SqlQueryConstants.CHECKING_COURSE_EXISTS,new Object[] {c.getCourseName().toLowerCase().trim()},Integer.class);
					
					if(count1==0) {
						jdbcTemplate.update(SqlQueryConstants.ADD_COURSE,new Object[] {c.getCourseName().toLowerCase().trim(),c.getCourseCost()});
						return "Sccessfully added the course  "+c.getCourseName();
					}
					else {
						jdbcTemplate.update(SqlQueryConstants.UPDATE_COURSE,new Object[] {c.getCourseCost(),c.getCourseName().toLowerCase().trim()});
							return "Sccessfully Updated the course  "+c.getCourseName();
					}
			}
			catch(Exception e) {
				throw new com.lt.crs.exception.IntegrityViolationException(e.getMessage());
			}
		
		
	}

	@Transactional
	@Override
	public List<Payment> listToDeletePaidStudentIfRequested() {
		// TODO Auto-generated method stub
		List<Payment> li=new ArrayList<Payment>();
		li=jdbcTemplate.query("select * from payment where delete_request=\"yes\";",new PaymentDeleteMapper());
		if(li.isEmpty()) {
			throw new NoDeleteRequestFoundException("No students raised delete request for course ");
		}
		
		return li;
	}
	
	@Transactional
	@Override
	public String deleteCourseForASingleStudent(int cid,int sid) {
		StudentCourseDetails p=new StudentCourseDetails();
		//int count1=jdbcTemplate.queryForObject("select distinct count(*) from payment where course_id=? and student_id=?",new Object[] {cid,sid},Integer.class);
		int count1=jdbcTemplate.queryForObject("select distinct count(*)  from payment p inner join course_professor_student cps on cps.course_id=p.course_id and cps.student_id=p.student_id where p.course_id=? and p.student_id=? and p.delete_request=\"yes\"",new Object[] {cid,sid},Integer.class);
		if(count1==0) {
			throw new NoDeleteRequestFoundException("No  delete request raised for course ");
		}
		else {
		//	int count2=jdbcTemplate.queryForObject("select distinct count(*)  from payment p inner join course_professor_student cps on cps.course_id=p.course_id and cps.student_id=p.student_id where p.course_id=? and p.student_id=? and p.delete_request=\"yes\"",new Object[] {cid,sid},Integer.class);
		p=jdbcTemplate.queryForObject("select distinct *  from payment p inner join course_professor_student cps on cps.course_id=p.course_id and cps.student_id=p.student_id where p.course_id=? and p.student_id=? and p.delete_request=?",new Object[] {cid,sid,"yes"},new PaymentDeleteRefundWithDeleteMapper());
		
		jdbcTemplate.update("delete from payment where course_id=? and student_id=?",new Object[] {cid,sid});
		jdbcTemplate.update("delete from course_professor_student where course_id=? and professor_id=? and student_id=?",new Object[] {cid,p.getProfessor().getProfessorId(),sid});
		return "deleted student id "+p.getStudent().getStudentID()+" with course id "+p.getCourse().getCourseId()+" and the amount "+p.getCourse().getCourseCost()+" will be refunded to the corresponding soon";
	}
	
	}

	@Override
	public List<Course> listCourseWithLessThanThreeStudents() {
		// TODO Auto-generated method stub
		List<Course> li=new ArrayList<Course>();
		li=jdbcTemplate.query(SqlQueryConstants.DROP_COURSES_CONDITION_BASED_ON_COUNT_OF_STUDENTS_LESS_THAN_THREE,new AllCoursessMapper());
		if (li.size()==0) {
			throw new CourseNotFoundException("No Courses To Drop");
		}
		return li;
	}

	@Override
	public String mapCourseToProfessor(int cid, int pid) {
		// TODO Auto-generated method stub
int count1=jdbcTemplate.queryForObject(SqlQueryConstants.PROFESSOR_EXISTS,new Object[] {pid},Integer.class);
		
		if(count1==0) {throw new com.lt.crs.exception.ProfessorNotFoundException("Professor ID does not exists");}
		else {
			int count2=jdbcTemplate.queryForObject(SqlQueryConstants.COURSE_EXISTS,new Object[] {cid},Integer.class);
			if(count2==0) {throw new com.lt.crs.exception.CourseNotFoundException("Course ID does not exists");}
			else {
				int count3=jdbcTemplate.queryForObject(SqlQueryConstants.COURSE_PROFESSOR_EXISTS,new Object[] {cid,pid},Integer.class);
				if(count3==0) {
					jdbcTemplate.update(SqlQueryConstants.COURSE_PROFESSOR_MAP,new Object[] {cid,pid});
					return "mapped courses Successfully";
				}
		
				else {
					throw new CourseMapFoundException("Course Map found so can't add the same course again to the professor");
				}
			}	
		
	}
	}
	
	
	//@Transactional
	@Override
	public List<String> deleteCourse(int cid) {
		//StudentCourseDetails p=new StudentCourseDetails();
		//int count1=jdbcTemplate.queryForObject("select distinct count(*) from payment where course_id=? and student_id=?",new Object[] {cid,sid},Integer.class);
		
		int count1=jdbcTemplate.queryForObject(SqlQueryConstants.COURSE_EXISTS,new Object[] {cid},Integer.class);
		if(count1==0) {throw new com.lt.crs.exception.CourseNotFoundException("Course ID does not exists");}
		else {
			List<String> li=new ArrayList<String>();
			List<StudentCourseDetails> scd=new ArrayList<StudentCourseDetails>();
			List<Integer> courseList=new ArrayList<Integer>();
			
			courseList=jdbcTemplate.query(SqlQueryConstants.DROP_COURSES_CONDITION_BASED_ON_COUNT_OF_STUDENTS_LESS_THAN_THREE,new ListCourseWithLessThanThreeStudentsMapper());
			if(courseList.contains(new Integer(cid))) {
			//;
			scd=jdbcTemplate.query("select distinct *  from payment p inner join course_professor_student cps on cps.course_id=p.course_id and cps.student_id=p.student_id where p.course_id=?",new Object[] {cid},new PaymentDeleteRefundWithDeleteMapper());
			if(scd.isEmpty()) {
				jdbcTemplate.update("delete from payment where course_id=?",new Object[] {cid});
				jdbcTemplate.update("delete from course where course_id=?",new Object[] {cid});
				throw new NoDeleteRequestFoundException("No Refund is available since no payment done by any student");
			}
			else {
				//"deleted student id "+p.getStudent().getStudentID()+" with course id "+p.getCourse().getCourseId()+" and the amount "+p.getCourse().getCourseCost()+" will be refunded to the corresponding soon";
				for(StudentCourseDetails p:scd) {
					li.add("deleted student id "+p.getStudent().getStudentID()+" with course id "+p.getCourse().getCourseId()+" and the amount "+p.getCourse().getCourseCost()+" will be refunded to the corresponding soon");
				}
				jdbcTemplate.update("delete from payment where course_id=?",new Object[] {cid});
				jdbcTemplate.update("delete from course where course_id=?",new Object[] {cid});
				return li;
			}
			
			
			}
			else {
				
				throw new com.lt.crs.exception.CourseNotFoundException("Drop Failed due to the course has more than three students");
			}
			
			
			
		
		}
		
		
	}
	
	
	
	
	
	
	
	
	
}
