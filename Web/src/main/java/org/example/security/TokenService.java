package org.example.security;

import org.security.ports.spi.JwtEncoder;

public interface TokenService extends JwtDecoder, JwtEncoder {
}
