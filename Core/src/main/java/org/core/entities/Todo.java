package org.core.entities;

import org.core.port.dto.TodoDto;

import java.time.LocalDate;

public class Todo {

  private final String description;
  private boolean done;
  private final LocalDate deadLine;
  private final LocalDate createdAt;

  public Todo(String description, boolean done, LocalDate deadLine, LocalDate createdAt) {
    this.description = description;
    this.done = done;
    this.deadLine = deadLine;
    this.createdAt = createdAt;
  }

  public static Todo fromDto(TodoDto dto) {
    return new Todo(dto.description(), dto.done(), dto.deadLine(), dto.createdAt());
  }

  public void setDone(){
    this.done = true;
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
}
