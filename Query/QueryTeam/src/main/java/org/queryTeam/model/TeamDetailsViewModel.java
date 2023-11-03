package org.queryTeam.model;

import java.util.List;
import java.util.Objects;

public record TeamDetailsViewModel(TeamDetails teamDetails, List<Teammate> availableTeammates) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamDetailsViewModel that)) return false;
        return Objects.equals(teamDetails, that.teamDetails) && Objects.equals(availableTeammates, that.availableTeammates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(teamDetails, availableTeammates);
    }
}
