package org.security.ports.spi;

public class FakePasswordEncoder implements PasswordSecurity {
  @Override
  public boolean matches(String password, String encodedPassword) {
    return password.equals(encodedPassword.toLowerCase());
  }

  @Override
  public String encode(String rawPassword) {
    return rawPassword.toUpperCase();
  }
}
