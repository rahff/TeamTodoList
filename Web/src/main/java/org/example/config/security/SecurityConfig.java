package org.example.config.security;


import org.example.security.*;
import org.security.application.*;
import org.security.ports.api.CreateAccount;
import org.security.ports.api.RefreshAuthentication;
import org.security.ports.api.Signup;
import org.security.ports.api.UsernamePasswordAuthentication;
import org.security.ports.spi.*;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig {
  @Autowired
  private JwtFilter jwtFilter;
  @Autowired
  private JwtAuthenticationProvider jwtAuthenticationProvider;
  @Autowired
  UserRepository userRepository;
  @Autowired
  TokenService jwtService;
  @Autowired
  AccountRepository accountRepository;
  @Autowired
  PaymentGateway paymentGateway;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);
    http.authorizeHttpRequests(authorize -> authorize.requestMatchers( "/login", "/create-account").permitAll()
      .anyRequest().authenticated());
    http.apply(new JwtConfigurer()
      .withJwtFilter(jwtFilter)
      .withAuthenticationProvider(jwtAuthenticationProvider));
     return http.build();
  }

  @Bean
  PasswordEncoder passwordEncoder(){
    return new BCryptPasswordEncoder();
  }

  @Bean
  PasswordSecurity passwordSecurity(){
    return new SecurityPasswordService(passwordEncoder());
  }

  @Bean
  UsernamePasswordAuthentication usernamePasswordAuthentication(){
    return new UsernamePasswordAuthenticationProvider(userRepository, passwordSecurity(), jwtService);
  }
  @Bean
  Signup signup(){
    return new CreateUserManager(userRepository, jwtService);
  }

  @Bean
  CreateAccount createAccount(){
    return new CreateManagerAccount(accountRepository, paymentGateway);
  }

  @Bean
  ChangePassword changePassword(){
    return new SetTeammatePassword(userRepository, passwordSecurity());
  }

  @Bean
  RefreshAuthentication refreshAuthentication(){
    return new RefreshUserAuthentication(userRepository, jwtService);
  }
}
