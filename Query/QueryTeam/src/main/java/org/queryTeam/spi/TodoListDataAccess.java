package org.queryTeam.spi;

import org.queryTeam.dto.TodoListDto;

import java.util.List;

public interface TodoListDataAccess {
    List<TodoListDto> getTodoListsByTeamId(String TeamId);
    int getTodoListsCountByTeamId(String TeamId);
}
