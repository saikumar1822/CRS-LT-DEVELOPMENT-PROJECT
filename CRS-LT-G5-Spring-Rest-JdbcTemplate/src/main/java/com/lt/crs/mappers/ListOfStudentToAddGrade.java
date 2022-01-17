package com.lt.crs.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import com.lt.crs.model.Student_Course;

/**
 * 
 * @author Rohan
 *
 */
public class ListOfStudentToAddGrade implements RowMapper<Student_Course> {

	@Override
	public Student_Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		com.lt.crs.model.Student_Course sc=new Student_Course();
		sc.setCourseId(rs.getInt(1));
		sc.setProfessorId(rs.getInt(2));
		sc.setStudentId(rs.getInt(3));
		return sc;
	}

}
