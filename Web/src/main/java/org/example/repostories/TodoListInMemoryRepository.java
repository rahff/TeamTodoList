package org.example.repostories;

import org.core.port.dto.TodoListDto;
import org.core.port.spi.TodoListRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TodoListInMemoryRepository implements TodoListRepository {

  private List<TodoListDto> data;
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
}
