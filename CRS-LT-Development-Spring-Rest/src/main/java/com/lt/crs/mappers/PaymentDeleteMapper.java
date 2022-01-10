package com.lt.crs.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.lt.crs.model.Payment;


/**
 * 
 * @author Siva
 *
 */
public class PaymentDeleteMapper  implements RowMapper<Payment> {

	@Override
	public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		Payment p=new Payment();
		p.setPaymentId(rs.getInt(1));
		p.setStudentId(rs.getInt(2));
		p.setCourseId(rs.getInt(3));
		p.setCourseCost(rs.getDouble(4));
		p.setDeleteRequest(rs.getString(5));
		return p;
	}


}
