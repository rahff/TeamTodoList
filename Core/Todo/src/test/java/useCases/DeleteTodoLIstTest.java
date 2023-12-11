package useCases;



import org.todo.application.commands.DeleteTodoLIstCommand;

import org.todo.port.dto.DeleteTodoListRequest;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.InMemoryTodoListRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.DateProviders;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class DeleteTodoLIstTest {

  private DeleteTodoLIstCommand command;
  private InMemoryTodoListRepository todoListRepository;

    @BeforeEach
  void setup(){
    DeleteTodoLIstDataFixture dataFixture = new DeleteTodoLIstDataFixture();
    todoListRepository = new InMemoryTodoListRepository(dataFixture.initialTodoListRepository());
    command = new DeleteTodoLIstCommand(todoListRepository);
  }
  @Test
  void User_can_delete_any_of_his_empty_todo_list(){
    var request = new DeleteTodoListRequest("todoListId");
    command.execute(request);
    assertTrue(todoListRepository.items().isEmpty());
  }
}

class DeleteTodoLIstDataFixture {
  public List<TodoListDto> initialTodoListRepository(){
    return List.of(new TodoListDto("todoListId", "userId", "My todo list", List.of(), DateProviders.now()));
  }
}