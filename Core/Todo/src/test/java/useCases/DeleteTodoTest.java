package useCases;

import org.todo.application.useCase.DeleteTodoCommand;
import org.todo.port.dto.DeleteTodoRequest;
import org.todo.port.spi.TodoListRepository;
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
    var request = new DeleteTodoRequest("todoListId", "todoID2");
    when(todoListRepository.getTodoListById("todoListId")).thenReturn(Optional.of(TodoListProviders.todoListDtoWithSeveralTodo()));
    deleteTodoCommand.execute(request);
    verify(todoListRepository).saveTodoList(TodoListProviders.todoListDtoWithSeveralTodoAfterDeleteSecondTodo());
  }
}
