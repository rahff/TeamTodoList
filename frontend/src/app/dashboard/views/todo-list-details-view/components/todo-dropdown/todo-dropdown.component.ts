import { Component, EventEmitter, Input, Output } from '@angular/core';


@Component({
  selector: 'app-todo-dropdown',
  templateUrl: './todo-dropdown.component.html',
  styleUrls: ['./todo-dropdown.component.css']
})
export class TodoDropdownComponent {

  @Input() public todoId!: string;
  @Output() public doneTodoButtonClicked = new EventEmitter<string>();
  @Output() public deleteTodoButtonClicked = new EventEmitter<string>();

  public doneTodoAction(id: string): void {
    this.doneTodoButtonClicked.emit(id)
  }

  public deleteTodoAction(id: string): void {
    this.deleteTodoButtonClicked.emit(id)
  }
}
