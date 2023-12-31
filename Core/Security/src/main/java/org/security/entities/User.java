package org.security.entities;

import org.shared.dto.SubscriptionDto;
import org.shared.dto.UserDto;

import java.util.Optional;

public class User {
  private final String id;
  private String email;
  private String name;
  private String password;
  private String teamId;
  private final String role;
  private final String accountId;
  private SubscriptionDto subscription;
  private User(String id, String email, String name, String password, String teamId, String role, String accountId, SubscriptionDto subscription){
    this.id = id;
    this.email = email;
    this.password = password;
    this.teamId = teamId;
    this.role = role;
    this.name = name;
    this.accountId = accountId;
    this.subscription = subscription;
  }

  public static User fromDto(UserDto dto) {
    return new User(dto.id(), dto.email(), dto.name(), dto.password(), dto.teamId().orElse(null), dto.role(), dto.accountId(), dto.subscription().orElse(null));
  }

  public void setPassword(String password){
    this.password = password;
  }

  public UserDto snapshot(){
    Optional<SubscriptionDto> userSubscription = this.subscription != null ? Optional.of(subscription): Optional.empty();
    Optional<String> _teamId = teamId != null ? Optional.of(teamId) : Optional.empty();
    return new UserDto(id, email, name, password, role, accountId, _teamId, userSubscription);
  }

  public void paidSubscription() {
      this.subscription = new SubscriptionDto(subscription.id(), true);
  }
}
