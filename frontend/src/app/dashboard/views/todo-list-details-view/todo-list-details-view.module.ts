import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TodoListDetailsViewRoutingModule } from './todo-list-details-view-routing.module';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { TodoDropdownComponent } from './components/todo-dropdown/todo-dropdown.component';
import { AddTodoFormComponent } from './components/add-todo-form/add-todo-form.component';
import { SharedViewModule } from '../shared-view/shared-view.module';
import { TodoListDetailsStoreApi } from 'src/core/store/todoList-details/TodoListDetailsStoreApi';
import { store } from 'src/core/store/Store';
import { IDProvider } from 'src/core/application/shared/interfaces/IDProvider';
import { AddTodo } from 'src/core/application/todo/command/AddTodo';
import { DeleteTodo } from 'src/core/application/todo/command/DeleteTodo';
import { DoneTodo } from 'src/core/application/todo/command/DoneTodo';
import { TodoListById } from 'src/core/application/todo/query/TodoListById';
import { TodoCommandHandler } from 'src/core/application/todo/spi/TodoCommandHandler';
import { TodoQueryHandler } from 'src/core/application/todo/spi/TodoQueryHandler';
import {
  AddTodoDependencyProvider, DeleteTodoDependencyProvider,
  DoneTodoDependencyProvider,
  TodoListByIdDependencyProvider
} from "./dependencyProvider";
import { environment } from "../../../../environments/environment";




@NgModule({
  declarations: [
    ...TodoListDetailsViewRoutingModule.viewComponents,
    TodoListComponent,
    TodoDropdownComponent,
    AddTodoFormComponent,
  ],
  imports: [
    CommonModule,
    TodoListDetailsViewRoutingModule,
    SharedViewModule
  ],
  providers: [
    {
      provide: TodoListDetailsStoreApi, useFactory: () => new TodoListDetailsStoreApi(store)
    },
    {
      provide: TodoListById, useFactory: (qh: TodoQueryHandler) => new TodoListById(qh),
      deps: TodoListByIdDependencyProvider(environment.dataSource)
    },
    {
      provide: AddTodo, useFactory: (ch: TodoCommandHandler, idp: IDProvider) => new AddTodo(ch, idp),
      deps: AddTodoDependencyProvider(environment.dataSource)
    },
    {
      provide: DoneTodo, useFactory: (ch: TodoCommandHandler) => new DoneTodo(ch),
      deps: DoneTodoDependencyProvider(environment.dataSource)
    },
    {
      provide: DeleteTodo, useFactory: (ch: TodoCommandHandler) => new DeleteTodo(ch),
      deps: DeleteTodoDependencyProvider(environment.dataSource)
    },
  ]
})
export class TodoListDetailsViewModule { }
