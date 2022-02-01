/* eslint-disable max-len */
/* eslint-disable no-unused-vars */
/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {Component, Input, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {LoginService} from "../login.service";
import {FormBuilder, Validators} from "@angular/forms";
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {LoginDto} from "src/Models/LoginDto";
import {UserDto} from "src/Models/UserDto";

@Component({
  selector: "app-user-login",
  templateUrl: "./user-login.component.html",
  styleUrls: ["./user-login.component.css"],
})
export class UserLoginComponent implements OnInit {
  loginForm = this.fb.group({
    userId: ["", [Validators.required]],
    password: ["", [Validators.required]]});
  showPassword:boolean=false;
  loginDto!: LoginDto;
  loginUnSuccess:boolean=false;
  loginUnSuccessMessage:string="";
  constructor(private loginService:LoginService, private router:Router, private fb: FormBuilder, private http:HttpClient) {

  }

  ngOnInit(): void {
  }

  onLogin() {
    this.loginDto=new LoginDto(this.loginForm.value["userId"], this.loginForm.value["password"]);
   

    const headers={"Content-Type": "application/json"};
    const body=JSON.stringify(this.loginDto);
  
    this.http.post<any>("http://localhost:4444/login", body, {headers}).subscribe(

        (res:UserDto)=>{
         console.log(res)
          this.loginService.loginUser(res);
        },
        (err:HttpErrorResponse)=>{
          this.loginUnSuccess=true;
          this.loginUnSuccessMessage=err.error.message;
          this.loginForm.reset();
        },
    );
  }

  password_show_hide(password:HTMLInputElement) {
    this.showPassword=!this.showPassword;
    password.type=this.showPassword?"text":"password";
  }
}


