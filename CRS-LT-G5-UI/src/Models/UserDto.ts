/* eslint-disable require-jsdoc */
/* eslint-disable linebreak-style */
/* eslint-disable space-before-blocks */

export class UserDto {
  public userId:number;
  public userName:string;
  public role: string;

  constructor(userId:number, userName:string, role:string){
    this.userId=userId;
    this.userName=userName;
    this.role=role;
  }
}
