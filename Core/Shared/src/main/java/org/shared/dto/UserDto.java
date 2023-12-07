package org.shared.dto;

import java.util.Optional;

public record UserDto(String id, String email, String name, String password, String role, String accountId, Optional<SubscriptionDto> subscription) {

 public String toString() {
    return "UserDto{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", role='" + role + '\'' +
      ", accountId='" + accountId + '\'' +
      '}';
  }
}
