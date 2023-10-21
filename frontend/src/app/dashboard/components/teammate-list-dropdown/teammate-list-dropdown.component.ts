import { Component, OnInit } from '@angular/core';
import { DropdownMenuComponent } from '../dropdown-menu/dropdown-menu.component';

@Component({
  selector: 'app-teammate-list-dropdown',
  templateUrl: './teammate-list-dropdown.component.html',
  styleUrls: ['./teammate-list-dropdown.component.css']
})
export class TeammateListDropdownComponent extends DropdownMenuComponent implements OnInit {

  constructor() { super() }

  ngOnInit(): void {
  }

}
