package org.queryTeam.spi.fakes;

import org.queryTeam.model.TeamCard;
import org.queryTeam.model.TeamListViewModel;

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
