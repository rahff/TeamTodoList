import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BootstrapComponent } from './components/bootstrap.component';


const routes: Routes = [
  {
    path: "", component: BootstrapComponent
  },
  {
    path: "dashboard", loadChildren: () => import("../dashboard/dashboard.module").then(m => m.DashboardModule)
  }
];


@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class BootstrapRoutingModule { }
