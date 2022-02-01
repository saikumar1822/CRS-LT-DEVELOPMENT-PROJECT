
/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {Component, OnInit} from "@angular/core";
import {FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {PasswordDto} from "src/Models/PasswordDto";
@Component({
  selector: "app-user-password-update",
  templateUrl: "./user-password-update.component.html",
  styleUrls: ["./user-password-update.component.css"],
})
export class UserPasswordUpdateComponent implements OnInit {
  showOldPassword:boolean=false;
  showNewPassword:boolean=false;
  passwordUpdateForm = this.fb.group({
    userId: ["", [Validators.required]],
    oldPassword: ["", [Validators.required]],
    newPassword: ["", [Validators.required]]});
  passwordDto!: PasswordDto;

  passwordUpdateSuccess: boolean=false;
  passwordUpdateSuccessMessage: string="";
  passwordUpdateUnSuccess: boolean=false;
  passwordUpdateUnSuccessMessage: string="";

  constructor(private fb: FormBuilder, private http:HttpClient, private router:Router) {

  }

  ngOnInit(): void {
  }
  onUpdate() {
    this.passwordDto=new PasswordDto(this.passwordUpdateForm.value["userId"], this.passwordUpdateForm.value["oldPassword"], this.passwordUpdateForm.value["newPassword"]);


    const headers={"Content-Type": "application/json"};
    const body=JSON.stringify(this.passwordDto);

    this.http.post<any>("http://localhost:4444/updatePassword", body, {headers}).subscribe(

        (res:any)=>{
          this.passwordUpdateSuccess=true;
          this.passwordUpdateSuccessMessage=res;
          this.passwordUpdateForm.reset();
        },
        (err:HttpErrorResponse)=>{
         
          if (err.error.message) {
            this.passwordUpdateUnSuccess=true;
            this.passwordUpdateUnSuccessMessage=err.error.message;
          } else {
            this.passwordUpdateSuccess=true;
            this.passwordUpdateSuccessMessage="Password Updated Successfully";
          }

          this.passwordUpdateForm.reset();
        },
    );
  }

  old_password_show_hide(oldPassword:HTMLInputElement) {
    this.showOldPassword=!this.showOldPassword;
    oldPassword.type=this.showOldPassword?"text":"password";
  }

  new_password_show_hide(newPassword:HTMLInputElement) {
    this.showNewPassword=!this.showNewPassword;
    newPassword.type=this.showNewPassword?"text":"password";
  }
  redirectToLogin() {
    this.router.navigateByUrl("user-login");
  }
}
