package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.bean.Course;
import com.lt.crs.bean.Professor;
import com.lt.crs.bean.Student;
import com.lt.crs.bean.StudentGrade;
import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.exceptions.DuplicateCourseSelectionException;
import com.lt.crs.exceptions.MaxCourseRegistered;
import com.lt.crs.exceptions.NoCourseInRegisteryException;
import com.lt.crs.exceptions.NoReportCardException;
import com.lt.crs.exceptions.NoVacanyException;
import com.lt.crs.utilsDB.DBUtils;

/**
 * 
 * @author sai kumar,Rohan,Diwakar,Abdul,Siva,Nikil
 *
 */
public class StudentDAOImpl implements StudentDAOInterface{
	private static Logger logger=Logger.getLogger(StudentDAOImpl.class);

	static Scanner sc=new Scanner(System.in);
	
	Connection con=null;
	LinkedList<Course> list1=new LinkedList<Course>();
	LinkedList<StudentCourseDetails> list2=new LinkedList<StudentCourseDetails>();
	LinkedList<StudentCourseDetails> list3=new LinkedList<StudentCourseDetails>();
	
	/**
	 *Using this method student can enroll for the course
	 * @param studentId
	 * @throws Exception
	 */
	
	@Override
	public void addCourse(int studentId) throws Exception {
		// TODO Auto-generated method stub
		
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		PreparedStatement ps2=null;
		PreparedStatement ps3=null;
		PreparedStatement ps4=null;
		PreparedStatement ps5=null;
	
		try {
			ps=con.prepareStatement(SqlQueryConstants.dao_student_addcourse_coursedetail);
			ResultSet rs=ps.executeQuery();

			
			while(rs.next()) {
				
				Course c=new Course();
				c.setCourseId(rs.getInt(1));
				c.setCourseName(rs.getString(2));
				list1.add(c);
//				System.out.println("Course ID\t\t"+rs.getInt(1)+"\t\t CourseName\t\t"+rs.getString(2).trim());
	
			}
			
			if(list1.isEmpty()){throw new NoCourseInRegisteryException("There is no professor to teach  the course");}
			System.out.println("\t\tCourse ID \t\tCourse Name");
			System.out.println("---------------------------------------------------------------");
			list1.stream().forEach(s->System.out.println("\t\t"+s.getCourseId()+"\t\t\t"+s.getCourseName()));
	
			ps1=con.prepareStatement(SqlQueryConstants.dao_student_addcourse_selectcourseenrolled);
			ps1.setInt(1,studentId);
			ResultSet rs1=ps1.executeQuery();
			int noOfCourse=0;
			while(rs1.next()) {
				noOfCourse+=1;
			}
			if(noOfCourse!=4) {
				System.out.println("You are eligible to select course");
				System.out.println("Enter Course ID from the above listed details");
				int couId=sc.nextInt();
				ps2=con.prepareStatement(SqlQueryConstants.dao_student_addcourse_selectprofessorfrom_courseprofessortable);//bug fix if rise
				ps2.setInt(1,couId);
				ResultSet rs2=ps2.executeQuery();
				boolean inserted=false;
				while(rs2.next()) {
					int profId=rs2.getInt(2);
					ps3=con.prepareStatement(SqlQueryConstants.dao_student_addcourse_validating_noofstudents);
					ps3.setInt(1,couId);
					ps3.setInt(2,profId);
					
					ResultSet rs3=ps3.executeQuery();
					int rows=0;
					if(rs3.next()) {
						rows=rs3.getInt(1);
										}
					if(rows>=10) {}
					else {
					ps4=con.prepareStatement(SqlQueryConstants.dao_student_addcourse_verifystudentcoursepresent);	
					ps4.setInt(1,couId);
					ps4.setInt(2,studentId);
					ResultSet rs4=ps4.executeQuery();
					if(rs4.next()) {
						throw new DuplicateCourseSelectionException("Sorry you have already registered this course So please select another");
					}
					else {
						ps5=con.prepareStatement(SqlQueryConstants.dao_student_addcourse);
						ps5.setInt(1,couId);
						ps5.setInt(2,profId);
						ps5.setInt(3,studentId);
						ps5.executeUpdate();
						logger.info("Inserted Successfully");
						inserted=true;
						break;
					}
					
					}
					
				
				}//while
				if(inserted==false) {throw new NoVacanyException(" The course ID you selected "
						+ " has no vacancy So please select another course ID");}
				
		
			}
				
			if(noOfCourse==4) {throw new MaxCourseRegistered("Sorry you have already registered four courses");}
		
			
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}
		if(ps1!=null) {ps1.close();}
		if(ps2!=null) {ps2.close();}
		if(ps3!=null) {ps3.close();}
		if(ps4!=null) {ps4.close();}
		if(ps5!=null) {ps5.close();}
		con.close();}catch(Exception e) {throw e;}		}


		}
	
	/**
	 * This method is used to drop the  course
	 * @param studentId
	 * @throws Exception
	 */
	
	@Override
	public void dropCourse(int studentId) throws Exception {
		// TODO Auto-generated method stub
		
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		
		
		try {
	ps=con.prepareStatement(SqlQueryConstants.dao_student_dropcourse_listcourseenrolled);
	ps.setInt(1,studentId);
	ResultSet rs=ps.executeQuery();
	int counter=0;
	while(rs.next()){
		counter+=1;
		int couId=rs.getInt(1);
		int profId=rs.getInt(2);
		int studId=rs.getInt(3);
		System.out.println("Do you want to Drop  the course ID : "+couId);
		char yesOrNo=sc.next().charAt(0);
		if(yesOrNo=='y' || yesOrNo=='Y'){
		ps1=con.prepareStatement(SqlQueryConstants.dao_student_dropcourse);
		ps1.setInt(1,couId);
		ps1.setInt(2,profId);
		ps1.setInt(3,studId);
		ps1.executeUpdate();
		System.out.println("Dropped the course ID "+couId);
		}
		
	}if(counter==0) {throw new com.lt.crs.exceptions.NoCourseRegisterException("No course registered yet");}
	
			
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}if(ps1!=null) {ps1.close();}con.close();}catch(Exception e) {throw e;}		}

		
	}
	@Override
	public void viewEnrolledCourses(int studentId) throws Exception {
		// TODO Auto-generated method stub
		
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		
		try {
	ps=con.prepareStatement(SqlQueryConstants.dao_student_viewenrolledcourse);
	ps.setInt(1,studentId);
	ResultSet rs=ps.executeQuery();
	
	while(rs.next()){
	
		int couId=rs.getInt(5);
		int profId=rs.getInt(6);
		int studId=rs.getInt(1);
		
		Student st=new Student();
		st.setStudentID(studId);
		st.setStudentName(rs.getString(2));
		Course c=new Course();
		c.setCourseId(couId);
		c.setCourseName(rs.getString(3));
		c.setCourseName(rs.getString(3));
			Professor p=new Professor();
			p.setProfessorId(profId);
			p.setProfessorName(rs.getString(4));
		StudentCourseDetails scd=new StudentCourseDetails();
		scd.setCourse(c);
	scd.setProfessor(p);
		scd.setStudent(st);
		list2.add(scd);
		
	//	System.out.println(" 	course ID  :	"+couId+"		professor ID : 		"+profId+" 		student Id: 	"+studId);;
		
	}if(list2.isEmpty()) {throw new com.lt.crs.exceptions.NoCourseRegisterException("No course registered yet");}
	else {
		
		System.out.println("\t Student ID \t\t Course ID \t\t Student Name \t\t\t Course Name \t \t\t\t Professor Name \t\tProfessor ID");
		System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
		list2.stream().forEach(s->System.out.println("\t\t"+s.getStudent().getStudentID()+"\t\t\t"+s.getCourse().getCourseId()
				+"\t\t\t"+s.getStudent().getStudentName()+"\t\t\t\t"+s.getCourse().getCourseName()
				+"\t\t\t\t"+s.getProfessor().getProfessorName()+"\t\t\t\t"+s.getProfessor().getProfessorId())
				);
	
		
	}
	
	
			
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}con.close();}catch(Exception e) {throw e;}		}

		
	}
	
	/**
	 * 
	 * @param studentId
	 * @throws Exception
	 */
	@Override
	public void viewReportCard(int studentId) throws Exception {
		// TODO Auto-generated method stub

		
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		
		try {
		
			ps=con.prepareStatement(SqlQueryConstants.dao_student_viewreportcard);
			ps.setInt(1,studentId);
			ResultSet rs=ps.executeQuery();
		
			while(rs.next()) {
			//System.out.println("\t\t"+rs.getInt(1)+"\t\t\t"+rs.getString(2)+"\t\t\t\t"+rs.getDouble(4)+"\t\t"+rs.getString(5));
				Student s=new Student();
				s.setStudentName(rs.getString(3));
				Course c=new Course();
				c.setCourseId(rs.getInt(1));
				c.setCourseName(rs.getString(2));
				StudentGrade sg=new StudentGrade();
				sg.setMark(rs.getDouble(4));
				sg.setGrade(rs.getString(5));
				StudentCourseDetails scd=new StudentCourseDetails();
				scd.setCourse(c);
				scd.setStudent(s);
				scd.setStudentGrade(sg);
				list3.add(scd);
			}
			if(list3.isEmpty()) {
				throw new NoReportCardException("No report Found");
			}else {
				System.out.println("Displaying report card for the student ID:  "+studentId);
				System.out.println("\tCourse ID \tCourse Name \t\t\t\tMark \t\t\tGrade");
				System.out.println("-------------------------------------------------------------------------------------------------");
				list3.stream().forEach(s->System.out.println("\t\t"+s.getCourse().getCourseId()+
						"\t\t"+s.getCourse().getCourseName()+"\t\t\t\t"+s.getStudentGrade().getMark()+"\t\t\t"+s.getStudentGrade().getGrade()));
			}
			
			
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}con.close();}catch(Exception e) {throw e;}		}

		
	}
		
		
	
}
