package com.lt.crs.dao;

import java.util.*;

import org.springframework.stereotype.Repository;

import com.lt.crs.bean.Course;
import com.lt.crs.bean.Professor;
import com.lt.crs.bean.Student;
import com.lt.crs.bean.StudentGrade;
import com.lt.crs.bean.Student_Course;
import com.lt.crs.dto.ViewStudentsDto;

@Repository
public class StudentDAOImpl implements StudentDAOInterface{

	public static List<Student_Course> stc=new ArrayList<Student_Course>();
	
	@Override
	public String addCourseToTheStudent(Student_Course student) {
		// TODO Auto-generated method stub
		Student_Course s=new Student_Course();
		s.setCourseId(student.getCourseId());
	
		s.setStudentId(student.getStudentId());
		s.setProfessorId(student.getProfessorId());
		
		if(stc.size()==4) {
			return "Sorry Already registered four courses";
		}else {
		stc.add(s);
		}
		System.out.println(stc);
		return "Successfully added";
		
	}

	@Override
	public String dropCourse(int studentId, int courseId) {
		// TODO Auto-generated method stub
		Iterator itr = stc.iterator();
		while (itr.hasNext()) {
			Student_Course s=(Student_Course)itr.next();
			if(s.getStudentId()==studentId && s.getCourseId()==courseId) {itr.remove();return "dropped Successfully";}	
		}
		
		return "No Such Entries are available";
		
	}

	@Override
	public List<StudentGrade> viewReportCard(int studentId) {
		// TODO Auto-generated method stub
		List<StudentGrade> gradeList=new ArrayList<StudentGrade>();
		
		for(Student_Course s:stc) {
			if(s.getStudentId()==studentId) {
			StudentGrade grade=new StudentGrade();
			grade.setStudentId(studentId);
			grade.setCourseId(s.getCourseId());
			grade.setProfessorId(s.getProfessorId());

			grade.setMark(79);
			grade.setGrade("C");
			gradeList.add(grade);
			}
			
		}
		
		return gradeList;
	}

	@Override
	public ViewStudentsDto viewEnrolledCourses(int studentId) {
		// TODO Auto-generated method stub
		ViewStudentsDto stdto=new ViewStudentsDto();
		Student stud=new Student();
		stud.setStudentID(studentId);
		stud.setStudentDepartment("IT");
		stud.setStudentName("Deepika");

		stdto.setStudent(stud);
		List<Course> course=new ArrayList<Course>();
		List<Professor> professor=new ArrayList<Professor>();
		for(Student_Course s:stc) {
			Course c=new Course();
			Professor p=new Professor();
			c.setCourseId(s.getCourseId());
			c.setCourseCost(1000);
			c.setCourseName("JAVA");
			p.setProfessorId(s.getProfessorId());
			p.setProfessorDepartment("IT");
			p.setProfessorName("Amit");

			course.add(c);
			professor.add(p);
		}
		stdto.setCourse(course);
		stdto.setProfessor(professor);
		return stdto;
	}

	@Override
	public String payment(int studentId) {
		// TODO Auto-generated method stub
		double amount=0;
		
		
		return "Payment done successfully!";
	}

}
