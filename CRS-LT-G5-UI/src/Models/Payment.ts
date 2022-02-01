/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable linebreak-style */
/* eslint-disable space-before-blocks */
export class Payment {
  public paymentId:number;
  public studentId:number;
  public courseId:number;
  public status:boolean;
  public amount:number;


  constructor(paymentId:number, studentId:number, courseId:number, status:boolean, amount:number){
    this.paymentId=paymentId;
    this.studentId=studentId;
    this.courseId=courseId;
    this.status=status;
    this.amount=amount;
  }
}


