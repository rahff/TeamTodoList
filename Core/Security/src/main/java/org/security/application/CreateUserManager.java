package org.security.application;


import org.security.ports.dto.JwtAuthenticationResult;
import org.security.ports.dto.SignupUserRequest;
import org.shared.dto.SubscriptionDto;
import org.shared.dto.UserDto;
import org.security.ports.spi.JwtEncoder;
import org.shared.spi.UserRepository;

import java.util.Optional;

public class CreateUserManager extends UserSecurity {
  public CreateUserManager(UserRepository userRepository, JwtEncoder jwtService) {
    super(jwtService, userRepository);
  }

  public JwtAuthenticationResult doSignup(SignupUserRequest signupUserRequest, SubscriptionDto subscription) throws Exception {
    var savedUser = createUser(signupUserRequest, subscription);
    return makeJwtResult(savedUser);
  }

  private UserDto createUser(SignupUserRequest signupUserRequest, SubscriptionDto subscription) {
    var newUser = initializeUserWithProperties(signupUserRequest, subscription);
    return userRepository.save(newUser);
  }

  private UserDto initializeUserWithProperties(SignupUserRequest signupUserRequest, SubscriptionDto subscription){
    String ROLE = "MANAGER";
    return new UserDto(
      signupUserRequest.userId(),
      signupUserRequest.email(),
      signupUserRequest.name(),
      signupUserRequest.password(),
      ROLE,
      signupUserRequest.accountId(), Optional.of(subscription));
  }
}
