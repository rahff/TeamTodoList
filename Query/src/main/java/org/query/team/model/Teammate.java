package org.query.team.model;

import java.util.Objects;
import java.util.Optional;

public record Teammate(String id, String name, String email, String teamId) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Teammate teammate)) return false;
        return Objects.equals(id, teammate.id) && Objects.equals(name, teammate.name) && Objects.equals(email, teammate.email) && Objects.equals(teamId, teammate.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, teamId);
    }
}
