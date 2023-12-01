import { Component, EventEmitter, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs';
import { Result } from 'src/core/application/shared/dto/Result';
import { JoinTeammate } from 'src/core/application/team/command/JoinTeammate';
import { Teammate } from 'src/core/model/team/Teammate';


@Component({
  selector: 'app-add-teammate-form',
  templateUrl: './add-teammate-form.component.html',
  styleUrls: ['./add-teammate-form.component.css']
})
export class AddTeammateFormComponent implements OnInit {

  @Output() public teammateJoinedEvent = new EventEmitter<Teammate>();

  private joinTeammateForm!: FormGroup;

  public constructor(private formBuilder: FormBuilder,
              private joinTeammate: JoinTeammate) { }

  public ngOnInit(): void {
    this.joinTeammateForm = this.formBuilder.group({
      name: ["", [Validators.required, Validators.minLength(2), Validators.maxLength(45)]],
      email: ["", [Validators.required, Validators.email]]
    })
  }

  public onSubmit(): void {
    if(this.joinTeammateForm.valid) {
      this.joinTeammate.execute(this.joinTeammateForm.getRawValue())
      .pipe(first()).subscribe({
        next: this.onJoinResult.bind(this)
      })
    }
  }

  private onJoinResult(result: Result<Teammate>): void {
    if(result.isOk()) this.teammateJoinedEvent.emit(result.getValue());
  }

}
