import { Component, Input, OnInit } from '@angular/core';
import { Teammate } from 'src/core/application/team/dto/Teammate';

@Component({
  selector: 'app-teammate-list',
  templateUrl: './teammate-list.component.html',
  styleUrls: ['./teammate-list.component.css']
})
export class TeammateListComponent implements OnInit {

  @Input() public teammates!: Teammate[];
  
  constructor() { }

  ngOnInit(): void {
  }

}
