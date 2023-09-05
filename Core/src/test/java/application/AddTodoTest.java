package application;

import org.core.application.useCase.AddTodoCommand;
import org.core.port.dto.AddTodoRequest;
import org.core.port.dto.TodoDto;
import org.core.port.dto.TodoListDto;
import org.core.port.spi.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.DateProviders;
import utils.RequestProviders;
import utils.TodoListProviders;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;



public class AddTodoTest {

  private AddTodoCommand addTodoCommand;
  private TodoListRepository todoListRepository;

  @BeforeEach
  void setup(){
    todoListRepository = Mockito.mock(TodoListRepository.class);
    addTodoCommand = new AddTodoCommand(todoListRepository);
  }

  @Test
  void UserAddTodoInExistingEmptyTodoList(){
    var request = RequestProviders.addTodoRequest();
    when(todoListRepository.getTodoListById("todoListId")).thenReturn(Optional.of(TodoListProviders.emptyTodoListDto()));
    addTodoCommand.execute(request);
    verify(todoListRepository).saveTodoList(eq(TodoListProviders.todoListDtoAfterAddingTodo(request.todoDto())));
  }
}
