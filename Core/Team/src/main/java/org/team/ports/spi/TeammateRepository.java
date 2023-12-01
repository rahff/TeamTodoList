package org.team.ports.spi;

import org.shared.dto.UserDto;

import java.util.List;

public interface TeammateRepository {
  void saveUserAsTeammate(UserDto userDto);

  void addTeamIdOnTeammate(List<String> ids, String teamId);
  void removeTeammateUser(String userId);
}
