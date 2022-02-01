/* eslint-disable max-len */
/* eslint-disable new-cap */
/* eslint-disable require-jsdoc */
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {StudentRoutingModule} from "./student-routing.module";
import {StudentComponent} from "./student.component";
import {EnrollCourseComponent} from "./enroll-course/enroll-course.component";
import {ViewEnrolledCoursesComponent} from "./view-enrolled-courses/view-enrolled-courses.component";
import {ViewReportCardComponent} from "./view-report-card/view-report-card.component";
import {StudentPaymentComponent} from "./student-payment/student-payment.component";
import { HttpClientModule } from "@angular/common/http";
import { ReactiveFormsModule } from "@angular/forms";


@NgModule({
  declarations: [
    StudentComponent,
    EnrollCourseComponent,
    ViewEnrolledCoursesComponent,
    ViewReportCardComponent,
    StudentPaymentComponent,
  ],
  imports: [
    ReactiveFormsModule,
    HttpClientModule,
    CommonModule,
    StudentRoutingModule,
  ],
})
export class StudentModule { }
