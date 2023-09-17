package org.todo.port.api;

public interface Command<T> {

  void execute(T request);
}
