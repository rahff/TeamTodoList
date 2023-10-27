import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BootstrapViewComponent } from './view/bootstrap-view.component';


const routes: Routes = [
  {
    path: "", component: BootstrapViewComponent
  },
  {
    path: "dashboard", loadChildren: () => import("../dashboard/dashboard.module").then(m => m.DashboardModule)
  },
  {
    path: "auth", loadChildren: () => import("../authentication/authentication.module").then(m => m.AuthenticationModule)
  },
  {
    path: "signup", loadChildren: () => import("../authentication/views/login-view/login-view.module").then(m => m.LoginViewModule)
  }
];


@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class BootstrapRoutingModule {
  public static viewComponents = [BootstrapViewComponent]
}
