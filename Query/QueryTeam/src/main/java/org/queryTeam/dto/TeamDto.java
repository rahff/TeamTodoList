package org.queryTeam.model;

import java.util.List;

public record TeamDto(String id, String name, List<String> teammateIds) {
}
