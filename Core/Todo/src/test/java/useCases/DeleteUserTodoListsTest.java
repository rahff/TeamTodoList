package useCases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.todo.application.commands.DeleteUserTodoLists;
import org.todo.port.dto.DeleteUserTodoListsRequest;
import org.todo.port.spi.TodoListRepository;
import utils.TodoListProviders;



import static org.mockito.Mockito.*;

public class DeleteUserTodoListsTest {

    private TodoListRepository todoListRepository;
    @BeforeEach
    void setup(){
        todoListRepository = Mockito.mock(TodoListRepository.class);
    }
    @Test
    void DeletingAllTeammateTodoList(){
        when(todoListRepository.getTodoListsByUserRef("userId")).thenReturn(TodoListProviders.getList());
        var command = new DeleteUserTodoLists(todoListRepository);
        var request = new DeleteUserTodoListsRequest("userId");
        command.execute(request);
        verify(todoListRepository, times(2)).delete(anyString());
    }
}
