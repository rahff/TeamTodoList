package org.example.security;

import jakarta.transaction.Transactional;
import org.example.controllers.api.httpRequestPayloads.LoginUserRequestBody;
import org.example.controllers.api.httpRequestPayloads.SignupUserRequestBody;
import org.example.persistance.entities.AppUser;
import org.example.persistance.entities.Authority;
import org.example.persistance.repositories.AppUserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class UsernamePasswordAuthenticationProvider {

  private final AppUserRepository userRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtService jwtService;
  private final String DEFAULT_PLAN = "FREE";

  public UsernamePasswordAuthenticationProvider(AppUserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
    this.jwtService = jwtService;
  }

  @Transactional
  public JwtAuthenticationResult signupUser(SignupUserRequestBody signupUserRequest) throws Exception {
    var savedUser = createUser(signupUserRequest);
    var userRole = getUserPlan(savedUser);
    return makeJwtResult(savedUser, userRole);
  }

  private AppUser createUser(SignupUserRequestBody signupUserRequest) {
    var defaultAuthority = new Authority(DEFAULT_PLAN);
    var newUser = new AppUser();
    newUser.setAuthority(defaultAuthority);
    newUser.setEmail(signupUserRequest.email());
    newUser.setPassword(signupUserRequest.password());
    newUser.setName(signupUserRequest.name());
    newUser.setId(signupUserRequest.id());
    return userRepository.save(newUser);
  }

  public JwtAuthenticationResult loginUser(LoginUserRequestBody loginUserRequest) throws AuthenticationException {
    var foundedUser = userRepository.findByEmail(loginUserRequest.email()).orElseThrow(()-> new BadCredentialsException("bad credentials"));
    if(passwordEncoder.matches(loginUserRequest.password(), foundedUser.getPassword())){
      var userPlan = getUserPlan(foundedUser);
      return makeJwtResult(foundedUser, userPlan);
    }
    throw new BadCredentialsException("bad credentials");
  }

  private String getUserPlan(AppUser foundedUser) {
    return foundedUser.getAuthority().getUserPlan();
  }

  private JwtAuthenticationResult makeJwtResult(AppUser user, String role) {
    var token = jwtService.encode(user.getEmail(), role);
    return new JwtAuthenticationResult(user, token);
  }
}
