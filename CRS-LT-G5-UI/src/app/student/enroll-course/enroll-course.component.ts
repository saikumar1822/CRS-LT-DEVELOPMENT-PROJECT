/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Component, OnInit} from "@angular/core";
import {CourseProfessorDto} from "src/Models/CourseProfessorDto";
import {StudentCourse} from "src/Models/StudentCourse";
@Component({
  selector: "app-enroll-course",
  templateUrl: "./enroll-course.component.html",
  styleUrls: ["./enroll-course.component.css"],
})
export class EnrollCourseComponent implements OnInit {
  listOfCourses: CourseProfessorDto[] = [];

  studentCourse!: StudentCourse;
  addedMessage:string="NA";
  courseAddedIndex:number=-1;
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>("http://localhost:1111/courses").subscribe(
        (res:CourseProfessorDto[])=>{
          this.listOfCourses=res;
        },
        (err:HttpErrorResponse)=>{

        },
    );
  }

  addCourse(i:number) {
    const id1:any=sessionStorage.getItem("userId");
    const id2=+id1;
    this.studentCourse=new StudentCourse(id2, this.listOfCourses[i].courseId, this.listOfCourses[i].professorId);


    const headers={"Content-Type": "application/json"};
    const body=JSON.stringify(this.studentCourse);

    this.http.post<any>("http://localhost:1111/addCourse", body, {headers}).subscribe(

        (res:string)=>{
          this.addedMessage="Added Sucessfully";
        },
        (err:HttpErrorResponse)=>{
          if (err.error.message) {
            this.courseAddedIndex=i;
            this.addedMessage=err.error.message;
          } else {
            this.courseAddedIndex=i;
            this.addedMessage="Added Sucessfully";
          }
        },
    );
  }
}
