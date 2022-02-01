/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {AdminRoutingModule} from "./admin-routing.module";
import {AdminComponent} from "./admin.component";
import {AddOrRemoveCourseComponent} from "./add-or-remove-course/add-or-remove-course.component";
import {ApproveOrRejectStudentsComponent} from "./approve-or-reject-students/approve-or-reject-students.component";
import {MapProfessorToCourseOrRemoveProfessorComponent} from "./map-professor-to-course-or-remove-professor/map-professor-to-course-or-remove-professor.component";
import {ReportCardGenerationComponent} from "./report-card-generation/report-card-generation.component";


@NgModule({
  declarations: [
    AdminComponent,
    AddOrRemoveCourseComponent,
    ApproveOrRejectStudentsComponent,
    MapProfessorToCourseOrRemoveProfessorComponent,
    ReportCardGenerationComponent,
  ],
  imports: [
    CommonModule,
    AdminRoutingModule,
  ],
})
export class AdminModule { }
