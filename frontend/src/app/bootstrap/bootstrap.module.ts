import { NgModule } from '@angular/core';
import { BootstrapRoutingModule } from './bootstrap-routing.module';



@NgModule({
  declarations: [
    ...BootstrapRoutingModule.viewComponents
  ],
  imports: [
    BootstrapRoutingModule
  ]
})
export class BootstrapModule { }
