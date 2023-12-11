package useCases;

import org.todo.application.commands.DoneTodoCommand;

import org.todo.port.dto.DoneTodoRequest;
import org.todo.port.dto.TodoDto;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.InMemoryTodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DateProviders;

import java.util.List;
;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DoneTodoTest {

  private DoneTodoCommand doneTodoCommand;
  private InMemoryTodoListRepository todoListRepository;
  private DoneTodoDataFixture dataFixture;

  @BeforeEach
  void setup(){
    dataFixture = new DoneTodoDataFixture();
    todoListRepository = new InMemoryTodoListRepository(dataFixture.initialTodoListRepository());
    doneTodoCommand = new DoneTodoCommand(todoListRepository);
  }
  @Test
  void UserDoneTodo(){
    var request = new DoneTodoRequest("todoListId", "todoID2");
    doneTodoCommand.execute(request);
    assertTrue(todoListRepository.items().contains(dataFixture.todoListAfterDoneTodoFromRequest()));
  }
}

class DoneTodoDataFixture {
  public List<TodoListDto> initialTodoListRepository() {
    return List.of(
            new TodoListDto(
                    "todoListId",
                    "userId",
                    "My todo list",
                    List.of(new TodoDto(
                            "todoID2",
                            "try something",
                            false,
                            DateProviders.getDateInFuture(),
                            DateProviders.now())),
                    DateProviders.now()));
  }

  public TodoListDto todoListAfterDoneTodoFromRequest() {
    return new TodoListDto(
            "todoListId",
            "userId",
            "My todo list",
            List.of(new TodoDto(
              "todoID2",
              "try something",
              true,
              DateProviders.getDateInFuture(),
              DateProviders.now())),
            DateProviders.now());
  }
}