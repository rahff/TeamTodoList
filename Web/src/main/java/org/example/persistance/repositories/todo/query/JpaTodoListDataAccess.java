package org.example.persistance.repositories.todo.query;

import org.queryTeam.dto.TodoListDto;
import org.queryTeam.spi.TodoListDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
@Profile("prod")
public class JpaTodoListDataAccess implements TodoListDataAccess {

    public List<TodoListDto> getTodoListsByTeamId(String TeamId) {
        return null;
    }

    public int getTodoListsCountByTeamId(String TeamId) {
        return 0;
    }
}
