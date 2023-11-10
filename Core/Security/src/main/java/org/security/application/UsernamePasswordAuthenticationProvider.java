package org.security.application;


import org.security.exceptions.BadCredentialsException;
import org.security.ports.api.UsernamePasswordAuthentication;
import org.security.ports.dto.JwtAuthenticationResult;
import org.security.ports.dto.LoginUserRequest;
import org.security.ports.spi.JwtEncoder;
import org.security.ports.spi.PasswordSecurity;
import org.shared.spi.UserRepository;

public class UsernamePasswordAuthenticationProvider extends UserSecurity implements UsernamePasswordAuthentication {
  public UsernamePasswordAuthenticationProvider(UserRepository userRepository, PasswordSecurity passwordEncoder, JwtEncoder jwtService) {
    super(jwtService, userRepository, passwordEncoder);
  }

  public JwtAuthenticationResult loginUser(LoginUserRequest loginUserRequest) throws BadCredentialsException {
    var foundedUser = userRepository.findByEmail(loginUserRequest.email())
      .orElseThrow(BadCredentialsException::new);
    if(passwordEncoder.matches(loginUserRequest.password(), foundedUser.password())){
      return makeJwtResult(foundedUser, foundedUser.role());
    }
    throw new BadCredentialsException();
  }
}
