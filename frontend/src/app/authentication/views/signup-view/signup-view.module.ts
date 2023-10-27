import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SignupViewComponent } from './signup-view/signup-view.component';
import { SignupFormComponent } from './components/signup-form/signup-form.component';



@NgModule({
  declarations: [
    SignupViewComponent,
    SignupFormComponent
  ],
  imports: [
    CommonModule
  ]
})
export class SignupViewModule { }
