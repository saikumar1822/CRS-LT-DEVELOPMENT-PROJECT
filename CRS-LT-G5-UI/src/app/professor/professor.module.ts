/* eslint-disable max-len */
/* eslint-disable new-cap */
/* eslint-disable require-jsdoc */
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {ProfessorRoutingModule} from "./professor-routing.module";
import {ProfessorComponent} from "./professor.component";
import {ViewEnrolledStudentsComponent} from "./view-enrolled-students/view-enrolled-students.component";
import {AddGradesComponent} from "./add-grades/add-grades.component";
import {HttpClientModule} from "@angular/common/http";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";


@NgModule({
  declarations: [
    ProfessorComponent,
    ViewEnrolledStudentsComponent,
    AddGradesComponent,
  ],
  imports: [
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
    ProfessorRoutingModule,
  ],
})
export class ProfessorModule { }
