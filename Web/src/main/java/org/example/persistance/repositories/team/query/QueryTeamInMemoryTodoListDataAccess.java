package org.example.persistance.repositories.team.query;

import org.query.team.dto.TodoListDto;
import org.query.team.model.TodoList;
import org.query.team.spi.TodoListDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Objects;

@Repository
@Profile("test")
public class QueryTeamInMemoryTodoListDataAccess implements TodoListDataAccess {

    public List<TodoListDto> getTodoListsByTeamId(String TeamId) {
        return List.of(
                new TodoListDto("todoListId1", "AtodoList", "2023-10-14"),
                new TodoListDto("todoListId2", "OthertodoList", "2023-10-17")
        );
    }

    public int getTodoListsCountByTeamId(String teamId) {
        return Objects.equals(teamId, "newTeamId") ? 0 : 2;
    }
}
