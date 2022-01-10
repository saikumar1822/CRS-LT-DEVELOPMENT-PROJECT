//package com.lt.crs.repository;
//
//import java.util.List;
//
//import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.Query;
//
//import com.lt.crs.model.Student_Course;
//
//public interface StudentCourseRepository extends JpaRepository<Student_Course, Integer> {
//	/*
//	 * @Query(value = "SELECT s FROM Student_Course where s.studentId=?1") public
//	 * List<Student_Course> findByStudentId(int studentId);
//	 */
//
//	public Student_Course findByCourseIdAndStudentId(int courseId, int studentId);
//
//	public void deleteByStudentIdAndCourseId(int studentId, int courseId);
//
//	public List<Student_Course> findByStudentId(int studentId);
//
//}
