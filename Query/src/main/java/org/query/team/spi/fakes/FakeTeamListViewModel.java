package org.query.team.spi.fakes;


import org.query.team.model.TeamCard;
import org.query.team.model.TeamListViewModel;

import java.util.List;

public class FakeTeamListViewModel {

    public static TeamListViewModel get() {
        return new TeamListViewModel(
                List.of(
                    new TeamCard("teamId1", "Team1", 2, 2),
                    new TeamCard("teamId2", "Team2", 2, 2)
                )
        );
    }
}
