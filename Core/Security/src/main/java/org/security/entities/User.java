package org.security.entities;

import org.security.ports.dto.UserDto;

public class User {
  private final String id;
  private String email;
  private String name;
  private String password;
  private final String role;
  private final String accountId;
  private User(String id, String email, String name, String password, String role, String accountId){
    this.id = id;
    this.email = email;
    this.password = password;
    this.role = role;
    this.name = name;
    this.accountId = accountId;
  }

  public static User fromDto(UserDto dto) {
    return new User(dto.id(), dto.email(), dto.name(), dto.password(), dto.role(), dto.accountId());
  }

  public void setPassword(String password){
    this.password = password;
  }

  public UserDto snapshot(){
    return new UserDto(id, email, name, password, role, accountId);
  }
}
