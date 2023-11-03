package org.security.application;


import org.security.ports.api.Signup;
import org.security.ports.dto.JwtAuthenticationResult;
import org.security.ports.dto.SignupUserRequest;
import org.security.ports.dto.UserDto;
import org.security.ports.spi.JwtEncoder;
import org.security.ports.spi.PasswordSecurity;
import org.security.ports.spi.UserRepository;

public class CreateUserManager extends UserSecurity implements Signup {
  private final String DEFAULT_ROLE = "MANAGER";
  public CreateUserManager(UserRepository userRepository, JwtEncoder jwtService, PasswordSecurity passwordEncoder) {
    super(jwtService, userRepository, passwordEncoder);
  }

  public JwtAuthenticationResult doSignup(SignupUserRequest signupUserRequest) throws Exception {
    var savedUser = createUser(signupUserRequest);
    return makeJwtResult(savedUser, savedUser.role());
  }

  private UserDto createUser(SignupUserRequest signupUserRequest) {
    var newUser = initializeUserWithProperties(signupUserRequest);
    return userRepository.save(newUser);
  }

  private UserDto initializeUserWithProperties(SignupUserRequest signupUserRequest){
    return new UserDto(
      signupUserRequest.userId(),
      signupUserRequest.email(),
      signupUserRequest.name(),
      signupUserRequest.password(),
      DEFAULT_ROLE,
      signupUserRequest.accountId());
  }
}
