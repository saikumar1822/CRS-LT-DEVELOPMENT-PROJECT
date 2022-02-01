/* eslint-disable require-jsdoc */
/* eslint-disable new-cap */
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {UserPasswordUpdateComponent} from "./user-password-update.component";

const routes: Routes = [{path: "", component: UserPasswordUpdateComponent}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UserPasswordUpdateRoutingModule { }
