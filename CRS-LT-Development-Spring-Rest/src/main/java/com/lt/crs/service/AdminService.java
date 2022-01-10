package com.lt.crs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.dao.AdminDAOInterface;
import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.model.Course;
import com.lt.crs.model.Payment;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;


/**
 * @author Abdul
 *
 */

@Service
public class AdminService implements AdminServiceInterface{
	@Autowired
	AdminDAOInterface admin;

	@Override
	public List<Professor> list() {
		// TODO Auto-generated method stub
		return admin.list();
	}
	
	public List<Course> listOfCourses(){
		return admin.listOfCourses();
	}
	@Override
	public String approveStudent(int sid) {
		// TODO Auto-generated method stub
		return admin.approveStudent(sid);
	}
	@Override
	public List<Student> listOfUnApprovedStudents() {
		// TODO Auto-generated method stub
		return admin.listOfUnApprovedStudents();
	}

	@Override
	public String deleteStudent(int sid) {
		// TODO Auto-generated method stub
		return admin.deleteStudent(sid);
	}
	
	
	

	

	@Override
	public String createProfessor(Professor prof1) {
		// TODO Auto-generated method stub
		return admin.createProfessor(prof1);
	}

	@Override
	public String deleteProfessor(int id) {
		// TODO Auto-generated method stub
		return admin.deleteProfessor(id);
	}

	@Override
	public List<StudentCourseDetails> viewAllReportCard() {
		// TODO Auto-generated method stub
		return admin.viewAllReportCard();
	}

	@Override
	public String addCourse(Course c) {
		// TODO Auto-generated method stub
		return admin.addCourse(c);
	}

	@Override
	public List<Payment> listToDeletePaidStudentIfRequested() {
		// TODO Auto-generated method stub
		return admin.listToDeletePaidStudentIfRequested();
	}

	@Override
	public String deleteCourseForASingleStudent(int cid, int sid) {
		// TODO Auto-generated method stub
		return admin.deleteCourseForASingleStudent(cid, sid);
	}

	@Override
	public List<Course> listCourseWithLessThanThreeStudents() {
		// TODO Auto-generated method stub
		return admin.listCourseWithLessThanThreeStudents();
	}

	@Override
	public String mapCourseToProfessor(int cid, int pid) {
		// TODO Auto-generated method stub
		return admin.mapCourseToProfessor(cid,pid);
	}

	@Override
	public List<String> deleteCourse(int cid) {
		// TODO Auto-generated method stub
		return admin.deleteCourse(cid);
	}



	
	

	

}
