package org.team.ports.dto;

import java.util.Objects;

public record RemoveTeammateFromTeamRequest(String teamId, String teammateId) {
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RemoveTeammateFromTeamRequest that)) return false;
        return Objects.equals(teamId, that.teamId) && Objects.equals(teammateId, that.teammateId);
    }

    public int hashCode() {
        return Objects.hash(teamId, teammateId);
    }
}
