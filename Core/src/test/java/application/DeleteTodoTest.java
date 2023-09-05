package application;

import org.core.application.useCase.DeleteTodoCommand;
import org.core.port.dto.DeleteTodoRequest;
import org.core.port.spi.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.TodoListProviders;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DeleteTodoTest {

  private DeleteTodoCommand deleteTodoCommand;
  private TodoListRepository todoListRepository;

  @BeforeEach
  void setup(){
    todoListRepository = Mockito.mock(TodoListRepository.class);
    deleteTodoCommand = new DeleteTodoCommand(todoListRepository);
  }
  @Test
  void UserDeleteTodo(){
    var request = new DeleteTodoRequest("todoListId", 1);
    when(todoListRepository.getTodoListById("todoListId")).thenReturn(Optional.of(TodoListProviders.todoListDtoWithSeveralTodo()));
    deleteTodoCommand.execute(request);
    verify(todoListRepository).saveTodoList(TodoListProviders.todoListDtoWithSeveralTodoAfterDeleteSecondTodo());
  }
}
