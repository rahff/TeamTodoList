package org.security.ports.spi;

public interface JwtEncoder {
  String encode(String userEmail, String role);

}
