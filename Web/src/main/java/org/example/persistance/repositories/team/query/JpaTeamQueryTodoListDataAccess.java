package org.example.persistance.repositories.team.query;

import org.example.persistance.repositories.team.query.mappers.TodoListMapper;
import org.example.persistance.repositories.todo.springData.JpaTodoListRepository;
import org.query.team.dto.TodoListDto;
import org.query.team.spi.TodoListDataAccess;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;


import java.util.List;

@Repository
@Profile("prod")
public class JpaTeamQueryTodoListDataAccess implements TodoListDataAccess {

  private final JpaTodoListRepository repository;

  public JpaTeamQueryTodoListDataAccess(JpaTodoListRepository repository) {
    this.repository = repository;
  }

  public List<TodoListDto> getTodoListsByTeamId(String teamId) {
    var list = repository.findByRef(teamId);
    return list.stream()
      .map(TodoListMapper::toQueryTeamDto).toList();
  }

  public int getTodoListsCountByTeamId(String teamId) {
    return repository.countByRef(teamId);
  }
}
