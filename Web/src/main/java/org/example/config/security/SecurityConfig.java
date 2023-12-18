package org.example.config.security;


import org.example.security.jwt.JwtAuthenticationProvider;
import org.example.security.jwt.JwtConfigurer;
import org.example.security.jwt.JwtFilter;
import org.example.security.services.SecurityPasswordService;
import org.example.security.services.TokenService;
import org.example.security.webhook.WebhookAuthenticationProvider;
import org.example.security.webhook.WebhookConfigurer;
import org.example.security.webhook.WebhookFilter;
import org.example.transactions.security.SetPasswordTransaction;
import org.security.application.*;
import org.security.ports.spi.PasswordSecurity;
import org.shared.spi.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
@Profile("prod")
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
  WebhookFilter webhookFilter;
  @Autowired
  WebhookAuthenticationProvider webhookAuthenticationProvider;


  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable);
    http.authorizeHttpRequests(authorize -> authorize.requestMatchers( "/login", "/create-account").permitAll()
      .anyRequest().authenticated());
    http.apply(new WebhookConfigurer()
            .withJwtFilter(webhookFilter)
            .withAuthenticationProvider(webhookAuthenticationProvider));
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
  UsernamePasswordAuthenticationProvider usernamePasswordAuthentication(){
    return new UsernamePasswordAuthenticationProvider(userRepository, passwordSecurity(), jwtService);
  }
  @Bean
  RefreshUserAuthentication refreshAuthentication(){
    return new RefreshUserAuthentication(userRepository, jwtService);
  }

  @Bean
  SetPasswordTransaction changePassword(){
    return new SetPasswordTransaction(new SetTeammatePassword(userRepository, passwordSecurity()));
  }

}
