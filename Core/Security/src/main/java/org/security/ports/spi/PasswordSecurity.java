package org.security.ports.spi;

public interface PasswordSecurity {
  boolean matches(String password, String encodedPassword);
  String encode(String rawPassword);
}
