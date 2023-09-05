package org.core.application.exception;

public class TodoListAlreadyExistException extends RuntimeException {

  public TodoListAlreadyExistException() {
    super("Cannot create two todo list with same name");
  }
}
