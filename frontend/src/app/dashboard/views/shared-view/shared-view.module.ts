import { NgModule } from '@angular/core';
import { TodoListListComponent } from './components/todo-list-list/todo-list-list.component';
import { TeammateListDropdownComponent } from './components/teammate-list-dropdown/teammate-list-dropdown.component';
import { TeammateListComponent } from './components/teammate-list/teammate-list.component';
import { TodoListDropdownComponent } from './components/todo-list-dropdown/todo-list-dropdown.component';
import { InMemoryTodoCommandHandler } from 'src/app/dashboard/services/inMemory/InMemoryTodoCommandHandler';
import { FireTeammate } from 'src/core/application/team/command/FireTeammate';
import { TeamCommandHandler } from 'src/core/application/team/spi/TeamCommandHandler';
import { InMemoryTeamCommandHandler } from '../../services/inMemory/InMemoryTeamCommandHandler';
import { RemoveTeammateFromTeam } from 'src/core/application/team/command/RemoveTeammateFromTeam';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import { DeleteTodoList } from 'src/core/application/todo/command/DeleteTodoList';
import { TodoCommandHandler } from 'src/core/application/todo/spi/TodoCommandHandler';


const components = [
  TodoListListComponent,
  TodoListDropdownComponent,
  TeammateListComponent,
  TeammateListDropdownComponent
]

@NgModule({
  imports: [CommonModule, RouterModule.forChild([])],
  declarations: [
    ...components
  ],
  exports: [
    ...components,
    CommonModule
  ],
  providers: [
    {
      provide: DeleteTodoList, useFactory: (ch: TodoCommandHandler) => new DeleteTodoList(ch), 
      deps:[InMemoryTodoCommandHandler]
    },
    {
      provide: FireTeammate, useFactory: (c: TeamCommandHandler) => new FireTeammate(c),
      deps: [InMemoryTeamCommandHandler]
    },
    {
      provide: RemoveTeammateFromTeam, useFactory: (c: TeamCommandHandler) => new RemoveTeammateFromTeam(c),
      deps: [InMemoryTeamCommandHandler]
    },
  ]
})
export class SharedViewModule { }
