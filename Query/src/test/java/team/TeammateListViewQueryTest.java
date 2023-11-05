package team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.query.team.api.TeammateListViewQuery;
import org.query.team.spi.fakes.FakeTeammateListViewModel;
import org.query.team.spi.fakes.InMemoryTeamDataAccess;
import org.query.team.spi.fakes.InMemoryUserDataAccess;


import static org.junit.jupiter.api.Assertions.assertEquals;

public class TeammateListViewQueryTest {

    private TeammateListViewQuery teammateListViewQuery;

    @BeforeEach
    void setup(){
        var userDataAccess = new InMemoryUserDataAccess();
        var teamDataAccess = new InMemoryTeamDataAccess();
        teammateListViewQuery = new TeammateListViewQuery(userDataAccess, teamDataAccess);
    }
    @Test
    void should_aggregate_data_for_teammate_list_view(){
        var result = teammateListViewQuery.query("accountId");
        assertEquals(FakeTeammateListViewModel.get(), result);
    }
}
