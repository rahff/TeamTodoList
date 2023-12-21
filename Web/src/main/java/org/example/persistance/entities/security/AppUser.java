package org.example.persistance.entities.security;

import jakarta.persistence.*;
import org.shared.dto.SubscriptionDto;
import org.shared.dto.UserDto;

import java.util.Optional;

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
  private String teamId;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Subscription subscription;
  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private Authority authority;

  public AppUser() {}

  public AppUser(String id, String email, String password, String name, Authority authority, String accountId, String teamId, Subscription subscription) {
    this.id = id;
    this.email = email;
    this.password = password;
    this.name = name;
    this.accountId = accountId;
    this.authority = authority;
    this.teamId = teamId;
    this.subscription = subscription;
  }
  public Subscription getSubscription() {
    return subscription;
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
    Optional<SubscriptionDto> sub = subscription != null ? Optional.of(new SubscriptionDto(subscription.getId(), subscription.paid())) : Optional.empty();
    Optional<String> _teamId = teamId != null ? Optional.of(teamId) : Optional.empty();
    return new UserDto(id, email, name, password, getAuthority().getUserRole(), accountId, _teamId, sub);
  }

  public String getAccountId() {
    return accountId;
  }

  public void setTeamId(String teamId) {
    this.teamId = teamId;
  }
}
