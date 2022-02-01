/* eslint-disable new-cap */
/* eslint-disable require-jsdoc */
/* eslint-disable max-len */
import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {LoginService} from "../login.service";
import {Router} from "@angular/router";
@Injectable({
  providedIn: "root",
})
export class StudentGuard implements CanActivate {
  constructor(private loginService:LoginService,private router:Router) {

  }
  canActivate(
      route: ActivatedRouteSnapshot,
      state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const loginValid=sessionStorage.getItem("isStudentLoggedIn");
    if (loginValid==="true") {
      return true;
    } else {
      this.router.navigateByUrl("user-login");
    }
    return false;
  }
}
