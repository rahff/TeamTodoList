package org.team.ports.dto;

import java.util.List;

public record AddTeammatesOnTeamRequest(String teamId, List<String> teammateToAdd) {
}
