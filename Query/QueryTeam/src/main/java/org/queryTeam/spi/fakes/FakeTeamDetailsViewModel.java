package org.queryTeam.spi.fakes;

import org.queryTeam.model.TeamDetails;
import org.queryTeam.model.TeamDetailsViewModel;
import org.queryTeam.model.Teammate;
import org.queryTeam.model.TodoList;

import java.util.List;
import java.util.Optional;

public class FakeTeamDetailsViewModel {

    public static TeamDetailsViewModel get() {
        return new TeamDetailsViewModel(
                new TeamDetails("teamId1", "Team1",
                        List.of(new Teammate("id_teammate1",
                                        "teammateNameid_teammate1",
                                        "teammateid_teammate1@gmail.com", Optional.of("teamId1")),
                                new Teammate("id_teammate2", "teammateNameid_teammate2",
                                        "teammateid_teammate2@gmail.com", Optional.of("teamId1"))),
                        List.of(
                                new TodoList("todoListId1", "AtodoList", "2023-10-14"),
                                new TodoList("todoListId2", "OthertodoList", "2023-10-17")
                        )), List.of(new Teammate("id_teammate3",
                                "teammateNameid_teammate3",
                                "teammateid_teammate3@gmail.com", Optional.empty()),
                                    new Teammate("id_teammate4", "teammateNameid_teammate4",
                                "teammateid_teammate4@gmail.com", Optional.empty())));
    }
}
