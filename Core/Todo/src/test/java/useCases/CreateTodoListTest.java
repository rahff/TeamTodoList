package useCases;

import org.mockito.Mockito;
import org.todo.application.exceptions.TodoListAlreadyExistException;
import org.todo.application.exceptions.TodoListCreationThresholdLimitReachedException;
import org.todo.application.commands.CreateTodoListCommand;
import org.todo.port.dto.AddTodoRequest;
import org.todo.port.dto.CreateTodoListRequest;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.InMemoryTodoListRepository;
import org.todo.port.spi.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.DateProviders;
import utils.RequestProviders;
import utils.TodoListProviders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class CreateTodoListTest {

  private CreateTodoListCommand createTodoList;
  private InMemoryTodoListRepository todoListRepository;

  private CreateTodoDataFixture dataFixture;
  @BeforeEach
  void setup(){
    dataFixture = new CreateTodoDataFixture();
    todoListRepository = new InMemoryTodoListRepository(dataFixture.initialTodoListRepository());
    createTodoList = new CreateTodoListCommand(todoListRepository);
  }

  @Test
  void UserCreateTodoListForTheFirstTime() {
    var request = RequestProviders.createTodoListRequest("A Todo List");
    createTodoList.execute(request);
    assertTrue(todoListRepository.items().contains(dataFixture.newTodoListFromRequest(request)));
  }

  @Test
  void UserCannotCreateTwoTodoListWithTheSameName() {
    var request = RequestProviders.createTodoListRequest("My todo list");
    assertThrows(TodoListAlreadyExistException.class, ()-> createTodoList.execute(request));
    assertFalse(todoListRepository.items().contains(dataFixture.newTodoListFromRequest(request)));
  }
}

class CreateTodoDataFixture {
  public List<TodoListDto> initialTodoListRepository(){
    return List.of(new TodoListDto("todoListId", "userId", "My todo list", List.of(), DateProviders.now()));
  }

  public TodoListDto newTodoListFromRequest(CreateTodoListRequest request) {
    return new TodoListDto(request.todoListId(), request.ref(), request.todoListName(), List.of(), DateProviders.now());
  }
}
