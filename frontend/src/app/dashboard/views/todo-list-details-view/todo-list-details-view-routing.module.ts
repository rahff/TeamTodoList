import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TodoListDetailsViewComponent } from './todo-list-details-view.component';



const routes: Routes = [
  {
    path: "/:id", component: TodoListDetailsViewComponent
  }
]

@NgModule({
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class TodoListDetailsViewRoutingModule {
  public static viewComponents = [TodoListDetailsViewComponent]
}
