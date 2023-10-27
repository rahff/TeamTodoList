import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DasboardComponent } from './root/views/dasboard/dasboard.component';



const routes: Routes = [
  {
    path: "", component: DasboardComponent, children: [
      {
        path: "", redirectTo: "teams"
      },
      {
        path: "teams", loadChildren: () => import("./views/team-list-view/team-list-view.module").then(m => m.TeamListViewModule)
      },
      {
        path: "team-details", loadChildren: () => import("./views/team-details-view/team-details-view.module").then(m => m.TeamDetailsViewModule)
      },
      {
        path: "todo-lists", loadChildren: () => import("./views/todo-lists-view/todo-lists-view.module").then(m => m.TodoListsViewModule)
      },
      {
        path: "todo-list-details", loadChildren: () => import("./views/todo-list-details-view/todo-list-details-view.module").then(m => m.TodoListDetailsViewModule)
      },
      {
        path: "teammates", loadChildren: () => import("./views/teammate-list-view/teammate-list-view.module").then(m => m.TeammateListViewModule)
      },
      {
        path: "account", loadChildren: () => import("./views/account-view/account-view.module").then(m => m.AccountViewModule)
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
