package org.queryTeam.spi.fakes;

import org.queryTeam.dto.TodoListDto;
import org.queryTeam.model.TodoList;
import org.queryTeam.spi.TodoListDataAccess;

import java.util.ArrayList;
import java.util.List;

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
