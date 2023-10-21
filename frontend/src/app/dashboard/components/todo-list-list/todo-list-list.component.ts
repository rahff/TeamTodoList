import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { Subscription } from 'rxjs';
import { ConfirmActionService } from 'src/app/services/confirm-action.service';
import { Result } from 'src/core/application/shared/dto/Result';
import { DeleteTodoList } from 'src/core/application/todo/command/DeleteTodoList';
import { TodoList } from 'src/core/application/todo/dto/TodoList';



@Component({
  selector: 'app-todo-list-list',
  templateUrl: './todo-list-list.component.html',
  styleUrls: ['./todo-list-list.component.css']
})
export class TodoListListComponent implements OnDestroy {

  @Input() public todoLists!: TodoList[];
  @Output() public todoListDeletedEvent = new EventEmitter<string>();
  private subscription = new Subscription();

  constructor(private deleteTodoList: DeleteTodoList, 
              private confirmationService: ConfirmActionService) { }


  public onDeleteTodoListButtonClicked(id: string): void {
    this.subscription.add(this.confirmationService.askConfirmationFor(this.deleteTodoList, id)
    .subscribe({ next: this.onResult.bind(this) }));
  }

  private onResult(result: Result<string>): void {
    if(result.isOk()) this.todoListDeletedEvent.emit(result.getValue());
  }

  public ngOnDestroy(): void {
      this.subscription.unsubscribe();
  }

}
