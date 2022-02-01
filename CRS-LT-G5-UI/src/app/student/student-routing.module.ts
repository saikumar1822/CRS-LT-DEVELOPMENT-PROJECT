/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {EnrollCourseComponent} from "./enroll-course/enroll-course.component";
import {StudentPaymentComponent} from "./student-payment/student-payment.component";
import {StudentComponent} from "./student.component";
import {StudentGuard} from "./student.guard";
import {ViewEnrolledCoursesComponent} from "./view-enrolled-courses/view-enrolled-courses.component";
import {ViewReportCardComponent} from "./view-report-card/view-report-card.component";

const routes: Routes = [{path: "", component: StudentComponent, canActivate: [StudentGuard],
  children: [
    {path: "enroll-course", component: EnrollCourseComponent},
    {path: "view-enrolled-courses", component: ViewEnrolledCoursesComponent},
    {path: "view-report-card", component: ViewReportCardComponent},
    {path: "payment", component: StudentPaymentComponent},
  ],

},


];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StudentRoutingModule { }
