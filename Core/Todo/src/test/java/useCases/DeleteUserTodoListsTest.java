package useCases;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.todo.application.commands.DeleteUserTodoLists;
import org.todo.port.dto.DeleteUserTodoListsRequest;
import org.todo.port.dto.TodoDto;
import org.todo.port.dto.TodoListDto;
import org.todo.port.spi.InMemoryTodoListRepository;
import org.todo.port.spi.TodoListRepository;
import utils.DateProviders;
import utils.TodoListProviders;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.mockito.Mockito.*;

public class DeleteUserTodoListsTest {

    private InMemoryTodoListRepository todoListRepository;
    private DeleteUserTodoListsDataFixture dataFixture;
    @BeforeEach
    void setup(){
        dataFixture = new DeleteUserTodoListsDataFixture();
        todoListRepository = new InMemoryTodoListRepository(dataFixture.initialTodoListRepository());
    }
    @Test
    void DeletingAllTeammateTodoList(){
        var command = new DeleteUserTodoLists(todoListRepository);
        var request = new DeleteUserTodoListsRequest("userId");
        command.execute(request);
        assertArrayEquals(
                todoListRepository.items().toArray(new TodoListDto[0]),
                dataFixture.todoListsAfterDeletingUserTodoList().toArray(new TodoListDto[0])
        );
    }
}


class DeleteUserTodoListsDataFixture {
    public List<TodoListDto> initialTodoListRepository(){
        return List.of(new TodoListDto(
                "todoListId",
                "userId",
                "My todo list",
                List.of(),
                DateProviders.now()),
                new TodoListDto(
                        "todoListId2",
                        "userId",
                        "My second todo list",
                        List.of(),
                        DateProviders.now()),
                new TodoListDto(
                        "todoListId3",
                        "userId2",
                        "a other todo list",
                        List.of(),
                        DateProviders.now())

                );
    }

    public List<TodoListDto> todoListsAfterDeletingUserTodoList(){
        return List.of(
                new TodoListDto(
                        "todoListId3",
                        "userId2",
                        "a other todo list",
                        List.of(),
                        DateProviders.now())

        );
    }

}