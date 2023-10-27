import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';


const routes: Routes = [
  {
    path: "login", loadChildren: () => import("./views/login-view/login-view.module").then(m => m.LoginViewModule)
  },
  {
    path: "signup", loadChildren: () => import("./views/signup-view/signup-view.module").then(m => m.SignupViewModule)
  }
]


@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class AuthenticationRoutingModule { }
