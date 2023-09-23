package useCases;


import org.shared.api.Command;
import org.todo.application.commands.DeleteTodoLIstCommand;
import org.todo.port.dto.DeleteTodoListRequest;
import org.todo.port.spi.TodoListRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import utils.TodoListProviders;

import java.util.Optional;


import static org.mockito.Mockito.*;

public class DeleteTodoLIstTest {

  private Command<DeleteTodoListRequest> command;
  private TodoListRepository todoListRepository;

  @BeforeEach
  void setup(){
    todoListRepository = Mockito.mock(TodoListRepository.class);
    command = new DeleteTodoLIstCommand(todoListRepository);
  }
  @Test
  void User_can_delete_any_of_his_empty_todo_list(){
    var request = new DeleteTodoListRequest("todoListId");
    when(todoListRepository.getTodoListById("todoListId")).thenReturn(Optional.of(TodoListProviders.emptyTodoListDto()));
    command.execute(request);
    verify(todoListRepository).delete("todoListId");
  }

  @Test
  void We_cannot_delete_non_empty_todo_list_without_emptying_first(){
    var request = new DeleteTodoListRequest("todoListId");
    when(todoListRepository.getTodoListById("todoListId")).thenReturn(Optional.of(TodoListProviders.todoListDtoWithSeveralTodo()));
    command.execute(request);
    verify(todoListRepository).saveTodoList(TodoListProviders.emptyTodoListDto());
    verify(todoListRepository).delete("todoListId");
  }
}
