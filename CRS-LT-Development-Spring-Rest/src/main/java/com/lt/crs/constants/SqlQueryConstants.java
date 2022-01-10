package com.lt.crs.constants;

/**
 * 
 * @author sai kumar,Rohan,Diwakar,Abdul,Siva,Nikil
 * All SQl Query Constants are present here 
 *
 */

public interface SqlQueryConstants {
	
	
	/*
	 * For Student Registeration DAO
	 */
final String STUDENT_REGISTERATION="insert into student (student_name,student_password,student_department) values(?,?,?)";

	/*
	 * For Professor DAO
	 */
	/*
	 * View Enrolled Students
	 */
	final String PROFESSOR_EXISTS="select distinct count(*) from professor where professor_id=?";
	final String LIST_ENROLLED_STUDENTS_FOR_PROFESSOR="select s.student_id,s.student_name,c.course_name,p.professor_name,c.course_id  from course_professor_student cps inner join course c on c.course_id=cps.course_id inner join professor p on p.professor_id=cps.professor_id inner join student s on cps.student_id=s.student_id where cps.professor_id=? group by 1,2,3,4,5 order by 5,1";
	
	
	/*
	 * Add Grades to the Students
	 */
	final String RECORD_OF_COURSE_PROFESSOR_STUDENT_EXISTS="select count(*) from course_professor_student cps where cps.course_id=? and cps.professor_id=? and cps.student_id=?";
	final String STUDENT_GRADE_FOR_COURSE_EXISTS="select count(*) from student_grade where course_id=? and professor_id=? and student_id=?";
	final String INSERT_GRADE_FOR_STUDENT="insert into student_grade values(?,?,?,?,?)";
	final String UPDATE_GRADE_FOR_STUDENT="update student_grade set mark=?,grade=? where course_id=? and professor_id=? and student_id=?";
	final String LIST_OF_STUDENTS_TO_ADD_GRADES="select * from course_professor_student cps where not exists (select * from student_grade sg where sg.student_id=cps.student_id and sg.professor_id=cps.professor_id and sg.course_id=cps.course_id) and cps.professor_id=? order by 3";
	final String LIST_OF_STUDENTS_TO_UPDATE_GRADES="select * from student_grade sg where sg.professor_id=? order by 3";
/*
 * Student DAO
 */
	/*
	 * View Enrolled Courses of the student
	 */
final String STUDENT_EXISTS="select distinct count(*) from student where student_id=?";
final String STUDENT_REGISTERED_FOR_COURSE_EXISTS="select count(*)  from course_professor_student cps where cps.student_id=?";
final String LIST_OF_COURSE_DETAILS_OF_STUDENT="select s.student_id,s.student_name,c.course_name,p.professor_name,c.course_id,p.professor_id  from course_professor_student cps inner join course c on c.course_id=cps.course_id inner join professor p on p.professor_id=cps.professor_id inner join student s on cps.student_id=s.student_id where cps.student_id=? group by 1,2,3,4,5,6 order by 5,1";

/*
 * View Report card to the Particular student
 */
final String CHECK_STUDENT_GRADE_DATA_EXISTS="select count(*) from student_grade sg where sg.student_id=?";
final String LIST_STUDENT_GRADE_DETAILS="select c.course_id,c.course_name,s.student_name,sg.mark,sg.grade,s.student_id,p.professor_id,p.professor_name from student_grade sg inner join student s on sg.student_id=s.student_id inner join course c on sg.course_id=c.course_id inner join professor p on sg.professor_id=p.professor_id where sg.student_id=? group by 1,2,3,4,5,6,7,8 order by 1";


/*
 * Drop course by student
 */
final String VERIFY_COURSE_OF_STUDENT_EXISTS="select count(*) from course_professor_student where course_id=? and student_id=?";
final String DONT_DROP_CHECK_STUDENT_GRADE_FOR_COURSE_EXISTS="select count(*) from student_grade where course_id=? and student_id=?";
final String DROP_COURSE_BY_THE_STUDENT="delete from course_professor_student where course_id=? and student_id=?";
final String DROP_COURSE_BY_STUDENT_PAYMENT_CHECK="select count(*) from payment where course_id=? and student_id=?";

/*
 * PAYMENT
 */
final String LIST_OF_COURSES_WHICH_ARE_UNPAID="select c.course_id,c.course_cost,c.course_name from course_professor_student cps inner join course c on c.course_id=cps.course_id where not exists(select * from payment p where p.course_id=cps.course_id and  p.student_id=?) and cps.student_id=?";

/*
 * Enroll course by the student
 */
final String TEACH_COURSE_BY_PROFESSOR_EXISTS="select count(*) from course_professor where course_id=?";
final String NUMBER_OF_COURSE_ENROLLED_BY_PARTICULAR_STUDENT="select count(*) from course_professor_student where student_id=?";
final String FETCH_PROFESSOR_LIST_FOR_PARTICULAR_COURSE="select professor_id from course_professor where course_id=? group by 1 order by 1 asc";
final String VALIDATING_NO_OF_STUDENTS_ENROLLED_TO_COURSE="select count(*) from course_professor_student where course_id=? and professor_id=?";
final String ENROLL_STUDENT_FOR_COURSE="insert into course_professor_student values(?,?,?)";

/*
 * Login DAO
 */

/*
 * USER LOGIN
 */
final String USER_ROLE_STUDENT="select count(*) from user_role where user_id=? and role_id=3";
final String STUDENT_LOGIN_QUERY="select count(*) from student where student_id=? and student_password=?";
final String PROFESSOR_ROLE_STUDENT="select count(*) from user_role where user_id=? and role_id=2";
final String PROFESSOR_LOGIN_QUERY="select count(*) from professor where professor_id=? and professor_password=?";
final String USER_ROLE_ADMIN="select count(*) from user_role where user_id=? and role_id=1";
final String ADMIN_LOGIN_QUERY="select count(*) from admin where admin_id=? and admin_password=?";

/*
 * PASSWORD UPDATION
 */
/**
 * update admin password based on admin id
 */
final String ADMIN_PASSWORD_UPDATE="update admin set admin_password=? where admin_id=?";

/**
 *update professor password based on professor id
 */
final String PROFESSOR_PASSWORD_UPDATE="update professor set professor_password=? where professor_id=?";

/**
 *update student password based on  student id
 */
final String STUDENT_PASSWORD_UPDATE="update student set student_password=? where student_id=?";




/*
 * Admin DAO
 */
final String LIST_ALL_PROFESSORS ="select * from professor order by 1";
final String LIST_ALL_COURSES = "select * from course order by 1";
final String LIST_ALL_STUDENTS_FOR_APPROVAL=" select distinct s.student_id from student s where not exists(select * from user_role ur where role_id=? and s.student_id=ur.user_id)";

/*
 * Approval Of Student
 */

final String CHECK_USER_ROLE_EXISTS_OR_NOT="select count(*) from user_role where user_id=? and role_id=?";
final String ADMIN_APPROVE_STUDENT="insert into user_role values(?,?)";
/*
 * Add and Approve professor
 */
final String PROFESSOR_REGISTERATION = "insert into professor (professor_name,professor_password,professor_department) values(?,?,?)";
final String ADMIN_APPROVE_PROFESSOR="insert into user_role values(?,?)";


/*
 * Remove Professor
 */
final String VERIFY_COURSE_OF_PROFESSOR_EXISTS="select count(*) from course_professor_student where professor_id=?";
final String DELETE_PROFESSOR="delete from professor where professor_id=?";
final String DELETE_ROLE_OF_PROFESSOR="delete from user_role where user_id=? and role_id=?";

/*
 * Remove Student
 */

final String VERIFY_COURSE_REGISTERED_BY_STUDNT_EXISTS = "select count(*) from course_professor_student where student_id=?";
final String DELETE_STUDENT ="delete from student where student_id=?";
final String DELETE_ROLE_OF_STUDENT ="delete from user_role where user_id=? and role_id=?";

/*
 * GENERATE REPORT CARD
 */
final String LIST_OF_ALL_STUDENT_GRADE_DETAILS="select c.course_id,c.course_name,s.student_name,sg.mark,sg.grade,s.student_id,p.professor_id,p.professor_name from student_grade sg inner join student s on sg.student_id=s.student_id inner join course c on sg.course_id=c.course_id inner join professor p on sg.professor_id=p.professor_id group by 1,2,3,4,5,6,7,8 order by 6,1";



/*
 * ADD COURSE
 */

/**
 * fetch course detail based on course name
 */
String CHECKING_COURSE_EXISTS="select count(*) from course where lower(course_name)=? ";

/**
 *adding course to course table 
 */
String ADD_COURSE="insert into course(course_name,course_cost) values(?,?)";

/**
 *updating course detail in course table 
 */
String UPDATE_COURSE="update course set course_cost=? where course_name=?";


/*
 * Drop Course List Based on number of enrolled students count less than 3
 */

final String DROP_COURSES_CONDITION_BASED_ON_COUNT_OF_STUDENTS_LESS_THAN_THREE="select c.course_id,c.course_name,c.course_cost from course c left outer join course_professor_student cps on c.course_id=cps.course_id group by 1 having count(*)<3";

/*
 * Map Course To The Professor
 */
final String COURSE_EXISTS="select distinct count(*) from course where course_id=?";
final String COURSE_PROFESSOR_EXISTS="select count(*) from course_professor where course_id=? and professor_id=?";
final String COURSE_PROFESSOR_MAP="insert into course_professor values(?,?)";

/*
 * Drop Course
 */
//final String DROP_COURSES_CONDITION="select * from course c left outer join course_professor_student cps on c.course_id=cps.course_id where c.course_id=?  group by 1 having count(*)<3";


}
