/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {LoginService} from "../login.service";

@Component({
  selector: "app-student",
  templateUrl: "./student.component.html",
  styleUrls: ["./student.component.css"],
})
export class StudentComponent implements OnInit {
  studentName: any="";
  constructor(private loginService:LoginService, private router:Router) {
  // this.studentName=sessionStorage.getItem("userName");
  }

  ngOnInit(): void {
    this.studentName=sessionStorage.getItem("userName");
  }

  logoutUser() {
    sessionStorage.clear();
    this.router.navigateByUrl("");
  }
}
