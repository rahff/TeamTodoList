package org.security.application;


import org.security.ports.dto.JwtAuthenticationResult;
import org.shared.dto.UserDto;
import org.security.ports.spi.JwtEncoder;
import org.security.ports.spi.PasswordSecurity;
import org.shared.spi.UserRepository;

public abstract class UserSecurity {
  protected final JwtEncoder jwtService;
  protected final UserRepository userRepository;
  protected final PasswordSecurity passwordEncoder;

  public UserSecurity(JwtEncoder jwtService, UserRepository userRepository, PasswordSecurity passwordEncoder) {
    this.jwtService = jwtService;
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  protected JwtAuthenticationResult makeJwtResult(UserDto user, String role) {
    var token = jwtService.encode(user.email(), role);
    return new JwtAuthenticationResult(user, token.accessToken(), token.refreshToken());
  }
}
