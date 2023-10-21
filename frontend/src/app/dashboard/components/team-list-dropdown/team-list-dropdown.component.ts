import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { DropdownMenuComponent } from '../dropdown-menu/dropdown-menu.component';
import { DeleteTeam } from 'src/core/application/team/command/DeleteTeam';
import { ConfirmActionService } from 'src/app/services/confirm-action.service';
import { Result } from 'src/core/application/shared/dto/Result';
import { Team } from 'src/core/application/team/dto/Team';

@Component({
  selector: 'app-team-list-dropdown',
  templateUrl: './team-list-dropdown.component.html',
  styleUrls: ['./team-list-dropdown.component.css']
})
export class TeamListDropdownComponent extends DropdownMenuComponent {

  @Input() public teamId!: string;
  @Output() public deleteActionClicked = new EventEmitter<string>();
  
  constructor() { super() }

  public deleteActionClick(id: string): void {
    this.deleteActionClicked.emit(id);
  }

}
