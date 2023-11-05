package org.example.persistance.entities.todo;

import jakarta.persistence.*;
import org.example.persistance.entities.todo.Todo;
import org.springframework.format.annotation.DateTimeFormat;
import org.todo.port.dto.TodoListDto;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "todo_list")
public class TodoList {
  @Id
  @Column(name = "id", nullable = false)
  private String id;

  @Column(unique = true)
  private String name;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Todo> todos;

  @Column(unique = true)
  private String ref;

  @Column(columnDefinition = "DATE")
  private LocalDate createdAt;


  public TodoList(String id, String name, String ref, List<Todo> todos) {
    this.id = id;
    this.name = name;
    this.todos = todos;
    this.ref = ref;
  }
  public TodoList(){}

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setTodos(List<Todo> todos) {
    this.todos = todos;
  }

  public void setRef(String ref) {
    this.ref = ref;
  }

  public LocalDate getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(LocalDate createdAt) {
    this.createdAt = createdAt;
  }

  public String getName() {
    return name;
  }

  public List<Todo> getTodos() {
    return todos;
  }

  public String getRef() {
    return ref;
  }

  public TodoListDto toDto() {
    var list = todos.stream().map(Todo::toDto).collect(Collectors.toList());
    return new TodoListDto(id, ref, name, list, createdAt);
  }
}
