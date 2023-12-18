package org.example.security.services;

import org.example.security.jwt.JwtDecoder;
import org.security.ports.spi.JwtEncoder;

public interface TokenService extends JwtDecoder, JwtEncoder {
}
