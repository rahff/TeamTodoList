package org.core.application.exception;

public class TodoListCreationThresholdLimitReachedException extends RuntimeException {
  public TodoListCreationThresholdLimitReachedException() {
    super("As a Free user, you cannot create over 5 todo list");
  }
}
