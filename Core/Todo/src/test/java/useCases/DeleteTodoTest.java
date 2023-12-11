package useCases;

import org.todo.application.commands.DeleteTodoCommand;
import org.todo.port.dto.DeleteTodoRequest;
import org.todo.port.dto.TodoDto;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.InMemoryTodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.DateProviders;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertTrue;


public class DeleteTodoTest {

  private DeleteTodoCommand deleteTodoCommand;
  private InMemoryTodoListRepository todoListRepository;
  private DeleteTodoDataFixture dataFixture;

  @BeforeEach
  void setup(){
    dataFixture = new DeleteTodoDataFixture();
    todoListRepository = new InMemoryTodoListRepository(dataFixture.initialTodoListRepository());
    deleteTodoCommand = new DeleteTodoCommand(todoListRepository);
  }
  @Test
  void UserDeleteTodo(){
    var request = new DeleteTodoRequest("todoListId", "todoID2");
    deleteTodoCommand.execute(request);
    assertTrue(todoListRepository.items().contains(dataFixture.todoListAfterDeletingTodo()));
  }
}

class DeleteTodoDataFixture {
  public List<TodoListDto> initialTodoListRepository(){
    return List.of(new TodoListDto(
            "todoListId",
            "userId",
            "My todo list",
            List.of(new TodoDto("todoID2",
                    "do",
                    true,
                    DateProviders.getDateInFuture(),
                    DateProviders.now()),
                    new TodoDto("todoID1",
                            "do something",
                            false,
                            DateProviders.getDateInFuture(),
                            DateProviders.now())),
            DateProviders.now()));
  }


  public TodoListDto todoListAfterDeletingTodo(){
    return new TodoListDto(
            "todoListId",
            "userId",
            "My todo list",
            List.of(new TodoDto(
                    "todoID1",
            "do something",
            false,
                  DateProviders.getDateInFuture(),
                  DateProviders.now())),
            DateProviders.now());
  }
}