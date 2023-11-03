import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SignupViewComponent } from './signup-view.component';


const routes: Routes = [
  {
    path: "", component: SignupViewComponent
  }
]


@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class SignupViewRoutingModule {
  public static viewComponent = [SignupViewComponent]
}
