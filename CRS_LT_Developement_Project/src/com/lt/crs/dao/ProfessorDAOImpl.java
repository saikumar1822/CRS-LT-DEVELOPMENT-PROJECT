package com.lt.crs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.lt.crs.bean.Course;
import com.lt.crs.bean.Professor;
import com.lt.crs.bean.Student;
import com.lt.crs.constants.SqlQueryConstants;
import com.lt.crs.dto.StudentCourseDetails;
import com.lt.crs.exceptions.NoStudentRegisteredException;
import com.lt.crs.utilsDB.DBUtils;

/**
 * @author sai kumar,Rohan,Diwakar,Abdul,Siva,Nikil
 * This is ProfessorDAOImpl class 
 */
public class ProfessorDAOImpl implements ProfessorDAOInterface{
	private static Logger logger=Logger.getLogger(ProfessorDAOImpl.class);

	
	static Scanner sc=new Scanner(System.in);
	
	LinkedList<StudentCourseDetails> list1=new LinkedList<StudentCourseDetails>();


	Connection con=null;
	

	/**
	 * this method shows the enrolled students
	 * @param profId
	 * @throws Exception
	 */
	@Override
	public void viewStudent(int profId) throws Exception {
		// TODO Auto-generated method stub
	
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		
		try {
			ps=con.prepareStatement(SqlQueryConstants.dao_professor_viewstudent);
			ps.setInt(1,profId);
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
			Student st=new Student();
			st.setStudentID(rs.getInt(1));
			st.setStudentName(rs.getString(2));
			Course c=new Course();
			c.setCourseId(rs.getInt(5));
			c.setCourseName(rs.getString(3));
			Professor p=new Professor();
			p.setProfessorName(rs.getString(4));
			
			StudentCourseDetails scd=new StudentCourseDetails();
			scd.setCourse(c);
			scd.setProfessor(p);
			scd.setStudent(st);
			list1.add(scd);
//				System.out.println("Student ID : 	"+rs.getInt(1)+
//						"		Student Name:		"+rs.getString(2)+"		Course Name: 		"+rs.getString(3)+" 	Professor Name :	"+rs.getString(4));
				
				
			}
			if(list1.isEmpty()) {throw new NoStudentRegisteredException("No students registered for your course");}
			else {
				System.out.println("\t Student ID \t\t Course ID \t\t Student Name \t\t\t Course Name \t \t\t Professor Name");
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
				list1.stream().forEach(s->System.out.println("\t\t"+s.getStudent().getStudentID()+"\t\t\t"+s.getCourse().getCourseId()+"\t\t\t"+s.getStudent().getStudentName()+"\t\t\t\t"+s.getCourse().getCourseName()+"\t\t\t\t"+s.getProfessor().getProfessorName())
						);
			}
			
			
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}con.close();}catch(Exception e) {throw e;}		}

		
	}

	/**
	 * This method Add the grade for the student
	 * @param profId
	 * @throws Exception
	 */

	@Override
	public void addGrade(int profId) throws Exception {
		// TODO Auto-generated method stub
		
		con=DBUtils.getConnection();
		PreparedStatement ps=null;
		PreparedStatement ps1=null;
		try {
			ps=con.prepareStatement(SqlQueryConstants.dao_professor_viewsalltudent);
			ps.setInt(1,profId);
			ResultSet rs=ps.executeQuery();
			int count=0;
			while(rs.next()) {
				count+=1;
				System.out.println("Do you want to add Grade to the Following Student: ");
				System.out.println("\tStudent ID \t Student Name \t \t Course Name\t Professor Name \t Course ID");
				System.out.println("------------------------------------------------------------------------------------------------------------------------");
				System.out.println("\t\t"+rs.getInt(1)+"\t\t"+rs.getString(2)+"\t\t "+rs.getString(3)+"\t\t  "+rs.getString(4)+"\t\t\t    "+rs.getInt(5));
				char ask=sc.next().charAt(0);
				if(ask=='y' || ask=='Y') {
					System.out.println("Adding Grade :");
					try {
						System.out.println("Enter Mark : ");
						double mark=sc.nextDouble();
						String grade=findingGrade(mark);
						
						PreparedStatement pscheck=con.prepareStatement(SqlQueryConstants.dao_professor_verifystudentgradepresent);
						pscheck.setInt(1,rs.getInt(5));
						pscheck.setInt(2,profId);
						pscheck.setInt(3,rs.getInt(1));
						ResultSet rscheck=pscheck.executeQuery();
						if(rscheck.next()) {
							System.out.println("Do you want to update grade");
							char ask1=sc.next().charAt(0);
							if(ask1=='y' || ask1=='Y') {
							
						PreparedStatement pscheck1	=con.prepareStatement(SqlQueryConstants.dao_professor_updategrade);
						pscheck1.setInt(3,rs.getInt(5));
						pscheck1.setInt(4,profId);
						pscheck1.setInt(5,rs.getInt(1));
						pscheck1.setDouble(1,mark);
						pscheck1.setString(2,grade);
						pscheck1.executeUpdate();
						System.out.println("Grade Updated  Successfully");
						}
						
							
						}
						
						else {
						ps1=con.prepareStatement(SqlQueryConstants.dao_professor_addgrade);
						ps1.setInt(1,rs.getInt(5));
						ps1.setInt(2,profId);
						ps1.setInt(3,rs.getInt(1));
						ps1.setDouble(4,mark);
						ps1.setString(5,grade);
						ps1.executeUpdate();
						logger.info("Grade Added Successfully");
						}
											
					}catch(Exception e) {throw e;}
				}
			
				
			}
			if(count==0) {
				throw new NoStudentRegisteredException("No students registered for your course");
			}
			
			
			
			
		}
		catch(Exception e) {throw e;}	finally {try {if(ps!=null) {ps.close();}if(ps1!=null) {ps1.close();}con.close();}catch(Exception e) {throw e;}		}

		
	}

	private static String findingGrade(double mark) {
		// TODO Auto-generated method stub
		if(mark>=0 && mark<50) {return "Fail";}
		else if(mark>=50 && mark<60){return "E";}
		else if(mark>=60 && mark<70){return "D";}
		else if(mark>=70 && mark<80){return "C";}
		else if(mark>=80 && mark<90){return "B";}
		else{return "A";}

	}

}
