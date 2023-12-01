package org.query.team.spi.fakes;



import org.query.team.model.TeamDetails;
import org.query.team.model.TeamDetailsViewModel;
import org.query.team.model.Teammate;
import org.query.team.model.TodoList;

import java.util.List;
import java.util.Optional;

public class FakeTeamDetailsViewModel {

    public static TeamDetailsViewModel get() {
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
