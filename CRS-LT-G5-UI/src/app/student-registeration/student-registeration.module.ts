/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {StudentRegisterationRoutingModule} from "./student-registeration-routing.module";
import {StudentRegisterationComponent} from "./student-registeration.component";
import {HttpClientModule} from "@angular/common/http";


@NgModule({
  declarations: [
    StudentRegisterationComponent,
  ],
  imports: [
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule,
    StudentRegisterationRoutingModule,
  ],
})
export class StudentRegisterationModule { }
