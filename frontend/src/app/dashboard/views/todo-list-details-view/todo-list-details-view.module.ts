import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TodoListDetailsViewRoutingModule } from './todo-list-details-view-routing.module';
import { TodoListComponent } from './components/todo-list/todo-list.component';
import { TodoListDropdownComponent } from '../shared-view/components/todo-list-dropdown/todo-list-dropdown.component';
import { TodoDropdownComponent } from './components/todo-dropdown/todo-dropdown.component';
import { AddTodoFormComponent } from './components/add-todo-form/add-todo-form.component';
import { SharedViewModule } from '../shared-view/shared-view.module';
import { TodoListDetailsStoreApi } from 'src/core/store/todoList-details/TodoListDetailsStoreApi';
import { store } from 'src/core/store/Store';
import { TodoListById } from 'src/core/application/todo/query/TodoListById';
import { TodoQueryHandler } from 'src/core/application/todo/spi/TodoQueryHandler';
import { InMemoryTodoQueryHandler } from 'src/app/dashboard/services/inMemory/InMemoryTodoQueryHandler';
import { AddTodo } from 'src/core/application/todo/command/AddTodo';
import { TodoCommandHandler } from 'src/core/application/todo/spi/TodoCommandHandler';
import { IDProvider } from 'src/core/application/shared/interfaces/IDProvider';
import { UUIDService } from '../../services/uuid.service';
import { DoneTodo } from 'src/core/application/todo/command/DoneTodo';
import { DeleteTodo } from 'src/core/application/todo/command/DeleteTodo';



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
      deps: [InMemoryTodoQueryHandler]
    },
    {
      provide: AddTodo, useFactory: (ch: TodoCommandHandler, idp: IDProvider) => new AddTodo(ch, idp),
      deps: [InMemoryTodoQueryHandler, UUIDService]
    },
    {
      provide: DoneTodo, useFactory: (ch: TodoCommandHandler) => new DoneTodo(ch),
      deps: [InMemoryTodoQueryHandler]
    },
    {
      provide: DeleteTodo, useFactory: (ch: TodoCommandHandler) => new DeleteTodo(ch),
      deps: [InMemoryTodoQueryHandler]
    },
  ]
})
export class TodoListDetailsViewModule { }
