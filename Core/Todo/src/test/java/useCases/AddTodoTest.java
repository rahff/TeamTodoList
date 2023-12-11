package useCases;

import org.todo.application.commands.AddTodoCommand;
import org.todo.port.dto.AddTodoRequest;
import org.todo.port.dto.TodoDto;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.InMemoryTodoListRepository;
import org.todo.port.spi.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.DateProviders;
import utils.RequestProviders;
import utils.TodoListProviders;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



public class AddTodoTest {

  private AddTodoCommand addTodoCommand;
  private InMemoryTodoListRepository todoListRepository;

  private AddTodoDataFixture dataFixture;
  @BeforeEach
  void setup(){
    dataFixture = new AddTodoDataFixture();
    todoListRepository = new InMemoryTodoListRepository(dataFixture.initialTodoListRepository());
    addTodoCommand = new AddTodoCommand(todoListRepository);
  }

  @Test
  void UserAddTodoInExistingEmptyTodoList(){
    var request = RequestProviders.addTodoRequest();
    addTodoCommand.execute(request);
    assertTrue(todoListRepository.items().contains(dataFixture.todoListAfterAddingTodoFromRequest(request)));
  }
}

class AddTodoDataFixture {
  public List<TodoListDto> initialTodoListRepository(){
    return List.of(new TodoListDto("todoListId", "userId", "My todo list", List.of(), DateProviders.now()));
  }

  public TodoListDto todoListAfterAddingTodoFromRequest(AddTodoRequest request) {
    return new TodoListDto(request.todoListId(), "userId", "My todo list", List.of(request.todoDto()), DateProviders.now());
  }
}
