/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable linebreak-style */
/* eslint-disable space-before-blocks */
export class CourseProfessorDto {
  public courseId:number;
  public courseName:string;
  public professorId:number;
  public professorName:string;
  constructor(courseId:number, courseName:string, professorId:number, professorName:string) {
    this.courseId=courseId;
    this.courseName=courseName;
    this.professorId=professorId;
    this.professorName=professorName;
  }
}
