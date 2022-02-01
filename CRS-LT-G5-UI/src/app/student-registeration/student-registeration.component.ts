/* eslint-disable max-len */
/* eslint-disable space-before-blocks */
/* eslint-disable new-cap */
/* eslint-disable require-jsdoc */
import {Component, OnInit} from "@angular/core";
import {FormBuilder, Validators} from "@angular/forms";
import {HttpClient, HttpErrorResponse, HttpHeaders} from "@angular/common/http";
import {StudentDto} from "src/Models/StudentDto";
import {Student} from "src/Models/Student";
import { Router } from "@angular/router";
@Component({
  selector: "app-student-registeration",
  templateUrl: "./student-registeration.component.html",
  styleUrls: ["./student-registeration.component.css"],
})
export class StudentRegisterationComponent implements OnInit {
  showPassword:boolean=false;
  studentRegisterationForm = this.fb.group({
    studentName: ["", [Validators.required]],
    password: ["", [Validators.required]],
    studentDepartment: ["", [Validators.required]]});

  studentDto!: StudentDto;
  student!: Student;
  registerationUnSuccess: boolean=false;
  registerationUnSuccessMessage: string="";
  registerationSuccess: boolean=false;
  registerationSuccessMessage: string="";
  constructor(private fb: FormBuilder, private http:HttpClient,private router:Router) {

  }


  ngOnInit(): void {
  }
  onRegister(){
    this.studentDto=new StudentDto(this.studentRegisterationForm.value["studentName"], this.studentRegisterationForm.value["password"], this.studentRegisterationForm.value["studentDepartment"]);


    const headers={"Content-Type": "application/json"};
    const body=JSON.stringify(this.studentDto);

    this.http.post<any>("http://localhost:4444/register", body, {headers}).subscribe(

        (res:Student)=>{
          this.registerationSuccess=true;
          this.registerationSuccessMessage="Successfully Registered your user ID is "+res.studentID+ " and waiting for admin approval";
          this.studentRegisterationForm.reset();
        },
        (err:HttpErrorResponse)=>{
          this.registerationUnSuccess=true;
          this.registerationUnSuccessMessage=err.error.message;
          this.studentRegisterationForm.reset();
        },
    );
  }

  password_show_hide(password:HTMLInputElement) {
    this.showPassword=!this.showPassword;
    password.type=this.showPassword?"text":"password";
  }
  redirectToLogin(){
    this.router.navigateByUrl("user-login");
  }
}
