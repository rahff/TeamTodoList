package useCases;

import org.todo.application.commands.AddTodoCommand;
import org.todo.port.spi.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.RequestProviders;
import utils.TodoListProviders;

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
