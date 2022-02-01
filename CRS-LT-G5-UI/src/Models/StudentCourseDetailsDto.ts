/* eslint-disable max-len */

/* eslint-disable require-jsdoc */
/* eslint-disable linebreak-style */
/* eslint-disable space-before-blocks */
export class StudentCourseDetailsDto {
  public courseId:number;
  public courseName:string;
  public studentId:number;
  public studentName:string;
  constructor(studentId:number, studentName:string, courseName:string, courseId:number) {
    this.courseId=courseId;
    this.courseName=courseName;
    this.studentId=studentId;
    this.studentName=studentName;
  }
}

