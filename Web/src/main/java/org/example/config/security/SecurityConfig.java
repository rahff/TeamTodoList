package org.example.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
     http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/").permitAll()
       .anyRequest().authenticated());
      http.csrf(AbstractHttpConfigurer::disable);
      http.sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
      http.oauth2ResourceServer(oauth -> oauth.jwt(jwt -> jwt.authenticationManager(new JwtAuthenticationManager())));
     return http.build();
  }
}
