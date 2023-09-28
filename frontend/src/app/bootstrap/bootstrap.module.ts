import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { BootstrapComponent } from './components/bootstrap.component';
import { BootstrapRoutingModule } from './bootstrap-routing.module';



@NgModule({
  declarations: [
    BootstrapComponent
  ],
  imports: [
    BootstrapRoutingModule
  ]
})
export class BootstrapModule { }
