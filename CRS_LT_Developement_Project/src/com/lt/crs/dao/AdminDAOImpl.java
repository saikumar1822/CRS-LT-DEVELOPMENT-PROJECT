
package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.bean.Course;
import com.lt.crs.bean.Student;
import com.lt.crs.bean.StudentGrade;
import com.lt.crs.business.AdminOperation;
import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.exceptions.CourseDuplicationException;
import com.lt.crs.exceptions.CourseMapFoundException;
import com.lt.crs.exceptions.NoCourseInRegisteryException;
import com.lt.crs.exceptions.NoCourseRegisterException;
import com.lt.crs.exceptions.NoReportCardException;
import com.lt.crs.exceptions.NoStudentRegisteredException;
import com.lt.crs.exceptions.ProfessorNotFoundException;
import com.lt.crs.exceptions.StudentNotFoundException;
import com.lt.crs.utilsDB.DBUtils;

/**
 * 
 * @author Rohan,diwikar,sai kumar, Abdul
 * 
 */
public class AdminDAOImpl implements AdminDAOInterface {
	private static Logger logger=Logger.getLogger(AdminDAOImpl.class);

	LinkedList<StudentCourseDetails> list3 = new LinkedList<StudentCourseDetails>();
	Connection con = null;

	static Scanner sc = new Scanner(System.in);

	LinkedList<Course> list1 = new LinkedList<Course>();
	LinkedList<Course> list2 = new LinkedList<Course>();

	/**
	 * @throws Exception addProfessor() is used to add the professor by the admin
	 *                   Professor details will be inserted into professor table and
	 *                   professor role will be assigned
	 */

	@Override
	public void addProfessor() throws Exception {

		con = DBUtils.getConnection();

		// TODO Auto-generated method stub

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;
		PreparedStatement ps3 = null;

		try {

			System.out.println("Do you want to add an existing professor  Y or N ");
			char addOrNot = sc.next().charAt(0);
			if (addOrNot == 'y' || addOrNot == 'Y') {

				System.out.println("Enter the existing  professor ID ");

				// TODO Auto-generated method stub
				int profId = sc.nextInt();

				ps = con.prepareStatement(SqlQueryConstants.dao_admin_addprofessor_existingprofessor);
				ps.setInt(1, profId);
				ps.execute();

				ResultSet rs = ps.executeQuery();
				if (rs.next()) {

					int profID = rs.getInt(1);

					//
					PreparedStatement psc5 = con
							.prepareStatement(SqlQueryConstants.dao_admin_addprofessor_listallcourses);
					ResultSet rscp11 = psc5.executeQuery();

					while (rscp11.next()) {
						Course c = new Course();
						c.setCourseId(rscp11.getInt(1));
						c.setCourseName(rscp11.getString(2));
						list2.add(c);
						// System.out.println("Course ID\t\t"+rsp11.getInt(1)+"\t\t
						// CourseName\t\t"+rsp11.getString(2).trim());

					}

					if (list2.isEmpty()) {
						throw new NoCourseInRegisteryException(
								"There is no courses in the course table So can't add new professor with zero course");
					} else {
						//
						System.out.println("\tCourse ID \tCourse Name");
						list2.stream().forEach(
								s -> System.out.println("\t\t" + s.getCourseId() + "\t\t" + s.getCourseName()));
						System.out.println("select your course from the above table ");
						int professorCourse = sc.nextInt();

						PreparedStatement p1 = con.prepareStatement(
								SqlQueryConstants.dao_admin_addprofessor_checkforduplicatecourseenroll_byprofessor);
						p1.setInt(2, profID);
						p1.setInt(1, professorCourse);
						ResultSet r1 = p1.executeQuery();
						if (r1.next()) {
							throw new CourseDuplicationException("Already present in Course Professor table");
						}

						else {

							ps1 = con.prepareStatement(SqlQueryConstants.dao_admin_addprofessor_addcourseforprofessor);
							ps1.setInt(2, profID);
							ps1.setInt(1, professorCourse);
							ps1.executeUpdate();
							logger.info("Successfully added the data in the course professor table");

						}
					}

				} else {
					throw new ProfessorNotFoundException(
							"Sorry No such professor data is available in professor table");
				} // throw

			} else {

				PreparedStatement ps5 = con.prepareStatement(SqlQueryConstants.dao_admin_addprofessor_listallcourses);
				ResultSet rsp11 = ps5.executeQuery();

				while (rsp11.next()) {
					Course c = new Course();
					c.setCourseId(rsp11.getInt(1));
					c.setCourseName(rsp11.getString(2));
					list1.add(c);
					// System.out.println("Course ID\t\t"+rsp11.getInt(1)+"\t\t
					// CourseName\t\t"+rsp11.getString(2).trim());

				}

				if (list1.isEmpty()) {
					throw new NoCourseInRegisteryException(
							"There is no courses in the course table So can't add new professor with zero course");
				}

				System.out.println("\tCourse ID \tCourse Name");
				list1.stream().forEach(s -> System.out.println("\t\t" + s.getCourseId() + "\t\t" + s.getCourseName()));

				System.out.println("Enter Professor Name");
				String professorName = sc.next();
				System.out.println("Enter Department of the Professor");
				String professorDept = sc.next();
				System.out.println("Enter the password for the professor");
				String professorPassword = sc.next();
				System.out.println("select your course from the above table ");
				int professorCourse = sc.nextInt();

				ps2 = con.prepareStatement(SqlQueryConstants.dao_admin_addprofessor,
						java.sql.Statement.RETURN_GENERATED_KEYS);
				ps2.setString(1, professorName);
				ps2.setString(2, professorPassword);
				ps2.setString(3, professorDept);
				ps2.setInt(4, professorCourse);
				ps2.executeUpdate();
				ResultSet rs = ps2.getGeneratedKeys();
				if (rs.next()) {
					int profID = rs.getInt(1);
					PreparedStatement ps4 = con
							.prepareStatement(SqlQueryConstants.dao_admin_addprofessor_addcourseforprofessor);
					ps4.setInt(1, professorCourse);
					ps4.setInt(2, profID);
					ps4.executeUpdate();
					ps4.close();

					System.out.println("Hello Admin the Professor ID for the added professor  is : " + rs.getInt(1)
							+ "and now adding to the USER_ROLE table");

					ps3 = con.prepareStatement("insert into user_role values(?,2)");
					ps3.setInt(1, profID);
					ps3.executeUpdate();
					logger.info("Successfully added the professor ID to the user_role");
					System.out
							.println("Please note the Professor ID for future reference : 	" + "professor-" + profID);
					// Query to insert into course_professor

				}

			}

		}

		catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (ps2 != null) {
					ps2.close();
				}
				if (ps3 != null) {
					ps3.close();
				}
				con.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	/**
	 * removeProfessor() removes the professor from the professor table first it
	 * will check weather the professor is teaching any course if teaching any
	 * course professor will not be removed if professor is not teaching any course
	 * then prof will be removed
	 */

	@Override
	public void removeProfessor() throws Exception {

		con = DBUtils.getConnection();

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;

		try {

			System.out.println("Enter the professor ID you want to remove");

			// TODO Auto-generated method stub
			int profId = sc.nextInt();

			ps = con.prepareStatement(SqlQueryConstants.dao_admin_removeprofessor_checkingprof);
			ps.setInt(1, profId);
			ResultSet rsp = ps.executeQuery();
			if (rsp.next()) {
				throw new CourseMapFoundException(
						"Sorry you cant remove the Professor who has been assigned to teach course");

			} else {

				ps1 = con.prepareStatement(SqlQueryConstants.dao_admin_addprofessor_existingprofessor);
				ps1.setInt(1, profId);
				ps1.execute();

				ResultSet rs = ps1.executeQuery();
				if (rs.next()) {

					int profID = rs.getInt(1);
					PreparedStatement p1 = con.prepareStatement(SqlQueryConstants.dao_admin_removeprofessor);
					p1.setInt(1, profID);
					p1.executeUpdate();

					ps2 = con.prepareStatement(SqlQueryConstants.dao_admin_removingprofessorrole);
					ps2.setInt(1, profID);
					ps2.executeUpdate();
					System.out.println("Successfully deleted the professor ID" + profID + " from the user_role");

				} else {
					throw new ProfessorNotFoundException(
							"Sorry No such professor data is available in professor table");
				}

			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (ps1 != null) {
					ps1.close();
				}
				if (ps2 != null) {
					ps2.close();
				}
				con.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	/**
	 * Add Student method is for approving the student registration This method will
	 * fetches the all the availble students from the database and checks in the
	 * user_role table if found student is already approved else admin will be
	 * approve the student and also admin can reject the student registration
	 */
	@Override
	public void addStudent() throws Exception {
		// TODO Auto-generated method stub

		con = DBUtils.getConnection();

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;

		try {
			ps = con.prepareStatement("select * from student");
			ResultSet rs = ps.executeQuery();
			int counter = 0;
			while (rs.next()) {
				counter += 1;
				try {
					System.out.println("Do you want to approve student whose student ID is : " + rs.getInt(1));

					ps2 = con.prepareStatement(SqlQueryConstants.dao_admin_selectingallstudents);
					ps2.setInt(1, rs.getInt(1));
					ResultSet rs1 = ps2.executeQuery();

					if (rs1.next()) {
						System.out.println("strudent ID " + rs.getInt(1) + " is already approved");
					} else {
						char addOrNot = sc.next().charAt(0);
						if (addOrNot == 'y' || addOrNot == 'Y') {
							ps1 = con.prepareStatement(SqlQueryConstants.dao_admin_approving_student);
							ps1.setInt(1, rs.getInt(1));
							ps1.executeUpdate();
							System.out.println("Student Id " + rs.getInt(1) + " inserted successfully");

						} else {
							System.out.println("Admin rejected the student with student ID" + rs.getInt(1));
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
			if (counter == 0) {
				throw new NoStudentRegisteredException("Zero student registered till now");
			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (ps1 != null) {
					ps1.close();
				}
				if (ps2 != null) {
					ps2.close();
				}
				con.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	/**
	 * remove student() first checks the weather student is taking any course or not
	 * if not student will be removed
	 */

	@Override
	public void removeStudent() throws Exception {
		// TODO Auto-generated method stub

		con = DBUtils.getConnection();

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		PreparedStatement ps2 = null;

		try {

			System.out.println("Enter the Student ID you want to remove");

			// TODO Auto-generated method stub
			int studId = sc.nextInt();

			ps = con.prepareStatement(SqlQueryConstants.dao_admin_removestudent_checkingstud);
			ps.setInt(1, studId);
			ResultSet rsp = ps.executeQuery();
			if (rsp.next()) {
				throw new CourseMapFoundException(
						"Sorry you cant remove the Student  who has been assigned to take the  course");

			} else {

				ps1 = con.prepareStatement(SqlQueryConstants.dao_admin_existingstudent);
				ps1.setInt(1, studId);
				ps1.execute();

				ResultSet rs = ps1.executeQuery();
				if (rs.next()) {

					int studID = rs.getInt(1);
					PreparedStatement p1 = con.prepareStatement(SqlQueryConstants.dao_admin_removestudent);
					p1.setInt(1, studID);
					p1.executeUpdate();

					ps2 = con.prepareStatement(SqlQueryConstants.dao_admin_removingstudentrole);
					ps2.setInt(1, studID);
					ps2.executeUpdate();
					System.out.println("Successfully deleted the Student ID" + studID + " from the user_role");

				} else {
					throw new StudentNotFoundException("Sorry No such student  data is available in student  table");
				}

			}
		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (ps1 != null) {
					ps1.close();
				}
				if (ps2 != null) {
					ps2.close();
				}
				con.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	/**
	 * Add course method will checks weather the course already present or not if
	 * not presented then new course will be added into course table
	 */

	@Override
	public void addCourse() throws Exception {
		// TODO Auto-generated method stub

		con = DBUtils.getConnection();

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;
		try {

			System.out.println("Enter Course Name");
			String courseName = sc.next();
			System.out.println("Enter Course Cost");
			double courseCost = sc.nextDouble();

			ps = con.prepareStatement(SqlQueryConstants.checking_course_exists);
			ps.setString(1, courseName.toLowerCase());
			;
			ResultSet rs = ps.executeQuery();
			if (!rs.next()) {

				ps1 = con.prepareStatement(SqlQueryConstants.add_course);
				ps1.setString(1, courseName.toLowerCase());
				;
				ps1.setDouble(2, courseCost);

				ps1.executeUpdate();
				System.out.println(" Course Added Successfully3.");
			} else {
				// throw new CourseDuplicationException("Course Already present So not able to
				// add course which is already existed ");
				System.out.println("Do you want to update the existing course");
				char ask1 = sc.next().charAt(0);
				if (ask1 == 'y' || ask1 == 'Y') {

					PreparedStatement pscheck111 = con.prepareStatement(SqlQueryConstants.update_course);
					pscheck111.setString(2, courseName.toLowerCase());
					;
					pscheck111.setDouble(1, courseCost);
					pscheck111.executeUpdate();
					System.out.println("Course Updated  Successfully");
				} else {
					throw new CourseDuplicationException("Admin not want to update the existing course");
				}

			}
		}

		catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (ps1 != null) {
					ps1.close();
				}
				con.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	/**
	 * Removecourse method will remove the course if less students are registered
	 * for that course
	 */
	@Override
	public void removeCourse() throws Exception {
		// TODO Auto-generated method stub

		con = DBUtils.getConnection();

		PreparedStatement ps = null;
		PreparedStatement ps1 = null;

		try {
			ps = con.prepareStatement(SqlQueryConstants.dao_admin_coursewithtotalcount_ofstudentenroll);
			ResultSet rs = ps.executeQuery();
			int counter = 0;

			while (rs.next()) {
				counter += 1;
				int coId = rs.getInt(1);
				int profId = rs.getInt(2);
				int count = rs.getInt(3);
				if (count < 3) {
					System.out.println("Do you want to remove Y or N    " + coId);
					char ask = sc.next().charAt(0);
					if (ask == 'y' || ask == 'Y') {

						System.out.println("Removing the course with students less than the desired student");
						ps1 = con.prepareStatement(SqlQueryConstants.dao_admin_fornotification);
						ps1.setInt(1, coId);
						ps1.setInt(2, profId);
						ResultSet rs1 = ps1.executeQuery();
						System.out.println("Append all the corresponding students ID in the list "
								+ "and send them notification regard choose of another course with money refund if they paid for the particular course");
						while (rs1.next()) {

							System.out.println("student ID: " + rs1.getInt(3));
						}
						// Delete Query
						PreparedStatement p1 = con.prepareStatement(SqlQueryConstants.delete_course);
						p1.setInt(1, coId);
						p1.executeUpdate();

					}
				}

			}

			if (counter == 0) {
				throw new NoCourseRegisterException("There is no courses registered till now");
			}

		}

		catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				if (ps1 != null) {
					ps1.close();
				}
				con.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}

	/**
	 * ReportCardGeneration method generates the grades for students
	 */
	@Override
	public void reportCardGeneration() throws Exception {
		// TODO Auto-generated method stub

		con = DBUtils.getConnection();

		PreparedStatement ps = null;

		try {

			ps = con.prepareStatement(com.lt.crs.constants.SqlQueryConstants.dao_admin_gradeofallstudents);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				Student s = new Student();
				s.setStudentID(rs.getInt(6));
				s.setStudentName(rs.getString(3));
				Course c = new Course();
				c.setCourseId(rs.getInt(1));
				c.setCourseName(rs.getString(2));
				StudentGrade sg = new StudentGrade();
				sg.setMark(rs.getDouble(4));
				sg.setGrade(rs.getString(5));
				StudentCourseDetails scd = new StudentCourseDetails();
				scd.setCourse(c);
				scd.setStudent(s);
				scd.setStudentGrade(sg);
				list3.add(scd);

//				System.out.println("******************* Displaying report card for Student ID:" +rs.getInt(6)+" and Student Name :"+rs.getString(3)+"******************  ");
//			System.out.println("\t\t course ID        "+rs.getInt(1)+"\t\t\t Course Name      "+rs.getString(2)+"\t\t\t\tMark     "+rs.getDouble(4)+"\t\tGrade       "+rs.getString(5));
			}

			if (list3.isEmpty()) {
				throw new NoReportCardException("There is no report card available");
			} else {

				System.out.println("\tStudent ID \tStudent Name \tCourse ID \tCourse Name \tMark \tGrade");
				System.out.println("--------------------------------------------------------------------------------------------------");
				list3.stream()
						.forEach(s -> System.out.println("\t\t" + s.getStudent().getStudentID() + "\t"
								+ s.getStudent().getStudentName() + "\t\t  " + s.getCourse().getCourseId() + "\t\t"
								+ s.getCourse().getCourseName() + "\t\t" + s.getStudentGrade().getMark() + "\t"
								+ s.getStudentGrade().getGrade()));

			}

		} catch (Exception e) {
			throw e;
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
				con.close();
			} catch (Exception e) {
				throw e;
			}
		}

	}
}