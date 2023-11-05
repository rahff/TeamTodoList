package org.example.persistance.repositories.team.query;

import org.query.team.dto.TodoListDto;
import org.query.team.spi.TodoListDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Profile("test")
public class QueryTeamInMemoryTodoListDataAccess implements TodoListDataAccess {

    public List<TodoListDto> getTodoListsByTeamId(String TeamId) {
        return null;
    }


    public int getTodoListsCountByTeamId(String TeamId) {
        return 0;
    }
}
