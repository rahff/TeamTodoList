package team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.query.team.api.TeamListViewQuery;
import org.query.team.model.TeamCard;
import org.query.team.model.TeamListViewModel;
import org.query.team.model.Teammate;
import org.query.team.spi.fakes.InMemoryTeamDataAccess;
import org.query.team.spi.fakes.InMemoryTodoListDataAccess;
import org.query.team.spi.fakes.InMemoryUserDataAccess;


import java.util.List;

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
        assertEquals(result, new FakeTeamListViewModel().get());
    }
}

class FakeTeamListViewModel {

    public TeamListViewModel get() {
        return new TeamListViewModel(
                List.of(
                        new TeamCard("teamId1", "Team1", 2, 2),
                        new TeamCard("teamId2", "Team2", 2, 2)
                ),
                List.of(new Teammate("id_teammate3",
                                "teammateNameid_teammate3",
                                "teammateid_teammate3@gmail.com", null),
                        new Teammate("id_teammate4", "teammateNameid_teammate4",
                                "teammateid_teammate4@gmail.com", null))
        );
    }
}
