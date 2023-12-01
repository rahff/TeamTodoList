package org.example.persistance.repositories.todo.command;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;
import org.todo.port.dto.TodoDto;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.TodoListRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@Profile("test")
public class TodoListInMemoryRepository implements TodoListRepository {

  private final List<TodoListDto> data = new ArrayList<>();
  public TodoListInMemoryRepository(){
    this.data.addAll(List.of(TodoListProvider.getTodoList()));
  }
  public void saveTodoList(TodoListDto todoList) {
    var existingTodoList = getTodoListById(todoList.listId()).orElse(null);
    if(existingTodoList != null){
      delete(existingTodoList.listId());
      data.add(todoList);
    }else{
      data.add(todoList);
    }
  }

  public Optional<TodoListDto> getTodoListByName(String userId, String todoListName) {
    return data.stream()
      .filter(todoList -> todoList.ref().equals(userId))
      .filter(todoList -> todoList.todoListName().equals(todoListName)).findFirst();
  }


  public Optional<TodoListDto> getTodoListById(String todoListId) {
    return data.stream()
      .filter(todoList -> todoList.listId().equals(todoListId)).findFirst();
  }


  public int getTodoListCount(String userId) {
     return (int) data.stream()
       .filter(todoList -> todoList.ref().equals(userId)).count();
  }

  public void delete(String id) {
      data.removeIf(todoList -> !todoList.listId().equals(id));
  }

  public List<TodoListDto> getTodoListsByUserRef(String userId) {
    return data.stream()
            .filter(todoList -> todoList.ref().equals(userId)).toList();
  }
}


class TodoListProvider {

  public static TodoListDto getTodoList(){
    var todos = List.of(new TodoDto("1","do something", false, LocalDate.of(2023, 11, 2), LocalDate.now()));
    return new TodoListDto("1", "userId", "default todo list", todos, LocalDate.of(2023, 11, 5));
  }
}