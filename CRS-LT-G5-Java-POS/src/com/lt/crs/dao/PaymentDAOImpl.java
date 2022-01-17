package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.exceptions.CourseNotRegisteredException;
import com.lt.crs.utilsDB.DBUtils;
import com.mysql.jdbc.Statement;

/**
 * 
 * @author sai kumar,Rohan,Diwakar,Abdul,Siva,Nikil
 *
 */
public class PaymentDAOImpl implements PaymentDAOInterface {
	private static Logger logger=Logger.getLogger(PaymentDAOImpl.class);

	Connection con = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;

	
	/**
	 * This method checks weather student has registered for the course and gets the course cost and payment details will added to payment table
	 * @param userId
	 * @param courseId
	 * @throws CourseNotRegisteredException
	 */
	public void makePayment(int studentId, int courseId) throws CourseNotRegisteredException
			 {
		con = DBUtils.getConnection();
		try {
			stmt = con.prepareStatement(SqlQueryConstants.student_course_check);
			stmt.setInt(1, studentId);
			stmt.setInt(2, courseId);
			rs = stmt.executeQuery();
			if (rs.next()) {
				rs.close();
				stmt = con.prepareStatement(SqlQueryConstants.get_course_cost);
				stmt.setInt(1, courseId);
				rs = stmt.executeQuery();
				double courseFee = 0;
				int count = 0;
				while (rs.next()) {
					count++;
					courseFee = rs.getDouble("course_cost");
				}
				rs.close();
				stmt = con.prepareStatement(SqlQueryConstants.payment_details,Statement.RETURN_GENERATED_KEYS);
				stmt.setInt(1, studentId);
				stmt.setInt(2, courseId);
				stmt.setString(3, "paid");
				stmt.setDouble(4, courseFee * count);
				stmt.executeUpdate();
				
				
				ResultSet result = stmt.getGeneratedKeys();
				if (result.next()) {

					System.out.println("PAID SUCCESSFULLY" +"  "+ "with Payment ID:   "+ " "+result.getInt(1));

				} else {
					logger.info("Transaction is unsucessful !");
				}

			} else {
				throw new CourseNotRegisteredException(
						"Course not Registered or Please Enter valid course Id");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
