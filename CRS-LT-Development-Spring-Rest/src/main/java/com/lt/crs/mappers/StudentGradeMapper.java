package com.lt.crs.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;


import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.model.Course;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;
import com.lt.crs.model.StudentGrade;

/**
 * 
 * @author Diwakar,Sai,Abdul,Rohan,Siva
 * Student Grade are mapped in this class
 *
 */

public class StudentGradeMapper implements RowMapper<StudentCourseDetails> {

	@Override
	public StudentCourseDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Student s=new Student();
		s.setStudentName(rs.getString(3));
		s.setStudentID(rs.getInt(6));
		Course c=new Course();
		c.setCourseId(rs.getInt(1));
		c.setCourseName(rs.getString(2));
		StudentGrade sg=new StudentGrade();
		sg.setMark(rs.getDouble(4));
		sg.setGrade(rs.getString(5));
		Professor p=new Professor();
		p.setProfessorId(rs.getInt(7));
		p.setProfessorName(rs.getString(8));
		
		StudentCourseDetails scd=new StudentCourseDetails();
		scd.setCourse(c);
		scd.setStudent(s);
		scd.setStudentGrade(sg);
		scd.setProfessor(p);
		return scd;
	}

	

}
