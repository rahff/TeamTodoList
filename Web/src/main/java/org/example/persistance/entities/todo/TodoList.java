package org.example.persistance.entities.todo;

import jakarta.persistence.*;
import org.example.persistance.entities.todo.Todo;
import org.todo.port.dto.TodoListDto;

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
  private String userId;


  public TodoList(String id, String name, String userId, List<Todo> todos) {
    this.id = id;
    this.name = name;
    this.todos = todos;
    this.userId = userId;
  }
  public TodoList(){}

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public List<Todo> getTodos() {
    return todos;
  }

  public String getUserId() {
    return userId;
  }

  public TodoListDto toDto() {
    var list = todos.stream().map(Todo::toDto).collect(Collectors.toList());
    return new TodoListDto(id, userId, name, list);
  }
}
