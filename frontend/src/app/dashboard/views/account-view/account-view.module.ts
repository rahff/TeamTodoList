import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AccountViewRoutingModule } from './account-view-routing.module';



@NgModule({
  declarations: [
    ...AccountViewRoutingModule.viewComponents
  ],
  imports: [
    CommonModule,
    AccountViewRoutingModule
  ]
})
export class AccountViewModule { }
