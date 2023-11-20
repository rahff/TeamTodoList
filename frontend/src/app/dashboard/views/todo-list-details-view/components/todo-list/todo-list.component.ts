import { Component, EventEmitter, Input, Output } from '@angular/core';
import { first } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { AddTodo } from 'src/core/application/todo/command/AddTodo';
import { DeleteTodo } from 'src/core/application/todo/command/DeleteTodo';
import { DoneTodo } from 'src/core/application/todo/command/DoneTodo';
import { CreateTodoFormData } from 'src/core/application/todo/dto/CreateTodoFormData';
import { Todo } from 'src/core/model/todo/Todo';

@Component({
  selector: 'app-todo-list',
  templateUrl: './todo-list.component.html',
  styleUrls: ['./todo-list.component.css']
})
export class TodoListComponent {

  @Input() todos: Todo[] = [];
  @Input() public listId!: string;
  @Output() public todoDonedEvent = new EventEmitter<string>();
  @Output() public todoDeletedEvent = new EventEmitter<string>();
  @Output() public todoAddedEvent = new EventEmitter<Todo>();

  constructor(private doneTodo: DoneTodo,
              private deleteTodo: DeleteTodo,
              private addTodo: AddTodo) { }

  public onDoneTodoButtonClicked(id: string): void {
    this.doneTodo.execute(id).pipe(first()).subscribe({
      next: this.onDoneTodoResult.bind(this)
    })
  }

  public onDeleteTodoButtonClicked(id: string): void {
    this.deleteTodo.execute({todoId: id, todoListId: this.listId})
    .pipe(first()).subscribe({
      next: this.onDeleteTodoResult.bind(this)
    })
  }

  private onDoneTodoResult(result: Result<string>): void {
    if(result.isOk()) 
      this.todoDonedEvent.emit(result.getValue());
  }

  private onDeleteTodoResult(result: Result<string>): void {
    if(result.isOk()) 
      this.todoDeletedEvent.emit(result.getValue());
  }

  public onTodoFormSubmited(data: {description: string, deadline: string}): void {
    const formData: CreateTodoFormData = {
      description: data.description, deadline: data.deadline, todoListId: this.listId
    }
    this.addTodo.execute(formData).pipe(first()).subscribe({
      next: this.onAddTodoResult.bind(this)
    })
  }

  private onAddTodoResult(result: Result<Todo>): void {
    if(result.isOk()) this.todoAddedEvent.emit(result.getValue());
  }
}
