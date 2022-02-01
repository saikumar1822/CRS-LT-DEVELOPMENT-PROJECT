/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {StudentRegisterationComponent} from "./student-registeration.component";

const routes: Routes = [{path: "", component: StudentRegisterationComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class StudentRegisterationRoutingModule { }
