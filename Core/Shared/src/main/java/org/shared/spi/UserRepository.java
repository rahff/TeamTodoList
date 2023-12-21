package org.shared.spi;


import org.shared.dto.UserDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
  Optional<UserDto> findByEmail(String email);

  UserDto save(UserDto newUser);

  Optional<UserDto> findBySubscription(String subscriptionId);

  Optional<UserDto> findByAccountIdAndTeamIdIsNull(String accountId);


  Optional<UserDto> findById(String teammateId);

  void addTeamIdOnTeammate(List<String> theirIds, String teamId);

  void removeTeammateUser(String teammateId);
}
