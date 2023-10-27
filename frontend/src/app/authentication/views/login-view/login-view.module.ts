import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { LoginViewComponent } from './login-view/login-view.component';
import { LoginFormComponent } from './login-view/components/login-form/login-form.component';



@NgModule({
  declarations: [
    LoginViewComponent,
    LoginFormComponent
  ],
  imports: [
    CommonModule
  ]
})
export class LoginViewModule { }
