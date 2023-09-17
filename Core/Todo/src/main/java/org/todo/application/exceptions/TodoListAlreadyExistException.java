package org.todo.application.exceptions;

public class TodoListAlreadyExistException extends RuntimeException {

  public TodoListAlreadyExistException() {
    super("Cannot create two todo list with same name");
  }
}
