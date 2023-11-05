package org.query.team.spi;

import org.query.team.dto.TodoListDto;
import org.query.todo.model.Todo;

import java.util.List;
import java.util.Optional;

public interface TodoListDataAccess {
    List<TodoListDto> getTodoListsByTeamId(String TeamId);
    int getTodoListsCountByTeamId(String TeamId);

}
