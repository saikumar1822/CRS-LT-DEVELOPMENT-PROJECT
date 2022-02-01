/* eslint-disable max-len */
/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {UserPasswordUpdateRoutingModule} from "./user-password-update-routing.module";
import {UserPasswordUpdateComponent} from "./user-password-update.component";
import { HttpClientModule } from "@angular/common/http";


@NgModule({
  declarations: [
    UserPasswordUpdateComponent,
  ],
  imports: [
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule,
    UserPasswordUpdateRoutingModule,
  ],
})
export class UserPasswordUpdateModule { }
