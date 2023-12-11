package team;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.query.team.api.TeammateListViewQuery;
import org.query.team.model.Teammate;
import org.query.team.model.TeammateListViewModel;
import org.query.team.spi.fakes.InMemoryTeamDataAccess;
import org.query.team.spi.fakes.InMemoryUserDataAccess;


import java.util.List;

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
        assertEquals(new FakeTeammateListViewModel().get(), result);
    }
}

class FakeTeammateListViewModel {

    public TeammateListViewModel get(){
        return new TeammateListViewModel(
                List.of(
                        new Teammate("teammateId1", "teammateName1", "teammateName1@gmail.com","teamId1"),
                        new Teammate("teammateId2", "teammateName2", "teammateName2@gmail.com", "teamId1"),
                        new Teammate("teammateId3", "teammateName3", "teammateName3@gmail.com", "teamId2"),
                        new Teammate("teammateId4", "teammateName4", "teammateName4@gmail.com", "teamId2")
                ));
    }
}