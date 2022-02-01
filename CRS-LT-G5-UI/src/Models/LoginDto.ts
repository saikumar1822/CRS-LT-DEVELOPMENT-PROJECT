/* eslint-disable require-jsdoc */
/* eslint-disable linebreak-style */
/* eslint-disable space-before-blocks */

export class LoginDto {
  public userId : number;
  public password : string;

  constructor(userId:number, password:string) {
    this.userId=userId;
    this.password=password;
  }
}
