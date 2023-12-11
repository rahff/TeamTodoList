package org.example.controllers.api.security;


import org.example.controllers.api.security.jsonPayload.JwtAuthenticationJson;
import org.example.controllers.api.security.mapper.UserDtoMapper;
import org.security.application.RefreshUserAuthentication;
import org.shared.dto.UserDto;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

@RestController
public class AuthenticationByTokenController {

    private final RefreshUserAuthentication refreshUserAuthentication;

    public AuthenticationByTokenController(RefreshUserAuthentication refreshUserAuthentication) {
        this.refreshUserAuthentication = refreshUserAuthentication;
    }

    @GetMapping("/authentication")
    public JwtAuthenticationJson getAuthentication(@AuthenticationPrincipal UserDto authenticatedUser){
        try {
            var authentication = refreshUserAuthentication.refreshAuthentication(authenticatedUser);
            return new JwtAuthenticationJson(UserDtoMapper.toJson(authenticatedUser), authentication.accessToken(), authentication.refreshToken());
        }catch(Exception e){
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("/refresh-token")
    public JwtAuthenticationJson refreshToken(@AuthenticationPrincipal UserDto authenticatedUser){
        return getAuthentication(authenticatedUser);
    }
}
