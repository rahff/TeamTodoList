package org.security.ports.spi;

import org.security.ports.dto.JwtTokenPair;

public interface JwtEncoder {
  JwtTokenPair encode(String userEmail, String role);

}
