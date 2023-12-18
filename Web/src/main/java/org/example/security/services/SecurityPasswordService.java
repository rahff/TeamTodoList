package org.example.security.services;

import org.security.ports.spi.PasswordSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;


public class SecurityPasswordService implements PasswordSecurity {
  private final PasswordEncoder passwordEncoder;

  public SecurityPasswordService(PasswordEncoder passwordEncoder) {
    this.passwordEncoder = passwordEncoder;
  }

  public boolean matches(String password, String encodedPassword) {
    return passwordEncoder.matches(password, encodedPassword);
  }

  public String encode(String rawPassword) {
    return passwordEncoder.encode(rawPassword);
  }
}
