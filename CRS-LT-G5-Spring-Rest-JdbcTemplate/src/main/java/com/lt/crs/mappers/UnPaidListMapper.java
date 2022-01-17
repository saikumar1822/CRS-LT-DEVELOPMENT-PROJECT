package com.lt.crs.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.lt.crs.model.Course;

/**
 * 
 * @author Siva
 *
 */
public class UnPaidListMapper implements RowMapper<Course>{
	@Override
	public Course mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
	
		Course c=new Course();
	c.setCourseId(rs.getInt(1));
	c.setCourseCost(rs.getDouble(2));
	c.setCourseName(rs.getString(3));
		return c;
	}


}
