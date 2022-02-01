/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {ReactiveFormsModule} from "@angular/forms";
import {UserLoginRoutingModule} from "./user-login-routing.module";
import {UserLoginComponent} from "./user-login.component";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    UserLoginComponent,
  ],
  imports: [
   
    HttpClientModule,
    ReactiveFormsModule,
    CommonModule,
    UserLoginRoutingModule,
  ],
})
export class UserLoginModule { }
