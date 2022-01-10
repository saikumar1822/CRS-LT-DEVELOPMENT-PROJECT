package com.lt.crs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lt.crs.dto.StudentCourseDetails;

import com.lt.crs.model.Student_Course;

/**
 * @author Diwakar
 *
 */
@Service
public class ProfessorService implements ProfessorServiceInterface{
	@Autowired com.lt.crs.dao.ProfessorDAOInterface profDao;



	@Override
	public List<StudentCourseDetails> viewEnrolledStudents(int id) {
		// TODO Auto-generated method stub
		return profDao.viewEnrolledStudents(id);
	}




	@Override
	public String addGrades(Student_Course o, double marks) {
		// TODO Auto-generated method stub
		return profDao.addGrades(o,marks);
	}




	@Override
	public List<Student_Course> listOfStudentsToAddGrades(int pid) {
		// TODO Auto-generated method stub
		return profDao.listOfStudentsToAddGrades(pid);
	}




	@Override
	public List<Student_Course> listOfStudentsToUpdateGrades(int pid) {
		// TODO Auto-generated method stub
		return profDao.listOfStudentsToUpdateGrades(pid);
	}





}
