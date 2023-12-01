package org.security.ports.api;

import org.security.ports.dto.JwtAuthenticationResult;
import org.shared.dto.UserDto;

public interface RefreshAuthentication {
    JwtAuthenticationResult refreshAuthentication(UserDto authenticatedUser);
}
