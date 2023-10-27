import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { TeammateListStoreApi } from 'src/core/store/teammate-list/TeammateListStoreApi';

@Component({
  selector: 'app-add-team-form',
  templateUrl: './add-team-form.component.html',
  styleUrls: ['./add-team-form.component.css']
})
export class AddTeamFormComponent implements OnInit {

  @Output() public teamFormSubmited = new EventEmitter<{name: string, teammates: string[]}>();
  public teamForm!: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  public ngOnInit(): void {
    this.teamForm = this.formBuilder.group({
      name: ["", [Validators.required, Validators.minLength(3), Validators.maxLength(255)]],
      teammates: [[]]
    })
  }

  public onSubmit(): void {
    if(this.teamForm.valid){
      this.teamFormSubmited.emit(this.teamForm.getRawValue());
    }
  }
}
