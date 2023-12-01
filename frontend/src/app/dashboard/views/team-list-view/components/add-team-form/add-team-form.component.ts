import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import {Teammate} from "../../../../../../core/model/team/Teammate";

@Component({
  selector: 'app-add-team-form',
  templateUrl: './add-team-form.component.html',
  styleUrls: ['./add-team-form.component.css']
})
export class AddTeamFormComponent implements OnInit {

  @Output() public teamFormSubmited = new EventEmitter<{name: string, teammates: string[]}>();
  @Input() public availableTeammates!: Teammate[];
  public teamForm!: FormGroup;

  public constructor(private formBuilder: FormBuilder) { }

  public ngOnInit(): void {
    this.teamForm = this.formBuilder.group({
      name: ["", [Validators.required, Validators.minLength(3), Validators.maxLength(255)]],
      teammates: []
    })

  }

  public onSubmit(): void {
    if(this.teamForm.valid){
      this.teamFormSubmited.emit(this.teamForm.getRawValue());
    }
  }
}
