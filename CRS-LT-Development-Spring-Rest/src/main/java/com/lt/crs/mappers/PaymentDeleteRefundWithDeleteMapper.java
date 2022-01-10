package com.lt.crs.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.model.Course;
import com.lt.crs.model.Professor;
import com.lt.crs.model.Student;

/**
 * 
 * @author Abdul
 *
 */
public class PaymentDeleteRefundWithDeleteMapper  implements RowMapper<StudentCourseDetails> {

	@Override
	public StudentCourseDetails mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		StudentCourseDetails scd=new StudentCourseDetails();
		Professor p=new Professor();
		p.setProfessorId(rs.getInt(7));
		Student s=new Student();
		s.setStudentID(rs.getInt(2));
		Course c=new Course();
		c.setCourseId(rs.getInt(3));
		c.setCourseCost(rs.getDouble(4));
		scd.setCourse(c);
		scd.setProfessor(p);
		scd.setStudent(s);
		
		return scd;
	}


}
