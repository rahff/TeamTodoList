import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DropdownMenuComponent } from '../dropdown-menu/dropdown-menu.component';


@Component({
  selector: 'app-todo-list-dropdown',
  templateUrl: './todo-list-dropdown.component.html',
  styleUrls: ['./todo-list-dropdown.component.css']
})
export class TodoListDropdownComponent extends DropdownMenuComponent {

  @Input() public todoListId!: string
  @Output() public deleteTodoListButtonClicked = new EventEmitter<string>();
  public constructor() { super() }


  public deleteTodoList(id: string): void {
    this.deleteTodoListButtonClicked.emit(id);
  }
}
