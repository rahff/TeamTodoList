package org.query.team.model;

import java.util.List;

public record TeamListViewModel(List<TeamCard> list, List<Teammate> availableTeammates) {
}
