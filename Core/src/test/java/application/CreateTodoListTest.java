package application;

import org.core.application.exception.TodoListAlreadyExistException;
import org.core.application.exception.TodoListCreationThresholdLimitReachedException;
import org.core.application.useCase.CreateTodoListCommand;
import org.core.port.api.CreateTodoList;


import org.core.port.dto.CreateTodoListRequest;
import org.core.port.dto.TodoListDto;
import org.core.port.dto.UserContext;
import org.core.port.spi.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.RequestProviders;
import utils.TodoListProviders;
import utils.UserCtxProvider;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;



public class CreateTodoListTest {

  private CreateTodoList createTodoList;
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
