/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable linebreak-style */
/* eslint-disable space-before-blocks */
export class StudentCourse {
  public studentId:number;
  public courseId:number;
  public professorId:number;
  constructor(studentId:number, courseId:number, professorId:number) {
    this.courseId=courseId;
    this.professorId=professorId;
    this.studentId=studentId;
  }
}

