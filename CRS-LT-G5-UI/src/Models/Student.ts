/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable linebreak-style */
/* eslint-disable space-before-blocks */
export class Student {
  public studentID:number;
  public studentName:string;
  public studentDepartment:string;
  public isApproved:boolean;


  constructor(studentName:string, studentID:number, studentDepartment:string, isApproved:boolean){
    this.studentName=studentName;
    this.studentID=studentID;
    this.studentDepartment=studentDepartment;
    this.isApproved=isApproved;
  }
}

