import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DropdownMenuComponent } from 'src/app/dashboard/views/shared-view/components/dropdown-menu/dropdown-menu.component';



@Component({
  selector: 'app-teammate-list-dropdown',
  templateUrl: './teammate-list-dropdown.component.html',
  styleUrls: ['./teammate-list-dropdown.component.css']
})
export class TeammateListDropdownComponent extends DropdownMenuComponent {

  @Input() public teammateId!: string;
  @Output() public fireTeammateButtonClicked = new EventEmitter<string>()

  constructor() { super() }

  public firedAction(id: string): void {
    this.fireTeammateButtonClicked.emit(id);
  }
}
