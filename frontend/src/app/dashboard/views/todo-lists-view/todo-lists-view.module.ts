import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TodoListsViewRoutingModule } from './todo-lists-view-routing.module';
import { SharedViewModule } from '../shared-view/shared-view.module';
import { TodoListsStoreApi } from 'src/core/store/todo-lists/TodoListStoreApi';
import { store } from 'src/core/store/Store';
import { UserContextHolder } from 'src/core/application/shared/interfaces/UserContextHolder';
import { InMemoryTodoQueryHandler } from 'src/app/dashboard/services/inMemory/InMemoryTodoQueryHandler';
import { FakeUserContextHolder } from 'src/app/dashboard/services/inMemory/FakeUserContextHolder';
import { TodoListsByUserId } from 'src/core/application/todo/query/TodoListsByUserId';
import { TodoQueryHandler } from 'src/core/application/todo/spi/TodoQueryHandler';



@NgModule({
  declarations: [
    ...TodoListsViewRoutingModule.viewComponents
  ],
  imports: [
    CommonModule,
    TodoListsViewRoutingModule,
    SharedViewModule
  ],
  providers: [
    {
      provide: TodoListsStoreApi, useFactory: () => new TodoListsStoreApi(store)
    },
    {
      provide: TodoListsByUserId, useFactory: (qh: TodoQueryHandler, cth: UserContextHolder) => new TodoListsByUserId(qh, cth),
      deps: [InMemoryTodoQueryHandler, FakeUserContextHolder]
    }
  ]
})
export class TodoListsViewModule { }
