package org.security.application;


import org.security.ports.dto.JwtAuthenticationResult;
import org.shared.dto.UserDto;
import org.security.ports.spi.JwtEncoder;
import org.security.ports.spi.PasswordSecurity;
import org.shared.spi.UserRepository;

public abstract class UserSecurity {
  protected final JwtEncoder jwtService;
  protected final UserRepository userRepository;

  public UserSecurity(JwtEncoder jwtService, UserRepository userRepository) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;

  }

  protected JwtAuthenticationResult makeJwtResult(UserDto user) {
    var token = jwtService.encode(user.email(), user.role());
    return new JwtAuthenticationResult(user, token.accessToken(), token.refreshToken());
  }
}
