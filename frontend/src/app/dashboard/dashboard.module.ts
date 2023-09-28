import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SidebarComponent } from './components/sidebar/sidebar.component';
import { HeaderComponent } from './components/header/header.component';
import { DasboardComponent } from './components/dasboard/dasboard.component';
import { DashboardRoutingModule } from './dashboard-routing.module';
import { TeamComponent } from './views/team/team.component';
import { TodoListsComponent } from './views/todo-list/todo-list.component';
import { TeammatesComponent } from './views/teammates/teammates.component';
import { AccountComponent } from './views/account/account.component';
import { FooterComponent } from './components/footer/footer.component';
import { ListTableComponent } from './components/list-table/list-table.component';
import { DropdownMenuComponent } from './components/dropdown-menu/dropdown-menu.component';



@NgModule({
  declarations: [
    SidebarComponent,
    HeaderComponent,
    DasboardComponent,
    TeamComponent,
    TodoListsComponent,
    TeammatesComponent,
    AccountComponent,
    FooterComponent,
    ListTableComponent,
    DropdownMenuComponent
  ],
  imports: [
    DashboardRoutingModule, CommonModule
  ]
})
export class DashboardModule { }
