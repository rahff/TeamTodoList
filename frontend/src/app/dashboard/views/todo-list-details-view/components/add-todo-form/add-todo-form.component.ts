import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-add-todo-form',
  templateUrl: './add-todo-form.component.html',
  styleUrls: ['./add-todo-form.component.css']
})
export class AddTodoFormComponent implements OnInit {

  @Output() public todoFormSubmited = new EventEmitter<{description: string, deadline: string}>();
  public todoForm!: FormGroup;

  constructor(private formBuilder: FormBuilder) { }

  ngOnInit(): void {
    this.todoForm = this.formBuilder.group({
      description: ["", [Validators.required, Validators.minLength(3), Validators.maxLength(255)]],
      deadline: ["", [Validators.required]]
    })
  }

  public onSubmit(): void {
    if(this.todoForm.valid){
      this.todoFormSubmited.emit(this.todoForm.getRawValue());
    }
  }

}
