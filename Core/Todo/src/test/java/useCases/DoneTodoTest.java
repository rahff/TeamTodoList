package useCases;

import org.todo.application.useCase.DoneTodoCommand;
import org.todo.port.dto.DoneTodoRequest;
import org.todo.port.spi.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.TodoListProviders;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DoneTodoTest {

  private DoneTodoCommand doneTodoCommand;
  private TodoListRepository todoListRepository;

  @BeforeEach
  void setup(){
    todoListRepository = Mockito.mock(TodoListRepository.class);
    doneTodoCommand = new DoneTodoCommand(todoListRepository);
  }
  @Test
  void UserDoneTodo(){
    var request = new DoneTodoRequest("todoListId", "todoID2");
    when(todoListRepository.getTodoListById("todoListId"))
      .thenReturn(Optional.of(TodoListProviders.todoListDtoWithSeveralTodo()));
    doneTodoCommand.execute(request);
    verify(todoListRepository).saveTodoList(eq(TodoListProviders.todoListDtoWithSeveralTodoAndDoneSecondTodo()));
  }
}
