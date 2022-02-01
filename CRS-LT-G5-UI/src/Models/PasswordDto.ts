/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable linebreak-style */
/* eslint-disable space-before-blocks */
export class PasswordDto {
  public userId:number;
  public oldPassword:string;
  public newPassword:string;


  constructor(userId:number, oldPassword:string, newPassword:string){
    this.userId=userId;
    this.oldPassword=oldPassword;
    this.newPassword=newPassword;
  }
}


