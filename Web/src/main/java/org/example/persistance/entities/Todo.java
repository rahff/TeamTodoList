package org.example.persistance.entities;

import jakarta.persistence.*;
import org.todo.port.dto.TodoDto;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "todo")
public class Todo {
  @Id
  @Column(name = "id", nullable = false)
  private String id;
  private String description;
  private boolean done;
  private LocalDate deadline;
  private LocalDate createdAt;

  public Todo(String id, String description, boolean done, LocalDate deadline, LocalDate createdAt) {
    this.description = description;
    this.done = done;
    this.deadline = deadline;
    this.createdAt = createdAt;
    this.id = id;
  }

  public Todo() {}

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public boolean isDone() {
    return done;
  }

  public void setDone(boolean done) {
    this.done = done;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public TodoDto toDto(){
    return new TodoDto(id, description, done, deadline, createdAt);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Todo todo)) return false;
    return done == todo.done && Objects.equals(id, todo.id) && Objects.equals(description, todo.description) && Objects.equals(deadline, todo.deadline) && Objects.equals(createdAt, todo.createdAt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, description, done, deadline, createdAt);
  }
}
