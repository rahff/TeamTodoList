import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.queryTeam.api.TeamDetailsViewQuery;
import org.queryTeam.spi.fakes.FakeTeamDetailsViewModel;
import org.queryTeam.spi.fakes.InMemoryTeamDataAccess;
import org.queryTeam.spi.fakes.InMemoryTodoListDataAccess;
import org.queryTeam.spi.fakes.InMemoryUserDataAccess;


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
        var result = query.query("teamId1");
        assertEquals(result, FakeTeamDetailsViewModel.get());
    }

}
