/* eslint-disable require-jsdoc */
/* eslint-disable linebreak-style */
/* eslint-disable space-before-blocks */
export class StudentDto {
  public studentName:string;
  public password:string;
  public studentDepartment:string;

  constructor(studentName:string, password:string, studentDepartment:string){
    this.studentName=studentName;
    this.password=password;
    this.studentDepartment=studentDepartment;
  }
}
