package team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.query.team.api.TeamListViewQuery;
import org.query.team.spi.UserDataAccess;
import org.query.team.spi.fakes.FakeTeamListViewModel;
import org.query.team.spi.fakes.InMemoryTeamDataAccess;
import org.query.team.spi.fakes.InMemoryTodoListDataAccess;
import org.query.team.spi.fakes.InMemoryUserDataAccess;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamListViewQueryTest {

    private TeamListViewQuery viewQuery;

    @BeforeEach
    void setup(){
        var userDataAccess = new InMemoryUserDataAccess();
        var teamDataAccess = new InMemoryTeamDataAccess();
        var todoListDataAccess = new InMemoryTodoListDataAccess();
        viewQuery = new TeamListViewQuery(teamDataAccess, todoListDataAccess, userDataAccess);
    }
    @Test
    void should_aggregates_view_model_data_for_team_list() throws Exception {
        var result = viewQuery.query("accountId");
        assertEquals(result, FakeTeamListViewModel.get());
    }
}
