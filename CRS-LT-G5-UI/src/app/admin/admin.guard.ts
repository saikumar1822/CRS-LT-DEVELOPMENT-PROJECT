/* eslint-disable new-cap */
/* eslint-disable require-jsdoc */
/* eslint-disable max-len */
import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree} from "@angular/router";
import {Observable} from "rxjs";
import {LoginService} from "../login.service";

@Injectable({
  providedIn: "root",
})
export class AdminGuard implements CanActivate {
  constructor(private loginService:LoginService, private router:Router) {

  }
  canActivate(
      route: ActivatedRouteSnapshot,
      state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    const loginValid=sessionStorage.getItem("isAdminLoggedIn");
    if (loginValid==="true") {
      return true;
    } else {
      this.router.navigateByUrl("user-login");
    }
    return false;
  }
}
