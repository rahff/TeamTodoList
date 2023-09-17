package org.example.persistance.repositories;

import org.todo.port.dto.TodoDto;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.TodoListRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TodoListInMemoryRepository implements TodoListRepository {

  private List<TodoListDto> data = new ArrayList<>();
  public TodoListInMemoryRepository(){
    var todos = new ArrayList<TodoDto>();
    todos.add(new TodoDto("1","do something", false, LocalDate.of(2023, 11, 2), LocalDate.now()));
    data.add(new TodoListDto("1", "userId", "default todo list", todos));
  }
  public void saveTodoList(TodoListDto todoList) {
    data.add(todoList);
  }

  public Optional<TodoListDto> getTodoListByName(String userId, String todoListName) {
    return data.stream()
      .filter(todoList -> todoList.userId().equals(userId))
      .filter(todoList -> todoList.todoListName().equals(todoListName)).findFirst();
  }


  public Optional<TodoListDto> getTodoListById(String todoListId) {
    return data.stream()
      .filter(todoList -> todoList.listId().equals(todoListId)).findFirst();
  }


  public int getTodoListCount(String userId) {
     return (int) data.stream()
       .filter(todoList -> todoList.userId().equals(userId)).count();
  }

  public void delete(String id) {
      data = data.stream().filter(todoList -> !todoList.listId().equals(id)).collect(Collectors.toList());
  }
}
