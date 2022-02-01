/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable linebreak-style */
/* eslint-disable space-before-blocks */
export class StudentGrade {
  public studentId:number;
  public courseId:number;
  public professorId:number;
  public mark:number;
  public grade:string;
  constructor(studentId:number, courseId:number, professorId:number, mark:number, grade:string) {
    this.courseId=courseId;
    this.professorId=professorId;
    this.studentId=studentId;
    this.mark=mark;
    this.grade=grade;
  }
}


