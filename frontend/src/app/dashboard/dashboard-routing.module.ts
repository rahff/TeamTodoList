import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DasboardComponent } from './components/dasboard/dasboard.component';
import { TeamComponent } from './views/team/team.component';
import { AccountComponent } from './views/account/account.component';
import { TeammatesComponent } from './views/teammates/teammates.component';
import { TodoListsComponent } from './views/todo-list/todo-list.component';
import { TeamDetailsComponent } from './views/team-details/team-details.component';

const routes: Routes = [
  {
    path: "", component: DasboardComponent, children: [
      {
        path: "", redirectTo: "teams"
      },
      {
        path: "teams", component: TeamComponent
      },
      {
        path: "team-details/:id", component: TeamDetailsComponent
      },
      {
        path: "todo-lists", component: TodoListsComponent
      },
      {
        path: "teammates", component: TeammatesComponent
      },
      {
        path: "account", component: AccountComponent
      }

    ]
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class DashboardRoutingModule { }
