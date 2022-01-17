package com.lt.crs.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lt.crs.model.Course;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;
import com.lt.crs.dto.StudentCourseDetails;

/**
 * 
 * @author Diwakar,Sai,Abdul,Rohan,Siva
 * Student Course details are mapped in this class
 *
 */
public class StudentCourseDetailMapper implements RowMapper<StudentCourseDetails> {

	@Override
	public StudentCourseDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		StudentCourseDetails scd=new StudentCourseDetails();
		Student st=new Student();
		st.setStudentID(rs.getInt(1));
		st.setStudentName(rs.getString(2));
		Course c=new Course();
		c.setCourseId(rs.getInt(5));
		c.setCourseName(rs.getString(3));
		Professor p=new Professor();
		p.setProfessorName(rs.getString(4));
		
		scd.setCourse(c);
		scd.setProfessor(p);
		scd.setStudent(st);
		return scd;
	}


}
