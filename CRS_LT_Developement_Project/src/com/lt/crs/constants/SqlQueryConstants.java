package com.lt.crs.constants;

/**
 * 
 * @author sai kumar,Rohan,Diwakar,Abdul,Siva,Nikil
 *
 */

public interface SqlQueryConstants {
	/**
	 * fetch row from student table if  student id and student password matches
	 */
String student_login_query="select * from student where student_id=? and student_password=?";

/**
	 * fetch row from user role table if user id and role id of 3 matches
	 */
	String user_role_student="select * from user_role where user_id=? and role_id=3";

	/**
	 * fetch row from user role table if user id and role id of 2 matches
	 */
	String user_role_professor="select * from user_role where user_id=? and role_id=2";
	
	/**
	 *fetch row from professor table if professor id and professor password matches 
	 */
	String professor_login_query="select * from professor where professor_id=? and professor_password=?";
	
	/**
	 *fetch row from user role table if user id and role id of 1 matches 
	 */
	String user_role_admin="select * from user_role where user_id=? and role_id=1";
	
	/**
	 * fetch row from admin if admin id and admin password matches
	 */
	String admin_login_query="select * from admin where admin_id=? and admin_password=?";
	
	/**
	 * update admin password based on admin id
	 */
	String admin_password_update="update admin set admin_password=? where admin_id=?";
	
	/**
	 *update professor password based on professor id
	 */
	String professor_password_update="update professor set professor_password=? where professor_id=?";
	
	/**
	 *update student password based on  student id
	 */
	String student_password_update="update student set student_password=? where student_id=?";

	/**
	 *insert into student table 
	 */
	String student_registeration="insert into student (student_name,student_password,student_department) values (?,?,?)";
	
	/**
	 *fetch all details of the student who are enrolled for the particular professor's course
	 */
	String dao_professor_viewstudent ="\r\n"
			+ "select s.student_id,s.student_name,c.course_name,p.professor_name,c.course_id  from course_professor_student cps "
			+ "inner join course c on c.course_id=cps.course_id inner join professor p "
			+ "on p.professor_id=cps.professor_id inner join student s on cps.student_id=s.student_id where cps.professor_id=? group by 1,2,3,4,5 order by 5,1";

	/**
	 *fetch all details of the student who are enrolled for the particular professor's course 
	 */	
	String dao_professor_viewsalltudent="\r\n"
			+ "select s.student_id,s.student_name,c.course_name,p.professor_name,c.course_id  from course_professor_student cps "
			+ "inner join course c on c.course_id=cps.course_id inner join professor p "
			+ "on p.professor_id=cps.professor_id inner join student s on cps.student_id=s.student_id where cps.professor_id=? group by 1,2,3,4,5 order by 5,1";
	
	/**
	 *this query is used for checking the grade is present or not for the particular student id 
	 */
String dao_professor_verifystudentgradepresent="select * from student_grade where course_id=? and professor_id=? and student_id=?";

/**
 *adding grade to the student 
 */
String dao_professor_addgrade="insert into student_grade values(?,?,?,?,?)";

/**
 *updating grade to the student 
 */
String dao_professor_updategrade="update student_grade set mark=?,grade=? where course_id=? and professor_id=? and student_id=?";
	
/**
 * fetch all courses which has a professor to teach
 */
String dao_student_addcourse_coursedetail="select cp.course_id,c.course_name from course_professor cp "
		+ "inner join course c on c.course_id=cp.course_id inner join professor p on p.professor_id=cp.professor_id group by 1,2 order by 1";

/**
 *fetch all the courses enrolled by a student 
 */
String dao_student_addcourse_selectcourseenrolled="select distinct cps.student_id,cps.course_id,cps.professor_id"
		+ " from course_professor_student cps where cps.student_id=?";

/**
 * fetch professor based on the course selected by the student
 */
String dao_student_addcourse_selectprofessorfrom_courseprofessortable="select * from course_professor where course_id=? group by 1,2 order by 2";

/**
 *fetch no of students enrolled  to the course
 */
String dao_student_addcourse_validating_noofstudents="select count(*) from course_professor_student where course_id=? and professor_id=?";

/**
 * fetch all course enrolled by a student
 */String dao_student_addcourse_verifystudentcoursepresent="select * from course_professor_student "
		+ "where course_id=? and student_id=?";
 
 /**
	 *enrolling course to the student 
	 */
 String dao_student_addcourse="insert into course_professor_student values(?,?,?)";
 
 /**
	 *list all the courses which has been enrolled by a student 
	 */
 String dao_student_dropcourse_listcourseenrolled="select *  from course_professor_student where student_id=? group by 1,2,3 order by 1 ";
 
 /**
	 * drop course by a student
	 */
 String dao_student_dropcourse="delete from course_professor_student where course_id=? and professor_id=? and student_id=?";
 
 /**
	 *fetch all the courses enrolled by a student 
	 */
 String dao_student_viewenrolledcourse="select s.student_id,s.student_name,c.course_name,p.professor_name,c.course_id,p.professor_id  from"
		+ " course_professor_student cps inner join course c on c.course_id=cps.course_id inner join "
		+ "professor p on p.professor_id=cps.professor_id inner join student s on cps.student_id=s.student_id where cps.student_id=? group by 1,2,3,4,5,6 order by 5,1";

 /**
	 * fetch a report card to thee student
	 */
 String dao_student_viewreportcard="select c.course_id,c.course_name,s.student_name,sg.mark,sg.grade from "
		+ "student_grade sg inner join"
		+ " student s on sg.student_id=s.student_id inner join course c on sg.course_id=c.course_id where sg.student_id=? group by 1,2,3,4,5 order by 1";

 
 /**
	 * use for checcking whether the professor is present or not
	 */
String dao_admin_addprofessor_existingprofessor="select professor_id  from professor where professor_id=?";

/**
 * list all the courses 
 */
String dao_admin_addprofessor_listallcourses="select c.course_id,c.course_name from course c group by 1,2 order by 1";

/**
 * use for checking whether the professor is already mapped to the selected course
 */
String dao_admin_addprofessor_checkforduplicatecourseenroll_byprofessor="select * from course_professor where course_id=? and professor_id=?";

/**
 *add professor with course 
 */
String dao_admin_addprofessor_addcourseforprofessor="insert into course_professor  values(?,?)";

/**
 *add new professor with the course
 */
String dao_admin_addprofessor="insert into professor (professor_name,professor_password,professor_department,course_id) values (?,?,?,?)";


/**
 *use for checking whether professor is present or not 
 */
String dao_admin_removeprofessor_checkingprof="select * from course_professor_student where professor_id=?";

/**
 *select all details of the courses enrolled by a student 
 */
String dao_admin_removestudent_checkingstud="select * from course_professor_student where student_id=?";

/**
 *delete professor  
 */
String dao_admin_removeprofessor="delete from professor where professor_id=?";

/**
 *delete student 
 */
String dao_admin_removestudent="delete from student where student_id=?";

/**
 *remove the role of a professor from the user role 
 */
String dao_admin_removingprofessorrole="delete from  user_role where user_id=? and role_id=2";

/**
 *remove role of a student from user role 
 */
String dao_admin_removingstudentrole="delete from  user_role where user_id=? and role_id=3";

/**
 *fetch student based on student id
 */
String dao_admin_selectingallstudents="select * from user_role where user_id=? and role_id=3";

/**
 *adding role to the user id  
 */
String dao_admin_approving_student="insert into user_role values(?,3)";

/**
 * fetch student detail based on id
 */
String dao_admin_existingstudent="select student_id  from student where student_id=?";


/**
 * fetch course detail based on course name
 */
String checking_course_exists="select * from course where course_name=? ";

/**
 *adding course to course table 
 */
String add_course="insert into course(course_name,course_cost) values(?,?)";

/**
 *updating course detail in course table 
 */
String update_course="update course set course_cost=? where course_name=?";

/**
 *deleting course from course table 
 */
String delete_course="delete from course where course_id=?";

/**
 * fetch  the no of students enrolled for each courses
 */
String dao_admin_coursewithtotalcount_ofstudentenroll="select course_id,professor_id,count(*) from course_professor_student group by 1,2 order by 1";

/**
 * fetch details of the courses enrolled by the students 
 */
String dao_admin_fornotification="select * from course_professor_student where course_id=? and professor_id=?";

/**
 *fetch all report cards of all the students 
 */
String dao_admin_gradeofallstudents=" select c.course_id,c.course_name,s.student_name,"
		+ "sg.mark,sg.grade,s.student_id from student_grade sg inner join student s "
		+ "on sg.student_id=s.student_id inner join course c on sg.course_id=c.course_id "
		+ "group by 1,2,3,4,5,6 order by 6 ";


/**
 *checking for the course enrollment 
 */
String student_course_check="select * from course_professor_student where student_id=? and course_id=?";

/**
 *fetching cost of the course based on course id 
 */
String get_course_cost="select course_cost from course where course_id=?";

/**
 * adding payment details
 */
String payment_details = "insert into payment (student_id,course_id,payment_status,amount) values(?,?,?,?)";

}
