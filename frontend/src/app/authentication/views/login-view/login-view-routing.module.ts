import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginViewComponent } from './login-view.component';


const routes: Routes = [
  {
    path: "", component: LoginViewComponent
  }
]


@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class LoginViewRoutingModule {
  public static viewComponents = [LoginViewComponent]
}
