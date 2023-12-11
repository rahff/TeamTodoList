package team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.query.team.model.TeamDetails;
import org.query.team.model.TeamDetailsViewModel;
import org.query.team.model.Teammate;
import org.query.team.model.TodoList;
import org.query.team.api.TeamDetailsViewQuery;
import org.query.team.spi.fakes.InMemoryTeamDataAccess;
import org.query.team.spi.fakes.InMemoryUserDataAccess;
import org.query.team.spi.fakes.InMemoryTodoListDataAccess;

import java.util.List;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeamDetailsViewQueryTest {

    private TeamDetailsViewQuery query;

    @BeforeEach
    void setup(){
        var teamDataAccess = new InMemoryTeamDataAccess();
        var todoListDataAccess = new InMemoryTodoListDataAccess();
        var userDataAccess = new InMemoryUserDataAccess();
        query = new TeamDetailsViewQuery(teamDataAccess, todoListDataAccess, userDataAccess);
    }
    @Test
    public void should_aggregates_data_to_view_model() throws ExecutionException, InterruptedException {
        var result = query.query("teamId1", "accountId");
        assertEquals(result, new FakeTeamDetailsViewModel().get());
    }

}

class FakeTeamDetailsViewModel {

    public TeamDetailsViewModel get() {
        return new TeamDetailsViewModel(
                new TeamDetails("teamId1", "Team1",
                        List.of(new Teammate("id_teammate1",
                                        "teammateNameid_teammate1",
                                        "teammateid_teammate1@gmail.com", "teamId1"),
                                new Teammate("id_teammate2", "teammateNameid_teammate2",
                                        "teammateid_teammate2@gmail.com", "teamId1")),
                        List.of(
                                new TodoList("todoListId1", "AtodoList", "2023-10-14"),
                                new TodoList("todoListId2", "OthertodoList", "2023-10-17")
                        )), List.of(new Teammate("id_teammate3",
                        "teammateNameid_teammate3",
                        "teammateid_teammate3@gmail.com", null),
                new Teammate("id_teammate4", "teammateNameid_teammate4",
                        "teammateid_teammate4@gmail.com", null))
        );
    }
}

