package org.example.persistance.repositories;

import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.TodoListRepository;
import org.example.persistance.entities.TodoList;
import org.example.persistance.mappers.TodoListMapper;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class JpaTodoListRepositoryAdapter implements TodoListRepository {

  private final JpaTodoListRepository todoListRepository;

  public JpaTodoListRepositoryAdapter(JpaTodoListRepository todoListRepository) {
    this.todoListRepository = todoListRepository;
  }
  @Override
  public void saveTodoList(TodoListDto todoList) {
    var entity = TodoListMapper.fromDto(todoList);
    todoListRepository.save(entity);
  }

  @Override
  public Optional<TodoListDto> getTodoListByName(String userId, String todoListName) {
    return todoListRepository.findByUserIdAndName(userId, todoListName)
      .map(TodoList::toDto);
  }

  @Override
  public Optional<TodoListDto> getTodoListById(String todoListId) {
    return todoListRepository.findById(todoListId).map(TodoList::toDto);
  }

  @Override
  public int getTodoListCount(String userId) {
    return todoListRepository.countByUserId(userId);
  }

  @Override
  public void delete(String id) {
    todoListRepository.deleteById(id);
  }
}
