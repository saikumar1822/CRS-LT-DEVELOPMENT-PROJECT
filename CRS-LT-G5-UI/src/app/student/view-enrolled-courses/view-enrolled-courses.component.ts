/* eslint-disable max-len */
/* eslint-disable new-cap */
/* eslint-disable require-jsdoc */
import {Component, OnInit} from "@angular/core";
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {CourseProfessorDto} from "src/Models/CourseProfessorDto";
@Component({
  selector: "app-view-enrolled-courses",
  templateUrl: "./view-enrolled-courses.component.html",
  styleUrls: ["./view-enrolled-courses.component.css"],
})
export class ViewEnrolledCoursesComponent implements OnInit {
  listOfEnrolledCourses: CourseProfessorDto[]=[];
  courseDroppedMessage:string="NA";
  courseDropped:boolean=false;

  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>("http://localhost:1111/EnrolledCourses?studentId="+sessionStorage.getItem("userId")).subscribe(
        (res:CourseProfessorDto[])=>{
          this.listOfEnrolledCourses=res;
        },
        (err:HttpErrorResponse)=>{
        },
    );
  }


  dropCourse(i:number) {
    const id1:any=sessionStorage.getItem("userId");
    const id2=+id1;


    this.http.delete<any>("http://localhost:1111/dropCourse/"+id2+"/"+this.listOfEnrolledCourses[i].courseId).subscribe(

        (res:string)=>{
          this.listOfEnrolledCourses.splice(i, 1);
        },
        (err:HttpErrorResponse)=>{
          if (err.error.message) {
            this.courseDropped=true;
            this.courseDroppedMessage=err.error.message+" ID "+this.listOfEnrolledCourses[i].courseId;
          } else {
            this.courseDropped=true;
            this.courseDroppedMessage="Dropped the course ID "+this.listOfEnrolledCourses[i].courseId+" Successfully";
            this.listOfEnrolledCourses.splice(i, 1);
          }
        },
    );
  }
}
