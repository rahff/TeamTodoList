import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ChangePassword} from "../../../../../../core/application/account/command/ChangePassword";
import {first} from "rxjs";
import {Result} from "../../../../../../core/application/shared/dto/Result";
import {Message} from "../../../../../../core/application/shared/dto/Message";


@Component({
  selector: 'app-change-password-form',
  templateUrl: './change-password-form.component.html',
  styleUrls: ['./change-password-form.component.css']
})
export class ChangePasswordFormComponent implements OnInit {

  @Output() public passwordChangedEvent = new EventEmitter<Message>();
  public changePasswordForm!: FormGroup;
  constructor(private changePassword: ChangePassword,private formBuilder: FormBuilder) { }

  public ngOnInit(): void {
    this.changePasswordForm = this.formBuilder.group({
      oldPassword: ["", Validators.required],
      newPassword: ["", Validators.required],
      confirmPassword: ["", Validators.required]
    })
  }

  public onSubmit(): void {
    if(this.changePasswordForm.valid){
      this.changePassword.execute(this.changePasswordForm.getRawValue())
          .pipe(first()).subscribe({
        next: this.onChangePasswordResult.bind(this)
      })
    }
  }

  private onChangePasswordResult(result: Result<Message>): void {
    if (result.isOk()) this.passwordChangedEvent.emit(result.getValue());
  }
}
