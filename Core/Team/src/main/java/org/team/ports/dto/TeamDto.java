package org.team.ports.dto;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record TeamDto(String id, String name, List<String> teammates, String accountId) {
  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof TeamDto teamDto)) return false;
    return Objects.equals(id, teamDto.id)
      && Objects.equals(name, teamDto.name)
      && Arrays.equals(teammates.toArray(), teamDto.teammates.toArray());
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, teammates);
  }
}
