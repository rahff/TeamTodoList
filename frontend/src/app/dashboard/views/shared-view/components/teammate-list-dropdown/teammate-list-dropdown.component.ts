import { Component, EventEmitter, Input, Output } from '@angular/core';
import { DropdownMenuComponent } from 'src/app/dashboard/views/shared-view/components/dropdown-menu/dropdown-menu.component';
import {Teammate} from "../../../../../../core/model/team/Teammate";



@Component({
  selector: 'app-teammate-list-dropdown',
  templateUrl: './teammate-list-dropdown.component.html',
  styleUrls: ['./teammate-list-dropdown.component.css']
})
export class TeammateListDropdownComponent extends DropdownMenuComponent {

  @Input() public teammate!: Teammate;
  @Output() public fireTeammateButtonClicked = new EventEmitter<Teammate>()

  public constructor() { super() }

  public firedAction(): void {
    this.fireTeammateButtonClicked.emit(this.teammate);
  }
}
