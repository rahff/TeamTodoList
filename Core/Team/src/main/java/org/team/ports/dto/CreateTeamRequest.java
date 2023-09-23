package org.team.ports.dto;

import java.util.List;

public record CreateTeamRequest(String id, String name, List<String> teammates, String accountId) {
}
