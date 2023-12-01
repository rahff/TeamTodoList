package org.query.team.spi.fakes;


import org.query.team.model.TeamCard;
import org.query.team.model.TeamListViewModel;
import org.query.team.model.Teammate;

import java.util.List;

public class FakeTeamListViewModel {

    public static TeamListViewModel get() {
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
