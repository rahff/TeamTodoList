package org.example.persistance.repositories.todo.query;

import org.queryTeam.dto.TodoListDto;
import org.queryTeam.spi.TodoListDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("test")
public class InMemoryTodoListDataAccess implements TodoListDataAccess {

    public List<TodoListDto> getTodoListsByTeamId(String TeamId) {
        return List.of(
                new TodoListDto("todoListId1", "AtodoList", "2023-10-14"),
                new TodoListDto("todoListId2", "OthertodoList", "2023-10-17")
        );
    }

    public int getTodoListsCountByTeamId(String TeamId) {
        return 2;
    }
}
