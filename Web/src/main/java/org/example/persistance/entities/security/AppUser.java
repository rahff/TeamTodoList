package org.example.persistance.entities.security;

import jakarta.persistence.*;
import org.shared.dto.UserDto;

@Entity
@Table(name = "user")
public class AppUser {
  @Id
  @Column(name = "id", nullable = false)
  private String id;
  @Column(unique = true)
  private String email;
  private String password;
  private String name;
  private String accountId;
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Authority authority;

  public AppUser() {}

  public AppUser(String id, String email, String password, String name, Authority authority, String accountId) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.accountId = accountId;
    this.authority = authority;
  }

  public String getId() {
    return id;
  }


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Authority getAuthority() {
    return authority;
  }

  public void setAuthority(Authority authority) {
    this.authority = authority;
  }

  public void setId(String id) {
    this.id = id;
  }

  public UserDto toDto(){
    return new UserDto(id, email, name, password, getAuthority().getUserRole(), accountId);
  }

  public String getAccountId() {
    return accountId;
  }
}
