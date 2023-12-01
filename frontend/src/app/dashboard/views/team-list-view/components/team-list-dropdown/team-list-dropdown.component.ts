import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DropdownMenuComponent } from 'src/app/dashboard/views/shared-view/components/dropdown-menu/dropdown-menu.component';


@Component({
  selector: 'app-team-list-dropdown',
  templateUrl: './team-list-dropdown.component.html',
  styleUrls: ['./team-list-dropdown.component.css']
})
export class TeamListDropdownComponent extends DropdownMenuComponent {

  @Input() public teamId!: string;
  @Output() public deleteActionClicked = new EventEmitter<string>();
  
  public constructor() { super() }

  public deleteActionClick(id: string): void {
    this.deleteActionClicked.emit(id);
  }

}
