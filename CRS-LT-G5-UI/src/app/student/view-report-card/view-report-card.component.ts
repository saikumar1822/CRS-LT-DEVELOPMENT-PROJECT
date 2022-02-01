/* eslint-disable new-cap */
/* eslint-disable require-jsdoc */
import {HttpClient, HttpErrorResponse} from "@angular/common/http";
import {Component, OnInit} from "@angular/core";
import {StudentGrade} from "src/Models/StudentGrade";

@Component({
  selector: "app-view-report-card",
  templateUrl: "./view-report-card.component.html",
  styleUrls: ["./view-report-card.component.css"],
})
export class ViewReportCardComponent implements OnInit {
  listOfStudentGrades: StudentGrade[]=[];
  constructor(private http:HttpClient) { }

  ngOnInit(): void {
    this.http.get<any>("http://localhost:1111/reportCard?studentId="+sessionStorage.getItem("userId")).subscribe(
        (res:StudentGrade[])=>{
          this.listOfStudentGrades=res;
        },
        (err:HttpErrorResponse)=>{
        },
    );
  }
}
