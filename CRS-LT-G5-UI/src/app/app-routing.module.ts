/* eslint-disable max-len */
/* eslint-disable new-cap */
/* eslint-disable require-jsdoc */
import {NgModule} from "@angular/core";
import {RouterModule, Routes} from "@angular/router";
import {MainComponent} from "./main/main.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";


const routes: Routes = [
  // eslint-disable-next-line key-spacing
  {path:"", component:MainComponent},
  {path: "main", component: MainComponent},
  {path: "student", loadChildren: () => import("./student/student.module").then((m) => m.StudentModule)},
  {path: "professor", loadChildren: () => import("./professor/professor.module").then((m) => m.ProfessorModule)},
  {path: "admin", loadChildren: () => import("./admin/admin.module").then((m) => m.AdminModule)},
  {path: "student-registeration", loadChildren: () => import("./student-registeration/student-registeration.module").then((m) => m.StudentRegisterationModule)},
  {path: "user-password-update", loadChildren: () => import("./user-password-update/user-password-update.module").then((m) => m.UserPasswordUpdateModule)},
  {path: "user-login", loadChildren: () => import("./user-login/user-login.module").then((m) => m.UserLoginModule)},
  {path: "**", component: PageNotFoundComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule { }
