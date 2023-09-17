package useCases;

import org.mockito.Mockito;
import org.todo.application.exceptions.TodoListAlreadyExistException;
import org.todo.application.exceptions.TodoListCreationThresholdLimitReachedException;
import org.todo.application.useCase.CreateTodoListCommand;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import utils.RequestProviders;
import utils.TodoListProviders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class CreateTodoListTest {

  private CreateTodoListCommand createTodoList;
  private TodoListRepository todoListRepository;

  @BeforeEach
  void setup(){
    todoListRepository = Mockito.mock(TodoListRepository.class);
    createTodoList = new CreateTodoListCommand(todoListRepository);
  }

  @Test
  void UserCreateTodoListForTheFirstTime() {
    var request = RequestProviders.createTodoListRequest();
    createTodoList.execute(request);
    verify(todoListRepository).saveTodoList(any(TodoListDto.class));
  }

  @Test
  void UserCannotCreateTwoTodoListWithTheSameName() {
    var request = RequestProviders.createTodoListRequest();
    when(todoListRepository.getTodoListByName("userId", "My todo list"))
      .thenReturn(Optional.of(TodoListProviders.emptyTodoListDto()));
    assertThrows(TodoListAlreadyExistException.class, ()-> createTodoList.execute(request));
    verify(todoListRepository, never()).saveTodoList(any(TodoListDto.class));
  }

  @Test
  void FreeUserCanCreateAMaximumOf5TodoList() {
    var request = RequestProviders.createTodoListRequest();
    when(todoListRepository.getTodoListCount("userId"))
      .thenReturn(5);
    assertThrows(TodoListCreationThresholdLimitReachedException.class, ()-> createTodoList.execute(request));
    verify(todoListRepository, never()).saveTodoList(any(TodoListDto.class));
  }

  @Test
  void PremiumUserCanCreateUnlimitedAmountOfTodoList() {
    var request = RequestProviders.createTodoListRequestWithPremiumUser();
    when(todoListRepository.getTodoListCount("userIdPremium"))
      .thenReturn(6);
    assertDoesNotThrow(()-> createTodoList.execute(request));
    verify(todoListRepository).saveTodoList(any(TodoListDto.class));
  }
}
