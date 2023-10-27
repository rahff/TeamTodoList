import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './root/components/sidebar/sidebar.component';
import { HeaderComponent } from './root/components/header/header.component';
import { DasboardComponent } from './root/views/dasboard/dasboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { FooterComponent } from './root/components/footer/footer.component';
import { SharedViewModule } from './views/shared-view/shared-view.module';
import { ReactiveFormsModule } from '@angular/forms';



@NgModule({
  declarations: [
    SidebarComponent,
    HeaderComponent,
    DasboardComponent,
    FooterComponent,

  ],
  imports: [
    DashboardRoutingModule, 
    CommonModule,
    SharedViewModule,
    ReactiveFormsModule
  ]
})
export class DashboardModule { }
