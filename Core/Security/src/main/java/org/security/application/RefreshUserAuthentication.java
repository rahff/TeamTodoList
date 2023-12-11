package org.security.application;

import org.security.ports.dto.JwtAuthenticationResult;
import org.security.ports.spi.JwtEncoder;
import org.shared.dto.UserDto;
import org.shared.spi.UserRepository;

public class RefreshUserAuthentication extends UserSecurity {
    public RefreshUserAuthentication(UserRepository userRepository, JwtEncoder jwtService) {
        super(jwtService, userRepository);
    }

    public JwtAuthenticationResult refreshAuthentication(UserDto authenticatedUser) {
        return makeJwtResult(authenticatedUser);
    }
}
