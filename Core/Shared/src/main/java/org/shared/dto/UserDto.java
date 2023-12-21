package org.shared.dto;

import java.util.Objects;
import java.util.Optional;

public record UserDto(String id, String email, String name, String password, String role, String accountId, Optional<String> teamId, Optional<SubscriptionDto> subscription) {

 public String toString() {
    return "UserDto{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", role='" + role + '\'' +
      ", accountId='" + accountId + '\'' +
      '}';
  }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserDto userDto)) return false;
        if (!Objects.equals(id, userDto.id)) return false;
        if (!Objects.equals(email, userDto.email)) return false;
        if (!Objects.equals(name, userDto.name)) return false;
        if (!Objects.equals(role, userDto.role)) return false;
        if (!Objects.equals(subscription, userDto.subscription)) return false;
        return Objects.equals(accountId, userDto.accountId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, role, accountId, subscription);

    }
}
