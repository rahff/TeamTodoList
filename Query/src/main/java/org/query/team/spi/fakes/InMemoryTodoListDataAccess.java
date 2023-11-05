package org.query.team.spi.fakes;


import org.query.team.dto.TodoListDto;
import org.query.team.spi.TodoListDataAccess;


import java.util.List;
import java.util.Optional;

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
