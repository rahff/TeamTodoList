package org.example.persistance.entities.todo;

import jakarta.persistence.*;
import org.example.persistance.entities.todo.Todo;
import org.springframework.format.annotation.DateTimeFormat;
import org.todo.port.dto.TodoListDto;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "todo_list")
public class TodoList {
  @Id
  @Column(name = "id", nullable = false)
  private String id;

  private String name;
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Todo> todos;

  private String ref;

  @Temporal(TemporalType.DATE)
  private Date createdAt;


  public TodoList(String id, String name, String ref, List<Todo> todos) {
    this.id = id;
    this.name = name;
    this.todos = todos;
    this.ref = ref;
    this.createdAt = new Date(System.currentTimeMillis());
  }
  public TodoList(){
    this.createdAt = new Date(System.currentTimeMillis());
  }

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

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
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
    return new TodoListDto(id, ref, name, list, createdAt.toLocalDate());
  }
}
