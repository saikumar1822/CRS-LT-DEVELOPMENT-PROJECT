/* eslint-disable max-len */
/* eslint-disable new-cap */
/* eslint-disable require-jsdoc */
import {Component, OnInit} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {CourseProfessorDto} from "src/Models/CourseProfessorDto";
import {FormBuilder, Validators} from "@angular/forms";
import {Payment} from "src/Models/Payment";
@Component({
  selector: "app-student-payment",
  templateUrl: "./student-payment.component.html",
  styleUrls: ["./student-payment.component.css"],
})
export class StudentPaymentComponent implements OnInit {
  listOfEnrolledCourses: CourseProfessorDto[]=[];
  makePayment:boolean=false;
  cancelPayment:boolean=false;
  paymentSuccess:boolean=false;
  paymentUnSuccess:boolean=false;
  paymentSuccessMessage:string="";
  paymentUnSuccessMessage:string="";
  studentPaymentForm = this.fb.group({
    "studentId": ["", [Validators.required]],
    "courseId": ["", [Validators.required]],
    "cardNo": ["", [Validators.required, Validators.minLength(13), Validators.maxLength(16)]],
    "cvv": ["", [Validators.required, Validators.minLength(3), Validators.maxLength(3)]],
    "expiryDate": ["", [Validators.required]],
  });

  constructor(private http:HttpClient, private fb: FormBuilder) { }

  ngOnInit(): void {
    this.http.get<any>("http://localhost:1111/EnrolledCourses?studentId="+sessionStorage.getItem("userId")).subscribe(
        (res:CourseProfessorDto[])=>{
          this.listOfEnrolledCourses=res;
        },
        (err:HttpErrorResponse)=>{
        },
    );
  }

  payCourse(i:number) {
    this.makePayment=true;
    this.paymentSuccess=false;
    this.paymentUnSuccess=false;
    const id1:any=sessionStorage.getItem("userId");
    const id2=+id1;
    this.studentPaymentForm.patchValue({
      studentId: id2,
      courseId: this.listOfEnrolledCourses[i].courseId,
    });
  }
  cancelPay() {
    this.makePayment=false;
    this.paymentSuccess=false;
    this.paymentUnSuccess=false;
    this.studentPaymentForm.reset();
  }
  successPay() {
    this.makePayment=false;
    this.paymentSuccess=false;
    this.paymentUnSuccess=false;
  }
  onPay() {
    this.paymentSuccess=false;
    this.paymentUnSuccess=false;
    const headers={"Content-Type": "application/json"};
    const body=JSON.stringify(this.studentPaymentForm.value);

    this.http.post<any>("http://localhost:1111/payment", body, {headers}).subscribe(

        (res:Payment)=>{
          this.paymentSuccess=true;
          this.paymentSuccessMessage="Successfully";
          this.studentPaymentForm.reset();
        },
        (err:HttpErrorResponse)=>{
          if (err.error.message) {
            this.paymentUnSuccess=true;
            this.paymentUnSuccessMessage=err.error.message;
          } else {
            this.paymentSuccess=true;
            this.paymentSuccessMessage=JSON.stringify(err.error.text);
            this.studentPaymentForm.reset();
          }
        },
    );
  }
}
