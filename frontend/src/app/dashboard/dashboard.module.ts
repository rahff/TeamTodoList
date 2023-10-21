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
import { TeamListComponent } from './components/team-list/team-list.component';
import { TeammateListComponent } from './components/teammate-list/teammate-list.component';
import { TodoListListComponent } from './components/todo-list-list/todo-list-list.component';
import { TodoListDropdownComponent } from './components/todo-list-dropdown/todo-list-dropdown.component';
import { TeamListDropdownComponent } from './components/team-list-dropdown/team-list-dropdown.component';
import { TeammateListDropdownComponent } from './components/teammate-list-dropdown/teammate-list-dropdown.component';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { TeamDetailsComponent } from './views/team-details/team-details.component';



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
    TeamListComponent,
    TeammateListComponent,
    TodoListListComponent,
    TodoListDropdownComponent,
    TeamListDropdownComponent,
    TeammateListDropdownComponent,
    TodoListComponent,
    TeamDetailsComponent
  ],
  imports: [
    DashboardRoutingModule, CommonModule
  ]
})
export class DashboardModule { }
