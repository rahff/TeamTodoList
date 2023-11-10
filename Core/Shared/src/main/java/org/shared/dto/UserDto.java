package org.shared.dto;

public record UserDto(String id, String email, String name, String password, String role, String accountId) {

 public String toString() {
    return "UserDto{" +
      "id='" + id + '\'' +
      ", name='" + name + '\'' +
      ", role='" + role + '\'' +
      ", accountId='" + accountId + '\'' +
      '}';
  }
}