package adapters.query;

import org.example.Main;
import org.example.persistance.mappers.team.TeamMapper;
import org.example.persistance.repositories.team.springData.JpaTeamRepository;
import org.example.persistance.repositories.team.query.JpaTeamQueryTodoListDataAccess;
import org.example.persistance.repositories.team.query.mappers.TodoListMapper;
import org.example.persistance.repositories.todo.springData.JpaTodoListRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import utils.TeamProvider;
import utils.TodoListProvider;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = Main.class)
@Profile("prod")
public class JpaTeamTodoListDataAccessTest {
  @Autowired
  private JpaTeamQueryTodoListDataAccess sutJpaDataAccess;
  @Autowired
  private JpaTeamRepository jpaTeamRepository;
  @Autowired
  private JpaTodoListRepository JpaTodoListRepository;
  @Test
  void JpaTeamTodoListDataAccess_getTodoListsByTeamIdTest(){
    var createdTeamId = createTeamThenInsertTodoList(0);
    var todoList = JpaTodoListRepository.save(TodoListProvider.getEmptyTodoList(createdTeamId));
    var result = sutJpaDataAccess.getTodoListsByTeamId(createdTeamId);
    assertArrayEquals(List.of(TodoListMapper.toQueryTeamDto(todoList)).toArray(), result.toArray());
  }

  @Test
  void JpaTeamTodoListDataAccess_getTodoListsCountByTeamId() {
    var createdTeamId = createTeamThenInsertTodoList(2);
    var count = sutJpaDataAccess.getTodoListsCountByTeamId(createdTeamId);
    assertEquals(2, count);
  }

  private String createTeamThenInsertTodoList(int nbrOfTodoList) {
    var createdTeam = jpaTeamRepository.save(TeamMapper.fromDto(TeamProvider.emptyTeamDto()));
    for (int i = 0; i < nbrOfTodoList; i++) {
      JpaTodoListRepository.save(TodoListProvider.getEmptyTodoList(createdTeam.getId()));
    }
    return createdTeam.getId();
  }
}
