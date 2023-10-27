import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { TodoListsViewComponent } from './todo-lists-view.component';

const routes: Routes = [
  {
    path: "", component:  TodoListsViewComponent,
  }
]

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forChild(routes)
  ],
  exports: [RouterModule]
})
export class TodoListsViewRoutingModule {
  public static viewComponents = [TodoListsViewComponent]
}
