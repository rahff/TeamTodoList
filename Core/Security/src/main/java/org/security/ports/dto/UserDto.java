package org.security.ports.dto;

public record UserDto(String id, String email, String name, String password, String role, String accountId) {

 /* public String toString() {
    return "UserDto{" +
      "id='" + id + '\'' +
      ", email='" + email + '\'' +
      ", name='" + name + '\'' +
      ", role='" + role + '\'' +
      ", accountId='" + accountId + '\'' +
      '}';
  }*/
}
