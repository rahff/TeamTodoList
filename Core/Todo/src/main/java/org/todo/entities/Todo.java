package org.todo.entities;

import org.todo.port.dto.TodoDto;

import java.time.LocalDate;

public class Todo {

  private final String id;
  private final String description;
  private boolean done;
  private final LocalDate deadLine;
  private final LocalDate createdAt;

  public Todo(String id, String description, boolean done, LocalDate deadLine, LocalDate createdAt) {
    this.id = id;
    this.description = description;
    this.done = done;
    this.deadLine = deadLine;
    this.createdAt = createdAt;
  }

  public static Todo fromDto(TodoDto dto) {
    return new Todo(dto.id(), dto.description(), dto.done(), dto.deadLine(), dto.createdAt());
  }

  public String description() {
    return description;
  }

  public boolean done() {
    return done;
  }

  public LocalDate deadLine() {
    return deadLine;
  }

  public LocalDate createdAt() {
    return createdAt;
  }

  public String getId(){
    return id;
  }

  public void markAsDone() {
    this.done = true;
  }
}
