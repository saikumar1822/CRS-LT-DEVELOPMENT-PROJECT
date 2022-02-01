/* eslint-disable new-cap */
/* eslint-disable require-jsdoc */
import {Component, OnInit} from "@angular/core";
import {Router} from "@angular/router";

@Component({
  selector: "app-professor",
  templateUrl: "./professor.component.html",
  styleUrls: ["./professor.component.css"],
})
export class ProfessorComponent implements OnInit {
  professorName: any="";
  constructor(private router:Router) { }

  ngOnInit(): void {
    this.professorName=sessionStorage.getItem("userName");
  }
  logoutUser() {
    sessionStorage.clear();
    this.router.navigateByUrl("");
  }
}
